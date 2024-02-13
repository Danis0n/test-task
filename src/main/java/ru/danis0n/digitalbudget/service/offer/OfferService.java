package ru.danis0n.digitalbudget.service.offer;

import ru.danis0n.digitalbudget.dto.movie.MovieDto;

import java.util.List;

public interface OfferService {

    List<MovieDto> offer(Long userId, Integer limit);

}
