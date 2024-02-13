package ru.danis0n.digitalbudget.model.favorite;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class MovieUserKey implements Serializable {
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "movie_id")
    private Long movieId;

}
