package com.humanup.matrix.management.api.gatway.bs.impl;

import com.humanup.matrix.management.api.gatway.bs.AccountBS;
import com.humanup.matrix.management.api.gatway.dao.AccountDAO;
import com.humanup.matrix.management.api.gatway.dao.RoleDAO;
import com.humanup.matrix.management.api.gatway.dao.entities.Account;
import com.humanup.matrix.management.api.gatway.dao.entities.Role;
import com.humanup.matrix.management.api.gatway.vo.AccountVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountBSImpl implements AccountBS {

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private RoleDAO roleDAO;

    @Override
    public boolean createAccount(AccountVO accountVO) {
        Role role = roleDAO.findByRoleTitle(accountVO.getAccountRole());
        Account accountToSave = Account.builder()
                .accountMailAdresse(accountVO.getAccountMailAdresse())
                .accountFirstName(accountVO.getAccountFirstName())
                .accountLastName(accountVO.getAccountLastName())
                .role(role)
                .build();
        return  accountDAO.save(accountToSave)!=null;
    }

    @Override
    public AccountVO findAccountByLastName(String accountLastName) {
        Optional<Account> accountFinded = Optional.ofNullable(accountDAO.findByAccountLastName(accountLastName));
        if(accountFinded.isPresent()) {
            return  AccountVO.builder()
                    .accountMailAdresse(accountFinded.get().getAccountMailAdresse())
                    .accountFirstName(accountFinded.get().getAccountFirstName())
                    .accountLastName(accountFinded.get().getAccountLastName())
                    .accountRole(accountFinded.get().getRole().getRoleTitle())
                    .build();
        }
        return null;
    }

    @Override
    public AccountVO findAccountByMailAdresse(String accountMailAdresse) {
        Optional<Account> accountFinded = Optional.ofNullable(accountDAO.findByAccountMailAdresse(accountMailAdresse));
        if(accountFinded.isPresent()) {
            return  AccountVO.builder()
                    .accountMailAdresse(accountFinded.get().getAccountMailAdresse())
                    .accountFirstName(accountFinded.get().getAccountFirstName())
                    .accountLastName(accountFinded.get().getAccountLastName())
                    .accountRole(accountFinded.get().getRole().getRoleTitle())
                    .build();
        }
        return null;
    }

    @Override
    public List<AccountVO> findListAccount() {
        return accountDAO.findAll()
                .stream()
                .map(accountFinded ->  AccountVO.builder()
                        .accountMailAdresse(accountFinded.getAccountMailAdresse())
                        .accountFirstName(accountFinded.getAccountFirstName())
                        .accountLastName(accountFinded.getAccountLastName())
                        .accountRole(accountFinded.getRole().getRoleTitle())
                        .build())
                .collect(Collectors.toList());

    }

    @Override
    public List<AccountVO> findListRolesByRoleTitle(String roleTitle) {
        return accountDAO.findByListRolesRoleTitle(roleTitle)
                .stream()
                .map(accountFinded ->  AccountVO.builder()
                        .accountMailAdresse(accountFinded.getAccountMailAdresse())
                        .accountFirstName(accountFinded.getAccountFirstName())
                        .accountLastName(accountFinded.getAccountLastName())
                        .accountRole(accountFinded.getRole().getRoleTitle())
                        .build())
                .collect(Collectors.toList());
    }



}
