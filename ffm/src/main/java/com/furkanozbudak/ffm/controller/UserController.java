package com.furkanozbudak.ffm.controller;

import com.furkanozbudak.ffm.model.UserEntity;
import com.furkanozbudak.ffm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes({"user", "authentication", "loggedIn", "role"})
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping(value = {"/userEntity/login"})
    public String displayLoginForm(Model model) {
        model.addAttribute("email", "");
        model.addAttribute("password", "");
        return "user/login";
    }

    @PostMapping(value = {"/userEntity/login"})
    public String login(@Valid @ModelAttribute("email") String email,
                        @ModelAttribute("password") String password,

                        BindingResult bindingResult, Model model) {

        UserEntity authentication = userService.findByEmailAndPassword(email, password);
        if (authentication == null) {
            model.addAttribute("message", "Wrong email or password.");
            return "user/login";
        }
//        session.setAttribute("loggedIn", true);
//        session.setAttribute("authentication", authentication);
//        session.setAttribute("role", authentication.getRole());
        model.addAttribute("loggedIn", true);
        model.addAttribute("authentication", authentication);
        model.addAttribute("role", authentication.getRole());
        return "redirect:/home/index";
    }

    @GetMapping(value = {"/userEntity/logout"})
    public String logout(@Valid @ModelAttribute("email") String email,
                         @ModelAttribute("password") String password,
                         HttpSession session,
                         BindingResult bindingResult, Model model) {

//        session.invalidate();
//        session.setAttribute("authentication", null);
//        session.setAttribute("loggedIn", false);
//        session.setAttribute("role", "noRole");
//        boolean loggedIn = (boolean) session.getAttribute("loggedIn");
//        String role = (String) session.getAttribute(("role"));
//        System.out.println(loggedIn + role);

        model.addAttribute("loggedIn", false);
        model.addAttribute("role", "noRole");
        model.addAttribute("authentication", null);
        return "redirect:/home/index";
    }

    @GetMapping(value = {"/userEntity/register"})
    public String register(Model model, @ModelAttribute("userType") String userType) {
        List<String> userTypes = new ArrayList<>();
        userTypes.add("buyer");
        userTypes.add("seller");
        model.addAttribute("userTypes", userTypes);
        return "user/register";
    }

    @PostMapping(value = {"/userEntity/register"})
    public String login(@ModelAttribute("userType") String userType, HttpSession session ) {
        session.setAttribute("userType", userType);
        return "redirect:/" + userType + "/new";
    }

}
