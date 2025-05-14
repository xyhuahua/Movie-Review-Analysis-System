package com.itheima.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie1 {
    private String _id;
    private String movieName;
    private String movieId;
    private String releaseDate;
    private String regions;
    private String movieTypes;
    private String movieScore;
    private String voteCount;
    private String movieRank;
    private String movieURL;
    private String coverURL;
    private List<Role> role;

}
