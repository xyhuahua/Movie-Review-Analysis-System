package com.itheima.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieList {
    private String movieId;
    private String movieName;
    private String movieUrl;
    private String dateTime;
    private String imageUrl;
    private ArrayList<Actor> actors;
}
