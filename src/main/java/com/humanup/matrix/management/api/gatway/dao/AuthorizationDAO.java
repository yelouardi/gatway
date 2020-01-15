package com.humanup.matrix.management.api.gatway.dao;

import com.humanup.matrix.management.api.gatway.dao.entities.Authorization;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorizationDAO extends CrudRepository<Authorization, Long> {

    Authorization findByAuthorizationTitle(String authorizationTitle);
    List<Authorization> findAll();
    Authorization findByauthorizationId(long authorizationId);
    @Query("SELECT a FROM Authorization a WHERE lower(a.authorizationTitle) like %:authorizationTitle% ")
    List<Authorization> findListAuthorizationsByTitle(String authorizationTitle);
    @Query("SELECT a FROM Authorization a WHERE a.role.roleId = :roleId ")
    List<Authorization> findAuthorizationsByRoleId(Long roleId);
}
