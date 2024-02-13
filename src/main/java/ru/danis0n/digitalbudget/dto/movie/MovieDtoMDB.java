package ru.danis0n.digitalbudget.dto.movie;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class MovieDtoMDB {
    private List<MovieDto> results;
}