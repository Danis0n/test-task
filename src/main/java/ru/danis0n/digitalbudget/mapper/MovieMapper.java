package ru.danis0n.digitalbudget.mapper;

import ru.danis0n.digitalbudget.dto.movie.MovieDto;
import ru.danis0n.digitalbudget.model.Movie;

import java.util.function.Function;

public class MovieMapper {

    public static Function<Movie, MovieDto> mapToDto =
            movie -> MovieDto.builder()
                    .id(movie.getId())
                    .title(movie.getTitle())
                    .posterPath(movie.getPosterPath())
                    .build();

    public static Function<MovieDto, Movie> mapToModel =
            movieDto -> {
                Movie movie = new Movie();
                movie.setTitle(movieDto.getTitle());
                movie.setPosterPath(movieDto.getPosterPath());
                return movie;
            };

}
