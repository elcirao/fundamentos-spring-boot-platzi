package com.fundamentos.platzi.fundamentos.caseuse;

import com.fundamentos.platzi.fundamentos.entity.User;
import com.fundamentos.platzi.fundamentos.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class CreateUser {

    private UserService userService;

    public CreateUser(UserService userService) {
        this.userService = userService;
    }

    public User save(User user) {
        return userService.save(user);
    }
}
