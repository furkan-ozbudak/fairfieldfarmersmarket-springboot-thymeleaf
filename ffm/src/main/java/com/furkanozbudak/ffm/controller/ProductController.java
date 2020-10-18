package com.furkanozbudak.ffm.controller;

import com.furkanozbudak.ffm.model.CartItem;
import com.furkanozbudak.ffm.model.Product;
import com.furkanozbudak.ffm.model.UserEntity;
import com.furkanozbudak.ffm.repository.CartItemRepository;
import com.furkanozbudak.ffm.service.CartItemService;
import com.furkanozbudak.ffm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@SessionAttributes({"user", "authentication", "loggedIn", "role"})
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    CartItemService cartItemService;

    @Autowired
    CartItemRepository cartItemRepository;


    //get all products
    @GetMapping(value = {"/product/list"})
    public ModelAndView displayProductList() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("products", productService.findAll());
        modelAndView.setViewName("product/list");
        boolean isListEmpty = false;
        if ((productService.findAll()).isEmpty()) {
            isListEmpty = true;
        }
        modelAndView.addObject("isListEmpty", isListEmpty);
        return modelAndView;
    }

    @GetMapping(value = {"/product/new"})
    public String displayNewProductForm(Model model) {
        model.addAttribute("product", new Product());
        /** TEST PURPOSE, SHOULD COME FROM LOGIN SESSION */
        UserEntity userEntity1 = new UserEntity();
        userEntity1.setFirstName("Furkan");
        model.addAttribute("user", userEntity1);
        return "product/new";
    }

    @PostMapping(value = {"/product/new"})
    public String addNewProduct(@Valid @ModelAttribute("product") Product product,
                                BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            /** TEST PURPOSE, SHOULD COME FROM LOGIN SESSION */
            UserEntity userEntity1 = new UserEntity();
            userEntity1.setFirstName("Furkan");
            model.addAttribute("user", userEntity1);
            return "product/new";
        }
        product = productService.save(product);
        return "redirect:/product/list";
    }

    @GetMapping(value = {"/product/delete/{id}"})
    public String deleteProductFromSeller(@PathVariable Long id, Model model) {
        productService.deleteById(id);
        return "redirect:/seller/productList";
    }

    @GetMapping(value = {"/product/remove/{id}"})
    public String deleteProductFromBuyer(@PathVariable Long id, Model model) {
        Product product = productService.findById(id);
        CartItem cartItem = product.getCartItem();
        //cartItem.getShoppingCart().getCartItems().remove(cartItem);
        cartItemRepository.deleteById(cartItem.getId());


        return "redirect:/buyer/shoppingCart";
    }

}
