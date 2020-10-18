package com.furkanozbudak.ffm.controller;

import com.furkanozbudak.ffm.model.CartItem;
import com.furkanozbudak.ffm.model.Product;
import com.furkanozbudak.ffm.model.ShoppingCart;
import com.furkanozbudak.ffm.model.UserEntity;
import com.furkanozbudak.ffm.service.CartItemService;
import com.furkanozbudak.ffm.service.ProductService;
import com.furkanozbudak.ffm.service.ShoppingCartService;
import com.furkanozbudak.ffm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
@SessionAttributes({"user", "authentication", "loggedIn", "role"})
public class BuyerController {
    @Autowired
    UserService userService;

    @Autowired
    ShoppingCartService shoppingCartService;

    @Autowired
    CartItemService cartItemService;

    @Autowired
    ProductService productService;

    //new seller registration
    @GetMapping(value = {"/buyer/new"})
    public String displayNewBuyerForm(Model model) {
        model.addAttribute("user", new UserEntity());
        System.out.println("BABANIN AMI");
        System.out.println("BABANIN AMI");
        return "buyer/new";
    }

    @PostMapping(value = {"/buyer/new"})
    public String addNewBuyer(@Valid @ModelAttribute("user") UserEntity userEntity,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", new UserEntity());
            System.out.println("ZAZANIN AMI");
            System.out.println("ZAZANIN AMI");
            return "buyer/new";
        }

        System.out.println("ANANIN AMI");
        System.out.println("ANANIN AMI");

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUserEntity(userEntity);
        shoppingCartService.save(shoppingCart);

        redirectAttributes.addFlashAttribute("successMessage", "Succesfully registered as a " + userEntity.getRole() + ". Please log in.");
        return "redirect:/userEntity/login";
    }

    @PostMapping(value = {"/buyer/addToCart"})
    public String addToCart(@Valid @ModelAttribute("cartItem") CartItem cartItem, @ModelAttribute("productId") Long productId,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        System.out.println("1");
        // find product
        Product product = productService.findById(productId);

        System.out.println("2");
        // find shopping cart
        ShoppingCart shoppingCart = ((UserEntity) model.getAttribute("authentication")).getShoppingCart();

        System.out.println("3");
        // configure cart item
        cartItem.setShoppingCart(shoppingCart);
        cartItem.setProduct(product);
        cartItem.setCost(cartItem.getQuantity() * cartItem.getProduct().getPrice());

        System.out.println("4");
        // save cart item
        cartItemService.save(cartItem);


        System.out.println("5");
        return "redirect:/buyer/shoppingCart";
    }

}
//Long userId = ((UserEntity) model.getAttribute("authentication")).getId();
//UserEntity user = userService.findById(userId);
//shoppingCart.setUserEntity(userService.findById(((UserEntity) model.getAttribute("authentication")).getId()));