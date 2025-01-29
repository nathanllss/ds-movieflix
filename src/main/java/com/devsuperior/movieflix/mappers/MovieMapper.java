package com.devsuperior.movieflix.mappers;

import com.devsuperior.movieflix.dto.GenreDTO;
import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;

public class MovieMapper {

    public static MovieCardDTO entityToCardDTO(Movie entity, MovieCardDTO dto) {
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setSubTitle(entity.getSubTitle());
        dto.setYear(entity.getYear());
        dto.setImgUrl(entity.getImgUrl());
        return dto;
    }

    public static MovieDetailsDTO entityToDetailsDTO(Movie entity, MovieDetailsDTO dto) {
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setSubTitle(entity.getSubTitle());
        dto.setYear(entity.getYear());
        dto.setImgUrl(entity.getImgUrl());
        dto.setSynopsis(entity.getSynopsis());
        dto.setGenre(GenreMapper.entityToDTO(entity.getGenre(), new GenreDTO()));
        return dto;
    }

    public static Movie detailsDTOtoEntity(MovieDetailsDTO dto, Movie entity) {
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setSubTitle(dto.getSubTitle());
        entity.setYear(dto.getYear());
        entity.setImgUrl(dto.getImgUrl());
        entity.setSynopsis(dto.getSynopsis());
        entity.setGenre(GenreMapper.dtoToEntity(dto.getGenre(), new Genre()));
        return entity;
    }

}
