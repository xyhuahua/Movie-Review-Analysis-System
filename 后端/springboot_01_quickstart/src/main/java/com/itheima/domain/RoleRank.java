package com.itheima.domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleRank implements Serializable {
    private String _id;
    private String listid;
    private String roleid;
    private String rolename;
    private String series;
    private String actor;

}
