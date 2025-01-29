package com.devsuperior.movieflix.repositories;

import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.projections.MovieCardProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @SuppressWarnings("SqlNoDataSourceInspection")
    @Query(nativeQuery = true, value = """
                    SELECT * FROM (
                    SELECT m.* FROM tb_movie m
                    INNER JOIN tb_genre g ON m.genre_id = g.id
                    WHERE (:genreId = 0 OR g.id = :genreId)
                    ORDER BY m.title
                    ) AS tb_result
            """, countQuery = """
                    SELECT COUNT(*) FROM (
                    SELECT m.* FROM tb_movie m
                    INNER JOIN tb_genre g ON m.genre_id = g.id
                    WHERE (:genreId = 0 OR g.id = :genreId)
                    ) AS tb_result
            """)
    Page<MovieCardProjection> searchMoviesByGenre(Long genreId, Pageable pageable);

    @Query(value = "SELECT obj FROM Movie obj JOIN FETCH obj.genre WHERE (:genreId = 0 OR obj.genre.id = :genreId)")
    List<Movie> searchMoviesWithGenre(Long genreId);
}
