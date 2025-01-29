package com.devsuperior.movieflix.controllers;

import com.devsuperior.movieflix.dto.GenreDTO;
import com.devsuperior.movieflix.mappers.GenreMapper;
import com.devsuperior.movieflix.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/genres")
public class GenreController {

    @Autowired
    private GenreRepository repository;

    @GetMapping
    @PreAuthorize("hasAnyRole('VISITOR', 'MEMBER')")
    public ResponseEntity<List<GenreDTO>> findAll() {
        List<GenreDTO> list = repository.findAll()
                .stream().map(x -> GenreMapper.entityToDTO(x, new GenreDTO())).toList();
        return ResponseEntity.ok(list);
    }
}
