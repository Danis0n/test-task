package ru.danis0n.digitalbudget.service.offer;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import ru.danis0n.digitalbudget.dto.movie.MovieDto;
import ru.danis0n.digitalbudget.exception.NotFoundException;
import ru.danis0n.digitalbudget.mapper.MovieMapper;
import ru.danis0n.digitalbudget.model.Movie;
import ru.danis0n.digitalbudget.repository.FavoriteRepository;

import java.util.List;

@Component("sql")
@RequiredArgsConstructor
public class OfferServiceSql implements OfferService {

    private static final Logger logger = LoggerFactory.getLogger(OfferServiceSql.class);

    private final FavoriteRepository favoriteRepository;

    @Override
    public List<MovieDto> offer(Long userId, Integer limit) {
        Pageable pageable = PageRequest.of(0, limit);

        logger.info("User {} requested offer with sql load type", userId);
        List<Movie> list = favoriteRepository.findMoviesNotInUserFavorites(userId, pageable);

        if (list.isEmpty()) {
            logger.warn("User {} requested offer has no results!", userId);
            throw new NotFoundException("There is nothing to offer");
        }

        return list.stream()
                .map(MovieMapper.mapToDto)
                .toList();
    }
}
