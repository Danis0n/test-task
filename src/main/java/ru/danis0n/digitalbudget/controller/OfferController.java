package ru.danis0n.digitalbudget.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.danis0n.digitalbudget.dto.movie.MovieDto;
import ru.danis0n.digitalbudget.exception.BadRequestException;
import ru.danis0n.digitalbudget.service.offer.OfferService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/offer/secured")
@RequiredArgsConstructor
public class OfferController {

    private final Map<String, OfferService> serviceMap;

    @GetMapping
    public ResponseEntity<List<MovieDto>> findAllOffered(
            @RequestHeader("User-Id") Long userId,
            @RequestParam(value = "loaderType", defaultValue = "sql") String loaderType,
            @RequestParam(value = "limit", defaultValue = "15") Integer limit) {

        if (!serviceMap.containsKey(loaderType)) {
            throw new BadRequestException("Unknown loader type!");
        }

        return ResponseEntity.ok(serviceMap.get(loaderType).offer(userId, limit));
    }


}
