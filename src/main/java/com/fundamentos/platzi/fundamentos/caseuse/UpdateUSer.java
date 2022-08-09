package com.fundamentos.platzi.fundamentos.caseuse;

import com.fundamentos.platzi.fundamentos.entity.User;
import com.fundamentos.platzi.fundamentos.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class UpdateUSer {

    private UserService userService;

    public UpdateUSer(UserService userService) {
        this.userService = userService;
    }

    public User update(User user, Long id) {
        return userService.update(user, id);
    }
}
