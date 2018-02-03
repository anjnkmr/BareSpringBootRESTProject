package com.example.demo.response;

import com.example.demo.response.sub.UserListItem;

import java.util.List;

public class UserListResponse extends BaseResponse {
    private List<UserListItem> users;

    public List<UserListItem> getUsers() {
        return users;
    }

    public void setUsers(List<UserListItem> users) {
        this.users = users;
    }
}
