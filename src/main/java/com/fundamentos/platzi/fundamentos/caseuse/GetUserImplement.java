package com.fundamentos.platzi.fundamentos.caseuse;

import com.fundamentos.platzi.fundamentos.entity.User;
import com.fundamentos.platzi.fundamentos.service.UserService;

import java.util.List;

public class GetUserImplement implements GetUser {
    private UserService userService;

    public GetUserImplement(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
