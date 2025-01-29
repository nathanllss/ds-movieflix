package com.devsuperior.movieflix.controllers;


import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.mappers.MovieMapper;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
        Long id = Long.parseLong(genreId);
        Page<MovieCardDTO> result = repository.searchMoviesByGenre(id, pageable).map(x -> MovieMapper.entityToCardDTO(x, new MovieCardDTO()));
        List<MovieCardDTO> entities = repository.searchMoviesWithGenre(id).stream().map(x -> MovieMapper.entityToCardDTO(x, new MovieCardDTO())).toList();
        entities = replace(result.getContent(), entities);
        return ResponseEntity.ok(new PageImpl<>(entities, result.getPageable(), result.getTotalElements()));
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('VISITOR', 'MEMBER')")
    public ResponseEntity<MovieDetailsDTO> findById(@PathVariable Long id) {
        Movie entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return ResponseEntity.ok(MovieMapper.entityToDetailsDTO(entity, new MovieDetailsDTO()));
    }


    private List<MovieCardDTO> replace(List<MovieCardDTO> ordered, List<MovieCardDTO> unordered) {
        Map<Long, MovieCardDTO> map = new HashMap<>();
        for (MovieCardDTO obj : unordered) {
            map.put(obj.getId(), obj);
        }
        List<MovieCardDTO> result = new ArrayList<>();
        for (MovieCardDTO obj : ordered) {
            result.add(map.get(obj.getId()));
        }
        return result;
    }
}
