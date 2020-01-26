package com.humanup.matrix.management.api.gatway.controllers;

import com.humanup.matrix.management.api.gatway.bs.UserService;
import com.humanup.matrix.management.api.gatway.configuration.JwtTokenUtil;
import com.humanup.matrix.management.api.gatway.dao.entities.Account;
import com.humanup.matrix.management.api.gatway.vo.LoginVO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

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
    public ResponseEntity<?> generateToken(@RequestBody LoginVO loginUser, HttpSession session)
            throws AuthenticationException {
        try {
            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginUser.getEmail(),
                            loginUser.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }catch(Exception e){

        }
        final Account user = userService.findOne(loginUser.getEmail());
        session.setAttribute("userID",user.toString());
        final String token = jwtTokenUtil.doGenerateToken(user.getAccountMailAdresse());
        return ResponseEntity.ok(token);
    }
    @RequestMapping(value="/my-account", method= RequestMethod.GET)
    String helloUser(HttpSession session) {
        return session.getId() +":"+session.getAttribute("userID");
    }
}
