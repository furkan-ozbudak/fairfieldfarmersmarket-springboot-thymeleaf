package com.furkanozbudak.ffm.controller;

import com.furkanozbudak.ffm.model.CartItem;
import com.furkanozbudak.ffm.model.UserEntity;
import com.furkanozbudak.ffm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes({"user", "authentication", "loggedIn", "role"})
public class HomeController {
    @Autowired
    ProductService productService;

    @GetMapping(value = {"/", "/home", "home/index"})
    public String home(Model model) {
//        if(session.getAttribute("loggedIn") == null) {
//            session.setAttribute("loggedIn", false);
//            session.setAttribute("role", "noRole");
//            session.setAttribute("authentication", new UserEntity());
//        }
//        UserEntity authentication = (UserEntity)session.getAttribute("authentication");
//        System.out.println(authentication.getRole() + authentication.getEmail() + authentication.getPassword());
//        UserEntity user = (UserEntity) session.getAttribute("authentication");
//        boolean loggedIn = (boolean) session.getAttribute("loggedIn");
//        String role = (String) session.getAttribute(("role"));
//        System.out.println(loggedIn + role);
//        System.out.println(user.getEmail());
        model.addAttribute("products", productService.findAll());
        if (productService.findAll() == null) {
            model.addAttribute("isListEmpty", true);
        }
        model.addAttribute("cartItem", new CartItem());
        return "home/index";
    }
}
