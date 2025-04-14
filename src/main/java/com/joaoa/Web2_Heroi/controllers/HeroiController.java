package com.joaoa.Web2_Heroi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HeroiController {

    @GetMapping("/heroi")
    public String getMethodName() {
        return "/index";
    }
}
