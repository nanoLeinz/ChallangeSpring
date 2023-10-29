package id.nano.challangespring.controller;

import id.nano.challangespring.entity.Merchant;
import id.nano.challangespring.entity.User;
import id.nano.challangespring.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UserController {
    @Autowired
    public UserService userService;

    @PostMapping(value = {"/user", "/user/"})
    public ResponseEntity<Object> saveUser(@RequestBody @Valid User request) {
        return userService.insert(request);
    }
    @PutMapping(value = {"/user", "/user/"})
    public ResponseEntity<Object> updateUser(@RequestBody @Valid User request) {
        return userService.update(request);
    }

    @GetMapping(value = {"/user/{id}"})
    public ResponseEntity<Object> getMerchant(@PathVariable("id") UUID id) {
        return userService.getById(id);
    }

    @DeleteMapping(value = {"/user/{id}"})
    public ResponseEntity<Object> deleteMerchant(@PathVariable("id") UUID id) {
        return userService.delete(id);
    }
}
