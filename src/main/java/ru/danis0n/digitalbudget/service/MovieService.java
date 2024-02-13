package ru.danis0n.digitalbudget.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.danis0n.digitalbudget.dto.movie.MovieDto;
import ru.danis0n.digitalbudget.exception.BadRequestException;
import ru.danis0n.digitalbudget.exception.NotFoundException;
import ru.danis0n.digitalbudget.mapper.MovieMapper;
import ru.danis0n.digitalbudget.model.Movie;
import ru.danis0n.digitalbudget.repository.MovieRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {

    private static final Logger logger = LoggerFactory.getLogger(MovieService.class);

    private final MovieRepository movieRepository;

    public List<MovieDto> findMovies(Integer page, Integer limit) {
        Page<Movie> title = movieRepository
                .findAll(PageRequest.of(page, limit, Sort.by(Sort.Direction.ASC, "title")));

        if (title.isEmpty()) {
            logger.error("No data available for the requested page and size!");
            throw new BadRequestException("No data available for the requested page and size!");
        }

        return title.stream()
                .map(MovieMapper.mapToDto).toList();
    }

    @Transactional
    public void saveBatch(List<MovieDto> movies) {
        movies.stream()
                .filter(movieDto -> !movieRepository.existsByTitle(movieDto.getTitle()))
                .map(MovieMapper.mapToModel)
                .forEach(movieRepository::save);
        logger.info("Movies was saved in batch");
    }

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public Movie findModelById(Long movieId) {
        Optional<Movie> movie = movieRepository.findById(movieId);
        if (movie.isEmpty()) {
            logger.error("Movie with current id {} was not found!", movieId);
            throw new NotFoundException("Movie with current id (" + movieId + ") was not found!");
        }

        return movie.get();
    }
}
