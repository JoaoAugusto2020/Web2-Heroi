package com.joaoa.Web2_Heroi.controller;

import com.joaoa.Web2_Heroi.model.Heroi;
import com.joaoa.Web2_Heroi.service.IHeroiService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/heroi")
public class HeroiController {

    @Autowired
    private IHeroiService IHeroiService;

    //pagina
    @GetMapping("/list")
    public String listar(Model model) {
        model.addAttribute("heroisList", IHeroiService.getAllHerois());
        return "heroi/list";
    }

    //pagina
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("heroi", new Heroi());
        return "heroi/create";
    }

    //pagina
    @GetMapping("/edit/{idHeroi}")
    public String edit(@PathVariable Long idHeroi, Model model) {
        Heroi heroi = IHeroiService.getHeroiById(idHeroi);
        model.addAttribute("heroi", heroi);
        return "heroi/edit";
    }

    @PostMapping("/save")
    public String postMethodName(@ModelAttribute @Valid Heroi heroi, BindingResult result) {
        if (result.hasErrors()) {
            return "heroi/create";
        }
        IHeroiService.saveHeroi(heroi);
        return "redirect:list";
    }

    @GetMapping("/delete/{idHeroi}")
    public String delete(@PathVariable Long idHeroi) {
        this.IHeroiService.deleteHeroiById(idHeroi);
        return "redirect:/heroi/list";
    }
}
