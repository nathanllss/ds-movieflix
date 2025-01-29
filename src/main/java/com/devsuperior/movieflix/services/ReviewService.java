package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.mappers.MovieMapper;
import com.devsuperior.movieflix.mappers.ReviewMapper;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.repositories.UserRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private MovieService movieService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    public ReviewDTO insertReview(ReviewDTO dto) {
        MovieDetailsDTO movieDetails = movieService.findById(dto.getMovieId());
        Movie movie = MovieMapper.detailsDTOtoEntity(movieDetails, new Movie());
        User user = userRepository.findById(userService.getProfile().getId()).get();
        Review review = reviewRepository.save(ReviewMapper.dtoToEntity(dto, new Review(), movie, user));
        return ReviewMapper.entityToDTO(review, new ReviewDTO());
    }

    public Page<ReviewDTO> findReviewsByMovie(Long id, Pageable pageable) {
        movieService.findById(id);
        Page<ReviewDTO> reviews = reviewRepository.searchByMovie(id, pageable)
                .map(review -> ReviewMapper.entityToDTO(review, new ReviewDTO()));
        if (reviews.getContent().isEmpty()) throw new ResourceNotFoundException("No reviews found for this movie");
        return reviews;
    }
}
