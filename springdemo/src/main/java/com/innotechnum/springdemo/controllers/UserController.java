package com.innotechnum.springdemo.controllers;

import com.innotechnum.springdemo.entities.Role;
import com.innotechnum.springdemo.entities.User;
import com.innotechnum.springdemo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping
    public String userList(Model model){
        model.addAttribute("users",userRepo.findAll());
        return "userList";
    }

    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user){

        user.setUserName(username);

       Set<String> roles = Arrays.stream(Role.values()).map(Role::name).collect(Collectors.toSet());

        userRepo.save(user);

        return "redirect:/user";

    }
}
