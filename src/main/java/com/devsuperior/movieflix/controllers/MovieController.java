package com.devsuperior.movieflix.controllers;


import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.mappers.MovieMapper;
import com.devsuperior.movieflix.repositories.MovieRepository;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/movies")
public class MovieController {

    @Autowired
    private MovieRepository repository;

    @GetMapping
    @PreAuthorize("hasAnyRole('VISITOR', 'MEMBER')")
    public ResponseEntity<Page<MovieCardDTO>> findByGenre(@RequestParam(value = "genreId", defaultValue = "0") String genreId,
                                                          Pageable pageable) {
        Page<MovieCardDTO> result;
        Long id = Long.parseLong(genreId);
        if (id.equals(0L)) {
            result = repository.findAll(pageable).map(x -> MovieMapper.entityToCardDTO(x, new MovieCardDTO()));
            return ResponseEntity.ok(result);
        }
        result  = repository.searchMoviesByGenre(id, pageable).map(x -> MovieMapper.entityToCardDTO(x, new MovieCardDTO()));
        return ResponseEntity.ok(result);
    }

}
