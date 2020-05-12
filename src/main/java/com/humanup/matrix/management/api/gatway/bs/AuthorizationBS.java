package com.humanup.matrix.management.api.gatway.bs;

import com.humanup.matrix.management.api.gatway.aop.dto.AuthorizationException;
import com.humanup.matrix.management.api.gatway.vo.AuthorizationVO;

import java.util.List;

public interface AuthorizationBS {

    boolean createAuthorization(AuthorizationVO authorization) throws AuthorizationException;
    AuthorizationVO findAuthorizationByTitle(String authorizationTitle);
    List<AuthorizationVO> findListAuthorization();
    List<AuthorizationVO> findListAuthorizationsByTitle(String authorizationTitle);
    List<AuthorizationVO> findAutorisationsByAccountEmail(String accountMailAdresse);


}
