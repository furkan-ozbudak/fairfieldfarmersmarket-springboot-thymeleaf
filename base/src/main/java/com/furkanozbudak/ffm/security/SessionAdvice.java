package com.furkanozbudak.ffm.security;

import com.furkanozbudak.ffm.model.UserEntity;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

@Aspect
//@Component
public class SessionAdvice {

    @Autowired
    HttpSession session;

    @Autowired
    Model model;


//    @Before("@annotation(SessionAnnotation)")
    @Before("within(com.furkanozbudak.ffm.controller.*)")
    public void sessionCheck(JoinPoint joinPoint){
        UserEntity authentication = (UserEntity) session.getAttribute("authentication");
        if(authentication != null) {
            model.addAttribute("loggedIn", true);
            model.addAttribute("authentication", authentication);
            model.addAttribute("role", authentication.getRole());
        }
        else {
            model.addAttribute("loggedIn", false);
        }
    }

}
