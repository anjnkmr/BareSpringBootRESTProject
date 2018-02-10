package com.example.demo.security;

import com.example.demo.entity.User;
import com.example.demo.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;

@Configuration
public class CustomAuthenticationManager implements AuthenticationManager {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        AuthenticationException exception = new BadCredentialsException("Invalid Login Details");
        if (authentication == null) throw exception;
        String username = authentication.getName();
        if (username == null) throw exception;
        if (authentication.getCredentials() == null) throw exception;
        String password = authentication.getCredentials().toString();
        if (password == null) throw exception;
        System.out.println("Username: "+username);
        System.out.println("Password: "+password);
        User user = userRepository.findByName(username);
        System.out.println("User: ");
        System.out.println(user+"");
        if (user == null) throw exception;
        System.out.println("DBPassword: "+user.getPassword());
        if (!user.getPassword().equalsIgnoreCase(password)) throw exception;
        ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("USER"));
        Authentication authenticationNew = new UsernamePasswordAuthenticationToken(username, password, grantedAuthorities);
        return authenticationNew;
    }
}
