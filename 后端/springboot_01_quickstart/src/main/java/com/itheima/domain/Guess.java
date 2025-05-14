package com.itheima.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Guess {
    private String movieId;
    private String moveName;
    private String movieUrl;
    private String imageUrl;
    private String score;
}
