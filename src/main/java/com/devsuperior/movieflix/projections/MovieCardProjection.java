package com.devsuperior.movieflix.projections;

public interface MovieCardProjection extends IdProjection<Long> {

    Long getId();
    String getTitle();
    String getSubTitle();
    Integer getYear();
    String getImgUrl();
}
