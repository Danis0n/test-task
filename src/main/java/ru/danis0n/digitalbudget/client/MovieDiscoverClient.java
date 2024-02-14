package ru.danis0n.digitalbudget.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.danis0n.digitalbudget.dto.movie.MovieDto;
import ru.danis0n.digitalbudget.dto.movie.MovieDtoMDB;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MovieDiscoverClient {

    @Value("${movie-database.key}")
    private String API_KEY;
    @Value("${movie-database.url}")
    private String URL;

    private final RestTemplate restTemplate;
    private final static String BEARER = "Bearer ";

    public List<MovieDto> fetchMovies(int page) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, BEARER + API_KEY);
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        String url = URL + "?page=" + page;
        ResponseEntity<MovieDtoMDB> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                MovieDtoMDB.class);

        if (response.getBody() == null || response.getBody().getResults() == null) {
            return null;
        }
        return response.getBody().getResults();
    }


}
