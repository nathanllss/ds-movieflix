package com.devsuperior.movieflix.util;

import com.devsuperior.movieflix.dto.MovieCardDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Util {

    public static List<MovieCardDTO> replace(List<MovieCardDTO> ordered, List<MovieCardDTO> unordered) {
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
