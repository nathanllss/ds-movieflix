package com.devsuperior.movieflix.mappers;

import com.devsuperior.movieflix.dto.GenreDTO;
import com.devsuperior.movieflix.entities.Genre;

public class GenreMapper {

    public static GenreDTO entityToDTO(Genre entity, GenreDTO dto) {
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }
}
