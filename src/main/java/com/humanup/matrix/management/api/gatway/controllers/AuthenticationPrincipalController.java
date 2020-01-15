package com.humanup.matrix.management.api.gatway.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class AuthenticationPrincipalController {
    @RequestMapping(value="/token/my-account", method= RequestMethod.GET)
    String helloUser(HttpSession session) {
        return session.getId() +":"+session.getCreationTime();
    }
}
