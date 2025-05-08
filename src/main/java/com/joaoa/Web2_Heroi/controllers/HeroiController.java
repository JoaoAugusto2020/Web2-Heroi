package com.joaoa.Web2_Heroi.controllers;

import com.joaoa.Web2_Heroi.model.Heroi;
import com.joaoa.Web2_Heroi.service.HeroiService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HeroiController {

    @Autowired
    private HeroiService heroiService;

    @GetMapping("/heroi/list")
    public String listar(Model model) {
        model.addAttribute("heroisList", heroiService.getAllHerois());
        return "heroi/list";
    }

    @GetMapping("/heroi/create")
    public String create(Model model) {
        model.addAttribute("heroi", new Heroi());
        return "heroi/create";
    }

    @PostMapping("/heroi/save")
    public String postMethodName(@ModelAttribute @Valid Heroi heroi, BindingResult result) {
        if (result.hasErrors()) {
            return "heroi/create";
        }
        heroiService.saveHeroi(heroi);
        return "redirect:/heroi/list";
    }

    @GetMapping("/heroi/delete/{idHeroi}")
    public String delete(@PathVariable Long idHeroi) {
        this.heroiService.deleteHeroiById(idHeroi);
        return "redirect:/heroi/list";
    }

    @GetMapping("/heroi/edit/{idHeroi}")
    public String edit(@PathVariable Long idHeroi, Model model) {
        Heroi heroi = heroiService.getHeroiById(idHeroi);
        model.addAttribute("heroi", heroi);
        return "heroi/edit";
    }
}
