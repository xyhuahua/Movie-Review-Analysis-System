package com.itheima.service;

import com.itheima.domain.RoleRank;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface RoleService {


    public List<RoleRank> getAllRoleRank();
}
