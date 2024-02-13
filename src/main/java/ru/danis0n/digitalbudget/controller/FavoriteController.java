package ru.danis0n.digitalbudget.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.danis0n.digitalbudget.dto.movie.MovieDto;
import ru.danis0n.digitalbudget.service.FavoriteService;

import java.util.List;

@RestController
@RequestMapping("api/favorite/secured")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;

    @GetMapping
    public ResponseEntity<List<MovieDto>> findAllFavorites(@RequestHeader("User-Id") Long userId) {
        return ResponseEntity.ok(favoriteService.findAllFavorites(userId));
    }

    @PostMapping
    public ResponseEntity<Boolean> addToFavorites(@RequestHeader("User-Id") Long userId,
                                                  @RequestParam("movieId") Long movieId) {
        return ResponseEntity.ok(favoriteService.addToFavorites(userId, movieId));
    }

    @DeleteMapping
    public ResponseEntity<Boolean> removeFromFavorites(@RequestHeader("User-Id") Long userId,
                                                       @RequestParam("movieId") Long movieId) {
        return ResponseEntity.ok(favoriteService.removeFromFavorites(userId, movieId));
    }

}
