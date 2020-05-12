package com.humanup.matrix.management.api.gatway.bs;

import com.humanup.matrix.management.api.gatway.aop.dto.RoleException;
import com.humanup.matrix.management.api.gatway.vo.RoleVO;

import java.util.List;

public interface RoleBS {
    boolean createRole(RoleVO role) throws RoleException;
    RoleVO findByRoleTitle(String roleTitle);
    List<RoleVO> findListRole();
    List<RoleVO> findListAuthorizationsByAuthorizationsTitle(String authorizationsTitle);
}