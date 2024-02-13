package ru.danis0n.digitalbudget.model.favorite;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import ru.danis0n.digitalbudget.model.Movie;
import ru.danis0n.digitalbudget.model.User;

import java.sql.Timestamp;

@Entity
@Table(name = "favorite")
@Getter
@Setter
public class Favorite {

    @EmbeddedId
    private MovieUserKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("movieId")
    @JoinColumn(name = "movie_id")
    private Movie movie;

    private Timestamp date;

}