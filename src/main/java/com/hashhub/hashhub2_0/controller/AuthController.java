package com.hashhub.hashhub2_0.controller;

import com.hashhub.hashhub2_0.models.UserEntity;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.hashhub.hashhub2_0.dto.RegistrationDto;
import com.hashhub.hashhub2_0.service.UserService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    private UserService userService;
    private String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private String passwordRegex = "^(?=.*[0-9])(?=.*[a-zA-Z])[a-zA-Z0-9]{8,}$"; //OWASP Recommendations for strong password.

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        RegistrationDto user = new RegistrationDto();
        model.addAttribute("user", user);
        return "register";
    }
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }


    @PostMapping("register/save")
    public String register(@Valid @ModelAttribute("users") RegistrationDto user,
                           BindingResult result, Model model) {

        UserEntity existingUserEmail = userService.findByEmail(user.getEmail());
        if (existingUserEmail != null && existingUserEmail.getEmail() != null && !existingUserEmail.getEmail().isEmpty()) {
            result.rejectValue("email", "This email or username has already been registered.");
            return "redirect:/register?fail";
        }

        UserEntity existingUserUsername = userService.findByUsername(user.getUsername());
        if (existingUserUsername != null && existingUserUsername.getUsername() != null && !existingUserUsername.getUsername().isEmpty()) {
            result.rejectValue("username", "This email or username has already been registered.");
            return "redirect:/register?fail";
        }


        if (result.hasErrors()) {
            model.addAttribute("users", user);
            return "register";
        }
        userService.saveUser(user);
        System.out.print("User registered successfully.");
        return "redirect:/login";

    }
}