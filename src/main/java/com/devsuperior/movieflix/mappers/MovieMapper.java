package com.devsuperior.movieflix.mappers;

import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.dto.MovieDetailsDTO;
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
        //dto.setGenre(GenreMapper.entityToDTO(entity.getGenre()));
        return dto;
    }

}
