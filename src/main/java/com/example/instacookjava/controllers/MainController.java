package com.example.instacookjava.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
    @GetMapping("/login")
    public String showLogInForm(){ return "login/login"; }

    @GetMapping("/access_denied")
    public String accessDeniedPage(){ return "login/accessDenied"; }

    @GetMapping("/perform_login")
    public String successfulLogin(){ return "/kitchens"; }

    @RequestMapping({"","/","/auction"})
    public ModelAndView getHome(){

        return new ModelAndView("main");
    }


}
