package com.humanup.matrix.management.api.gatway.bs.impl;

import com.humanup.matrix.management.api.gatway.bs.UserService;
import com.humanup.matrix.management.api.gatway.dao.AccountDAO;
import com.humanup.matrix.management.api.gatway.dao.entities.Account;
import com.humanup.matrix.management.api.gatway.vo.AccountVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {
    @Autowired
    private AccountDAO userDao;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public Account save(AccountVO user) {
        Account newUser = Account.builder()
        .accountMailAdresse(user.getAccountMailAdresse())
        .password(passwordEncoder.encode(user.getPassword()))
        .accountFirstName(user.getAccountFirstName())
        .accountLastName(user.getAccountLastName())
                .build();
        return userDao.save(newUser);
    }

    @Override
    public Account findOne(String accountMailAdresse) {
        return userDao.findByAccountMailAdresse(accountMailAdresse);
    }

    public UserDetails loadUserByUsername(String userId) throws
            UsernameNotFoundException {
        Account user = userDao.findByAccountMailAdresse(userId);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getAccountMailAdresse(), user.getPassword(), getAuthority(user));
    }

    private Collection<? extends GrantedAuthority> getAuthority(Account account) {
        return AuthorityUtils.createAuthorityList(account.getRole().getPathAllowed());
    }
}
