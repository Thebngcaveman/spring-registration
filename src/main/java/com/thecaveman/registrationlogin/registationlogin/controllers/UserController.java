package com.thecaveman.registrationlogin.registationlogin.controllers;

import com.thecaveman.registrationlogin.registationlogin.models.User;
import com.thecaveman.registrationlogin.registationlogin.repository.UserRepository;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired //automatically create repository
    private UserRepository repository;

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/register")
    public String showSignUpForm(Model model){
        model.addAttribute("user", new User());
        return "sign_up";
    }

    @PostMapping("/process_register")
    public String processRegistration(User user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        repository.save(user);
        return "register_succeess";
    }

    @GetMapping("/list_users")
    public String viewUserList(Model model){
        List<User> listUsers = repository.findAll();
        model.addAttribute("listUsers", listUsers);
        return "users";
    }
}
