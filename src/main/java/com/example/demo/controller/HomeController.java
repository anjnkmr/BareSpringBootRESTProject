package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.repository.IUserRepository;
import com.example.demo.response.BaseResponse;
import com.example.demo.response.UserListResponse;
import com.example.demo.response.sub.UserListItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class HomeController {

    @Autowired
    private IUserRepository userRepository;

    @GetMapping
    public @ResponseBody BaseResponse index() {
        BaseResponse response = new BaseResponse();
        response.setStatus(1);
        response.setMessage("Hello World");
        return response;
    }

    @GetMapping("/register")
    public @ResponseBody BaseResponse register (@RequestParam("name") String name, @RequestParam("password") String password) {
        BaseResponse response = new BaseResponse();
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        userRepository.save(user);
        response.setStatus(1);
        response.setMessage("Registered Successfully");
        return response;
    }

    @GetMapping("/list")
    public @ResponseBody UserListResponse list() {
        UserListResponse response = new UserListResponse();
        response.setStatus(1);
        response.setMessage("Hello World");
        List<User> users = userRepository.findAll();
        ArrayList<UserListItem> userListItems = new ArrayList<>();
        for (User user : users) {
            UserListItem item = new UserListItem();
            item.setAge(user.getAge());
            item.setName(user.getName());
            item.setId(user.getId());
            userListItems.add(item);
        }
        response.setUsers(userListItems);
        return response;
    }

    @GetMapping("/info")
    public @ResponseBody User info () {
        if (SecurityContextHolder.getContext() != null)
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
            User user = userRepository.findByName(userName);
            return user;
        }
        return new User();
    }

}
