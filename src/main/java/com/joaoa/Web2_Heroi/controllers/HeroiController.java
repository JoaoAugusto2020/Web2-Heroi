package com.joaoa.Web2_Heroi.controllers;

import com.joaoa.Web2_Heroi.service.HeroiService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HeroiController {

    @Autowired
    private HeroiService heroiService;

    @GetMapping("/heroi")
    public String listar(Model model) {
        model.addAttribute("heroisList", heroiService.getAllHerois());
        return "heroi/listHeroi";
    }
}
