package com.project.customerrelationshipmanager.controller;

import com.project.customerrelationshipmanager.model.User;
import com.project.customerrelationshipmanager.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping(path = "/home")
    public String home(Model model) {
        model.addAttribute("title", "Home - TheHuddle");
        return "home";
    }

    @GetMapping(path = "/about")
    public String about(Model model) {
        model.addAttribute("title", "About - TheHuddle");
        return "about";
    }

    @GetMapping(path = "/signup")
    public String signup(Model model) {
        model.addAttribute("title", "Signup - TheHuddle");
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping(path = "/process_signup")
    public String processSignup(@Valid @ModelAttribute("user") User user,
                                BindingResult bindingResult,
                                @RequestParam("userPassword") String userPassword,
                                Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println("error block");

            return "signup";
        }
        user.setUserRole("USER");
        user.setUserImageUrl("default.png");
        user.setUserPassword(bCryptPasswordEncoder.encode(userPassword));
        System.out.println(user);
        userRepository.save(user);
        return "redirect:/home";
    }

    @GetMapping("/login")
    public String login(Model model)
    {
        model.addAttribute("title","Login- TheHuddle");
        return "login";
    }

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        User user = new User();
        user.setUserName("Rajat Sharma");
        user.setUserEmail("rajatsharma@gmail.com");
        userRepository.save(user);
        return "Working";
    }
}
