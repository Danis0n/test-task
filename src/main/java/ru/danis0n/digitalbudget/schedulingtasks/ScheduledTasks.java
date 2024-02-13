package ru.danis0n.digitalbudget.schedulingtasks;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.danis0n.digitalbudget.client.MovieDiscoverClient;
import ru.danis0n.digitalbudget.dto.movie.MovieDto;
import ru.danis0n.digitalbudget.service.MovieService;

import java.util.List;
import java.util.Random;

@Slf4j
@Component
@AllArgsConstructor
public class ScheduledTasks {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

    private final MovieService movieService;
    private final MovieDiscoverClient client;
    private final Integer moviePages = 500;

    @Scheduled(cron = "${scheduled-tasks.fetch-movies}")
    public void scheduleTransferOutbox() {
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            int rand = random.nextInt(moviePages) + 1;
            List<MovieDto> movies = client.fetchMovies(rand);
            logger.info("Scheduler fetched {} page of data", i);

            movieService.saveBatch(movies);
        }
    }
}
