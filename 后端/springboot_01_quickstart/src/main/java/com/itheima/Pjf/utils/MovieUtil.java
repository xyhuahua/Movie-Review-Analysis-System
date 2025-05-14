package com.itheima.Pjf.utils;

import com.itheima.domain.Movie;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieUtil {
    public static List<Map> RegionMap(List<Movie> list){
        List<Map> list1 = new ArrayList<>();
        Map<String,Integer> map = new HashMap<>();
        for (Movie movie : list) {
            String s = movie.getRegions().get(0);
            if(map.get(s) == null){
                map.put(s,1);
            }else {
                int num = map.get(s) + 1 ;
                map.put(s,num);
            }
        }
        for (Map.Entry entry: map.entrySet()) {
            Map<String,Object> map2 = new HashMap<>();
            map2.put("value", entry.getValue());
            map2.put("name",entry.getKey());
            list1.add(map2);
        }
        return list1;
    }

    public static List<Map> scoreMap(List<Movie> list){
        List<Map> list1 = new ArrayList<>();
        Map<String,Integer> map = new HashMap<>();
        for (Movie movie : list) {
            String s = movie.getScore();
            if(map.get(s) == null){
                map.put(s,1);
            }else {
                int num = map.get(s) + 1 ;
                map.put(s,num);
            }
        }
        for (Map.Entry entry: map.entrySet()) {
            Map<String,Object> map2 = new HashMap<>();
            map2.put("num", entry.getValue());
            map2.put("score",entry.getKey());
            list1.add(map2);
        }
        return list1;
    }

    public static String getMovieId(String url){
        int last =url.lastIndexOf("/");
        int lastSecond = url.substring(0,last).lastIndexOf("/");
        return url.substring(lastSecond + 1,last);
    }

}
