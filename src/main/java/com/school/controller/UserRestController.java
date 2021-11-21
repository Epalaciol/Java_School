package com.school.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.school.dto.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserRestController {

    @PostMapping("token")
    public User login(@RequestParam("user") String username, @RequestParam("password") String pwd) {

        User user = new User();
        user.setUsername(username);
        return validateUser(user);

    }

    private String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1200000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return token;
    }


    private User validateUser(User loggedUser) {

        if(loggedUser.getUsername().equalsIgnoreCase("admin")) {
            String token = getJWTToken(loggedUser.getUsername());
            loggedUser.setToken(token);
        }
        return loggedUser;
    }

}