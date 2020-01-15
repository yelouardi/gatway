package com.humanup.matrix.management.api.gatway.controllers;

import com.humanup.matrix.management.api.gatway.bs.UserService;
import com.humanup.matrix.management.api.gatway.dao.entities.Account;
import com.humanup.matrix.management.api.gatway.vo.AccountVO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Operation(summary = "Create Account", description = " Create new Account by firstname, lastname ...", tags = { "account" })
    @PostMapping("/signup")
    public ResponseEntity saveUser(@RequestBody AccountVO user){
        Optional<Object> findAccount = Optional.ofNullable(userService.save(user));
        if(findAccount.isEmpty()){
            return ResponseEntity.status(HttpStatus.FOUND).body("This Account is Founded");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(findAccount.get());
    }
}