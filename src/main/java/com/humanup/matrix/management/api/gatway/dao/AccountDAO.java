package com.humanup.matrix.management.api.gatway.dao;

import com.humanup.matrix.management.api.gatway.dao.entities.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountDAO extends CrudRepository<Account, Long> {
    Account findByAccountLastName(String accountLastName);
    List<Account> findAll();
    Account findByAccountMailAdresse(String accountMailAdresse);
    @Query("SELECT a FROM Account a WHERE lower(a.role.roleTitle) like %:roleTitle% ")
    List<Account> findByListRolesRoleTitle(String roleTitle);
    
}
