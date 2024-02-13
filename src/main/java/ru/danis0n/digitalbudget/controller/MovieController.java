package ru.danis0n.digitalbudget.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.danis0n.digitalbudget.dto.movie.MovieDto;
import ru.danis0n.digitalbudget.service.MovieService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/movie")
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<List<MovieDto>> findMovies(
            @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(value = "limit", defaultValue = "15", required = false) Integer limit) {
        return ResponseEntity.ok(movieService.findMovies(page, limit));
    }

}
