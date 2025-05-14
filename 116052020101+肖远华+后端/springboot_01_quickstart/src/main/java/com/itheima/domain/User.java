package com.itheima.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String _id;
    private String userName;
    private String password;
    private int age;
    private String address;
    private String info;
    private String tel;
    private String sex;
    private String headId;
    private ArrayList<OperatorLog> operatorLog;
    private ArrayList<MovieList> movieList;
    private ArrayList<GiveScore> giveScore;
    private ArrayList<MovieSawList> movieSawList;
    private ArrayList<Guess> guess;
    private ArrayList<Like> like;
    private String _class;

    public User(String username, String password, String age, String address, String info, String sex, String tel,ArrayList<Like> like) {
        this.userName = username;
        this.password = password;
        this.age = Integer.parseInt(age);
        this.address = address;
        this.info = info;
        this.sex = sex;
        this.tel = tel;
        this.like = like;
    }
}
