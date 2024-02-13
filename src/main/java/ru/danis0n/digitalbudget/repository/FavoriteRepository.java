package ru.danis0n.digitalbudget.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.danis0n.digitalbudget.model.Movie;
import ru.danis0n.digitalbudget.model.favorite.Favorite;
import ru.danis0n.digitalbudget.model.favorite.MovieUserKey;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, MovieUserKey> {
    @Query("SELECT m FROM Movie m WHERE m.id NOT IN (SELECT f.movie.id FROM Favorite f WHERE f.user.id = :userId)")
    List<Movie> findMoviesNotInUserFavorites(@Param("userId") Long userId, Pageable pageable);
    List<Favorite> findFavoriteByUser_Id(Long userId);
    List<Favorite> findFavoriteByUser_Id(Long userId, Pageable pageable);

}