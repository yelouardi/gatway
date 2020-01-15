package com.humanup.matrix.management.api.gatway.controllers;

import com.humanup.matrix.management.api.gatway.bs.UserService;
import com.humanup.matrix.management.api.gatway.configuration.JwtTokenUtil;
import com.humanup.matrix.management.api.gatway.dao.entities.Account;
import com.humanup.matrix.management.api.gatway.vo.AccountVO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserService userService;
    @Operation(summary = "Get Token", description = " Get  Token by email...", tags = { "account" })
    @RequestMapping(value = "/generate-token", method = RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody AccountVO loginUser)
            throws AuthenticationException {
        try {
            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginUser.getAccountMailAdresse(),
                            loginUser.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }catch(Exception e){

        }
        final Account user = userService.findOne(loginUser.getAccountMailAdresse());
        final String token = jwtTokenUtil.doGenerateToken(user.getAccountMailAdresse());
        return ResponseEntity.ok(token);
    }
}
