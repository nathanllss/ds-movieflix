package com.devsuperior.movieflix.mappers;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;

public class ReviewMapper {

    public static Review dtoToEntity(ReviewDTO dto, Review entity, Movie movie, User user) {
        entity.setText(dto.getText());
        entity.setMovie(movie);
        entity.setUser(user);
        return entity;
    }

    public static ReviewDTO entityToDTO(Review entity, ReviewDTO dto) {
        dto.setId(entity.getId());
        dto.setText(entity.getText());
        dto.setMovieId(entity.getMovie().getId());
        dto.setUserId(entity.getUser().getId());
        dto.setUserName(entity.getUser().getName());
        dto.setUserEmail(entity.getUser().getEmail());
        return dto;
    }
}
