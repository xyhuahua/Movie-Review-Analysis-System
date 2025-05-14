package com.itheima.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    @Id
    private String _id;
    private String title;
    private String movieId;
    private ArrayList<Actor> actors;
    private String release_date;
    private ArrayList<String> types;
    private ArrayList<String> regions;
    private String score;
    private String vote_count;
    private String rank;
    private String description;
    private String url;
    private String cover_url;
    private String language;
    private String pianchang;
    private String IMDB;
    private ArrayList<ciyun> ciyun;
    private ArrayList<Iprate> iprate;
    private ArrayList<Ipcount> ipcount;
    private ArrayList<EmotionAnalyse> emotionanalyse;

}
