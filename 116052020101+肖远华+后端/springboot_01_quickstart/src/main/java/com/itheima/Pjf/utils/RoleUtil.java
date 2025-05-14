package com.itheima.Pjf.utils;

import com.itheima.Pjf.domain.Role;

import java.util.ArrayList;
import java.util.List;

public class RoleUtil {
    public static List<String> getMovieId(Role role){
        List<String> list = new ArrayList<>();
        ArrayList<String> connectmovieurls = role.getConnectmovieurls();
        for (String s :connectmovieurls) {
            list.add(MovieUtil.getMovieId(s));
        }
        return list;
    }
}
