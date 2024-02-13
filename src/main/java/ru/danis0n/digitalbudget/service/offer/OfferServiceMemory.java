package ru.danis0n.digitalbudget.service.offer;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.danis0n.digitalbudget.dto.movie.MovieDto;
import ru.danis0n.digitalbudget.exception.NotFoundException;
import ru.danis0n.digitalbudget.mapper.MovieMapper;
import ru.danis0n.digitalbudget.model.Movie;
import ru.danis0n.digitalbudget.model.favorite.Favorite;
import ru.danis0n.digitalbudget.service.FavoriteService;
import ru.danis0n.digitalbudget.service.MovieService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component("inMemory")
@RequiredArgsConstructor
public class OfferServiceMemory implements OfferService {

    private static final Logger logger = LoggerFactory.getLogger(OfferServiceMemory.class);

    private final FavoriteService favoriteService;
    private final MovieService movieService;

    @Override
    public List<MovieDto> offer(Long userId, Integer limit) {
        List<Movie> allMovies = movieService.findAll();
        List<Favorite> favorites = favoriteService.findByUserId(userId);

        Set<Long> favoriteMovieIds = favorites.stream()
                .map(favorite -> favorite.getMovie().getId())
                .collect(Collectors.toSet());

        if (favoriteMovieIds.isEmpty()) {
            logger.warn("User {} requested offer has no results!", userId);
            throw new NotFoundException("There is nothing to offer");
        }

        logger.info("User {} requested offer with inMemory load type", userId);
        return allMovies.stream()
                .filter(movie -> !favoriteMovieIds.contains(movie.getId()))
                .limit(limit)
                .map(MovieMapper.mapToDto)
                .toList();
    }
}
