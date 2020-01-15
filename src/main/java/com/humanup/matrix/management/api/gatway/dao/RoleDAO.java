package com.humanup.matrix.management.api.gatway.dao;

import com.humanup.matrix.management.api.gatway.dao.entities.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleDAO extends CrudRepository<Role, Long> {

    Role findByRoleTitle(String roleTitle);
    List<Role> findAll();
    Role findByRoleId(long roleId);
    @Query("SELECT r FROM Role r WHERE lower(r.roleTitle) like %:roleTitle% ")
    List<Role> findListRolesByTitle(String roleTitle);

}