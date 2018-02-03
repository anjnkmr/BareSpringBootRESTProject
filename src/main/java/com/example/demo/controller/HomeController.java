package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.repository.IUserRepository;
import com.example.demo.response.BaseResponse;
import com.example.demo.response.UserListResponse;
import com.example.demo.response.sub.UserListItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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

}
