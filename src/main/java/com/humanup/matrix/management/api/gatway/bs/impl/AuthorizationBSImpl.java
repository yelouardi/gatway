package com.humanup.matrix.management.api.gatway.bs.impl;


import com.humanup.matrix.management.api.gatway.aop.dto.AccountException;
import com.humanup.matrix.management.api.gatway.aop.dto.AuthorizationException;
import com.humanup.matrix.management.api.gatway.bs.AuthorizationBS;
import com.humanup.matrix.management.api.gatway.bs.impl.sender.RabbitMQAuthorizationSender;
import com.humanup.matrix.management.api.gatway.dao.AccountDAO;
import com.humanup.matrix.management.api.gatway.dao.AuthorizationDAO;
import com.humanup.matrix.management.api.gatway.dao.RoleDAO;
import com.humanup.matrix.management.api.gatway.dao.entities.Account;
import com.humanup.matrix.management.api.gatway.dao.entities.Authorization;
import com.humanup.matrix.management.api.gatway.dao.entities.Role;
import com.humanup.matrix.management.api.gatway.vo.AuthorizationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional(readOnly = true)
public class AuthorizationBSImpl implements AuthorizationBS {


    @Autowired
    private AuthorizationDAO authorizationDAO;

    @Autowired
    private RabbitMQAuthorizationSender rabbitMQAuthorizationSender;

    @Autowired
    private AccountDAO accountDAO;

    @Transactional(
            transactionManager = "transactionManagerWrite",
            rollbackFor = AuthorizationException.class)
    @Override
    public boolean createAuthorization(AuthorizationVO authorization) throws AuthorizationException {
        if (null == authorization) throw new AuthorizationException();
        rabbitMQAuthorizationSender.send(authorization);
        return true;
    }

    @Override
    public AuthorizationVO findAuthorizationByTitle(String authorizationTitle) {
        Optional<Authorization> authorizationFinded = Optional.ofNullable(authorizationDAO.findByAuthorizationTitle(authorizationTitle));
        if(authorizationFinded.isPresent()) {
            return  AuthorizationVO.builder()
                    .authorizationTitle(authorizationFinded.get().getAuthorizationTitle())
                    .authorizationDescription(authorizationFinded.get().getAuthorizationDescription())
                    .authorizationUrl(authorizationFinded.get().getAuthorizationUrl())
                    .authorizationRole(authorizationFinded.get().getRole().getRoleTitle())
                    .build();
        }
        return null;
    }

    @Override
    public List<AuthorizationVO> findListAuthorization() {
        return authorizationDAO.findAll()
                .stream()
                .map(authorizationFinded ->  AuthorizationVO.builder()
                        .authorizationTitle(authorizationFinded.getAuthorizationTitle())
                        .authorizationDescription(authorizationFinded.getAuthorizationDescription())
                        .authorizationUrl(authorizationFinded.getAuthorizationUrl())
                        .authorizationRole(authorizationFinded.getRole().getRoleTitle())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<AuthorizationVO> findListAuthorizationsByTitle(String authorizationTitle) {
        return authorizationDAO.findListAuthorizationsByTitle(authorizationTitle)
                .stream()
                .map(authorizationFinded ->  AuthorizationVO.builder()
                        .authorizationTitle(authorizationFinded.getAuthorizationTitle())
                        .authorizationDescription(authorizationFinded.getAuthorizationDescription())
                        .authorizationUrl(authorizationFinded.getAuthorizationUrl())
                        .authorizationRole(authorizationFinded.getRole().getRoleTitle())
                        .build())
                .collect(Collectors.toList());
    }


    @Override
    public List<AuthorizationVO> findAutorisationsByAccountEmail(String accountMailAdresse) {

        Account account = accountDAO.findByAccountMailAdresse(accountMailAdresse);
        if(null==account) return null;
        return authorizationDAO.findAuthorizationsByRoleId(account.getRole().getRoleId())
                .stream()
                .map(authorizationFinded ->  AuthorizationVO.builder()
                        .authorizationTitle(authorizationFinded.getAuthorizationTitle())
                        .authorizationDescription(authorizationFinded.getAuthorizationDescription())
                        .authorizationUrl(authorizationFinded.getAuthorizationUrl())
                        .authorizationRole(authorizationFinded.getRole().getRoleTitle())
                        .build())
                .collect(Collectors.toList());
    }
}
