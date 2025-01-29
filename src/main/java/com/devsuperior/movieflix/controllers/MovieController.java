package com.devsuperior.movieflix.controllers;


import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.mappers.MovieMapper;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.MovieService;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    @PreAuthorize("hasAnyRole('VISITOR', 'MEMBER')")
    public ResponseEntity<Page<MovieCardDTO>> findByGenre(@RequestParam(value = "genreId", defaultValue = "0") String genreId,
                                                          Pageable pageable) {
        Page<MovieCardDTO> result = movieService.findByGenre(genreId, pageable);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('VISITOR', 'MEMBER')")
    public ResponseEntity<MovieDetailsDTO> findById(@PathVariable Long id) {
        MovieDetailsDTO result = movieService.findById(id);
        return ResponseEntity.ok(result);
    }

}
