package com.itheima.Pjf.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    private String _id;
    private String actorId;
    private String sex;
    private String occupation;
    private String imdb;
    private String actorinfo;
    private String actortouxiang;
    private String actorname;
    private ArrayList<Award> award;
    private ArrayList<String> connectmovieurls;
    private String averageScore;
    private String constellation;
    private String birthday;
    private String birthplace;
    private String foreignname;
    private String chinaname;
    private String family;
    private String birthtodead;
}
