package com.furkanozbudak.ffm.controller;

import com.furkanozbudak.ffm.model.ShoppingCart;
import com.furkanozbudak.ffm.model.UserEntity;
import com.furkanozbudak.ffm.service.CartItemService;
import com.furkanozbudak.ffm.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes({"user", "authentication", "loggedIn", "role"})
public class ShoppingCartController {
    @Autowired
    ShoppingCartService shoppingCartService;

    //get products by seller
    @GetMapping(value = {"/buyer/shoppingCart"})
    public String displayShoppingCart(Model model) {
        //ModelAndView modelAndView = new ModelAndView();
        model.addAttribute("shoppingCart", shoppingCartService.findByUserEntity_Id(((UserEntity)model.getAttribute("authentication")).getId()));
        //modelAndView.setViewName("buyer/shoppingCart");
        //boolean isListEmpty = false;
//        if (shoppingCartService.findByUserEntity_Id(((UserEntity)model.getAttribute("authentication")).getId()).getCartItems() == null) {
//            isListEmpty = true;
//        }
        //modelAndView.addObject("isListEmpty", isListEmpty);
        //return modelAndView;

        return "buyer/shoppingCart";

    }
}
