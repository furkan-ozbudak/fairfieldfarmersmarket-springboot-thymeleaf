package com.furkanozbudak.ffm.controller;

import com.furkanozbudak.ffm.model.UserEntity;
import com.furkanozbudak.ffm.security.SessionAnnotation;
import com.furkanozbudak.ffm.service.AdminService;
import com.furkanozbudak.ffm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@SessionAttributes({"user", "authentication", "loggedIn", "role"})
public class AdminController {
    @Autowired
    AdminService adminService;

    @Autowired
    UserService userService;

    @GetMapping(value = {"/admin/list"})
    public ModelAndView displayAdminList(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("authentication", session.getAttribute("authentication"));
        modelAndView.addObject("users", userService.findAllByRole("admin"));

        modelAndView.setViewName("adminList");
        boolean isListEmpty = false;
        if ((userService.findAllByRole("admin")).isEmpty()) {
            isListEmpty = true;
        }
        modelAndView.addObject("isListEmpty", isListEmpty);
        return modelAndView;
    }

    @GetMapping(value = {"/admin/new"})
    public String displayNewAdminForm(Model model) {
        model.addAttribute("user", new UserEntity());
        return "admin/new";
    }

    @PostMapping(value = {"/admin/new"})
    public String addNewAdmin(@Valid @ModelAttribute("user") UserEntity userEntity,
                                BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "account/new";
        }
        userEntity = userService.save(userEntity);
        return "redirect:/admin/list";
    }

    @GetMapping(value = {"/admin/delete/{id}"})
    public String deleteAdmin(@PathVariable Long id, Model model) {
        userService.deleteById(id);
        return "redirect:/admin/list";
    }

    @GetMapping(value = {"/admin/update/{id}"})
    public String displayUpdateAdminForm(@PathVariable Long id, Model model) {
        UserEntity userEntity = userService.findById(id);
        if (userEntity != null) {
            model.addAttribute("user", userEntity);
            return "admin/update";
        }
        return "adminList";
    }

    @PostMapping(value = {"/admin/update"})
    public String updateAdmin(@Valid @ModelAttribute("user") UserEntity userEntity,
                                BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "admin/update";
        }
        userEntity = userService.save(userEntity);
        return "redirect:/admin/list";
    }
}
