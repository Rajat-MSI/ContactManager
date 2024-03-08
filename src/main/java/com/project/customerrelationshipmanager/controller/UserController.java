package com.project.customerrelationshipmanager.controller;

import com.project.customerrelationshipmanager.model.User;
import com.project.customerrelationshipmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
//@RequestMapping("/user")
public class UserController
{
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user_dashboard")
    public String userDashboard() {
        System.out.println("This is user dashboard");
        return "user_dashboard";
    }

    @GetMapping("/user_detail")
    @ResponseBody
    public String userDetail() {

        User user = userRepository.getUserByUserEmail("simpi123@gmail.com");
        System.out.println(user);
        return "user_detail";
    }
}
