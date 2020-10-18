package com.furkanozbudak.ffm.controller;

import com.furkanozbudak.ffm.model.Product;
import com.furkanozbudak.ffm.model.UserEntity;
import com.furkanozbudak.ffm.service.ProductService;
import com.furkanozbudak.ffm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@SessionAttributes({"user", "authentication", "loggedIn", "role"})
public class SellerController {
    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    //new seller registration
    @GetMapping(value = {"/seller/new"})
    public String displayNewSellerForm(Model model) {
        model.addAttribute("user", new UserEntity());
        return "seller/new";
    }

    @PostMapping(value = {"/seller/new"})
    public String addNewSeller(@Valid @ModelAttribute("user") UserEntity userEntity,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", new UserEntity());
            return "seller/new";
        }
        userEntity = userService.save(userEntity);
        redirectAttributes.addFlashAttribute("successMessage", "Succesfully registered as a " + userEntity.getRole() + ". Please log in.");
        return "redirect:/userEntity/login";
    }

    //new product
    @GetMapping(value = {"/seller/newProduct"})
    public String displayNewProductForm(Model model, HttpSession session) {
        model.addAttribute("product", new Product());
        model.addAttribute("user", (session.getAttribute("authentication")));
        return "seller/newProduct";
    }

    @PostMapping(value = {"/seller/newProduct"})
    public String addNewSeller(@Valid @ModelAttribute("product") Product product,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model, HttpSession session) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("product", new Product());
            return "seller/newProduct";
        }
        product.setUserEntity((UserEntity) session.getAttribute("authentication"));
        product = productService.save(product);
        redirectAttributes.addFlashAttribute("successMessage", "New product is succesfully published on sale.");
        return "redirect:/seller/productList";
    }

    //get products by seller
    @GetMapping(value = {"/seller/productList"})
    public ModelAndView displayProductList(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("products", productService.findAllByUserEntity_Id(((UserEntity) session.getAttribute("authentication")).getId()));
        modelAndView.setViewName("seller/productList");
        boolean isListEmpty = false;
        if ((productService.findAllByUserEntity_Id(((UserEntity) session.getAttribute("authentication")).getId()).isEmpty())) {
            isListEmpty = true;
        }
        modelAndView.addObject("isListEmpty", isListEmpty);
        return modelAndView;
    }



}
