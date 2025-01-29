package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.mappers.MovieMapper;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import com.devsuperior.movieflix.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public Page<MovieCardDTO> findByGenre(String genreId, Pageable pageable) {
        Long id = Long.parseLong(genreId);
        Page<MovieCardDTO> pageResult = movieRepository.searchMoviesByGenre(id, pageable)
                .map(x -> MovieMapper.entityToCardDTO(x, new MovieCardDTO()));
        List<MovieCardDTO> entities = movieRepository.searchMoviesWithGenre(id)
                .stream().map(x -> MovieMapper.entityToCardDTO(x, new MovieCardDTO())).toList();
        entities = Util.replace(pageResult.getContent(), entities);
        return new PageImpl<>(entities, pageResult.getPageable(), pageResult.getTotalElements());
    }

    public MovieDetailsDTO findById(Long id) {
        Movie entity = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found"));
        return MovieMapper.entityToDetailsDTO(entity, new MovieDetailsDTO());
    }
}
