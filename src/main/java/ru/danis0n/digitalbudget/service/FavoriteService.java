package ru.danis0n.digitalbudget.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.danis0n.digitalbudget.dto.movie.MovieDto;
import ru.danis0n.digitalbudget.mapper.MovieMapper;
import ru.danis0n.digitalbudget.model.Movie;
import ru.danis0n.digitalbudget.model.User;
import ru.danis0n.digitalbudget.model.favorite.Favorite;
import ru.danis0n.digitalbudget.model.favorite.MovieUserKey;
import ru.danis0n.digitalbudget.repository.FavoriteRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private static final Logger logger = LoggerFactory.getLogger(FavoriteService.class);

    private final FavoriteRepository favoriteRepository;
    private final UserService userService;
    private final MovieService movieService;

    @Transactional
    public boolean addToFavorites(Long userId, Long movieId) {
        User user = userService.findModelById(userId);
        Movie movie = movieService.findModelById(movieId);

        MovieUserKey id = new MovieUserKey();
        id.setUserId(userId);
        id.setMovieId(movieId);

        Favorite favorite = new Favorite();
        favorite.setId(id);
        favorite.setUser(user);
        favorite.setMovie(movie);
        favorite.setDate(Timestamp.valueOf(LocalDateTime.now()));

        favoriteRepository.save(favorite);
        logger.info("Movie {} has been added to favorites for user: {}", movie.getTitle(), user.getUsername());
        return true;
    }

    @Transactional
    public boolean removeFromFavorites(Long userId, Long movieId) {
        MovieUserKey id = new MovieUserKey();
        id.setUserId(userId);
        id.setMovieId(movieId);

        favoriteRepository.deleteById(id);
        logger.info("Movie {} has been removed to favorites for user: {}", movieId, userId);
        return true;
    }

    public List<MovieDto> findAllFavorites(Long userId) {
        logger.info("User {} requested own favorites", userId);

        return findByUserId(userId)
                .stream()
                .map(favorite -> MovieMapper.mapToDto.apply(favorite.getMovie()))
                .toList();
    }

    public List<Favorite> findByUserId(Long userId) {
        return favoriteRepository.findFavoriteByUser_Id(userId);
    }
}
