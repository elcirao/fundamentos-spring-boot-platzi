package com.fundamentos.platzi.fundamentos.controller;

import com.fundamentos.platzi.fundamentos.caseuse.CreateUser;
import com.fundamentos.platzi.fundamentos.caseuse.DeleteUser;
import com.fundamentos.platzi.fundamentos.caseuse.GetUser;
import com.fundamentos.platzi.fundamentos.caseuse.UpdateUSer;
import com.fundamentos.platzi.fundamentos.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
    //create, get, delete, update
    private GetUser getUser;
    private CreateUser createUser;
    private DeleteUser deleteUser;
    private UpdateUSer updateUSer;

    public UserRestController(GetUser getUser, CreateUser createUser, DeleteUser deleteUser, UpdateUSer updateUSer) {
        this.createUser = createUser;
        this.getUser = getUser;
        this.deleteUser = deleteUser;
        this.updateUSer = updateUSer;
    }

    @GetMapping("/")
    List<User> getAll() {
        return getUser.getAll();
    }

    @PostMapping("/")
    ResponseEntity<User> newUser(@RequestBody User user) {
        return new ResponseEntity<>(createUser.save(user), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteUser(@PathVariable Long id) {
        deleteUser.remove(id);
    }

    @PutMapping("/{id}")
    ResponseEntity<User> replaceUser(@RequestBody User user, @PathVariable Long id) {
        return new ResponseEntity<>(updateUSer.update(user, id), HttpStatus.OK);
    }
}
