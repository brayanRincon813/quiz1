package com.quiz1.app.controller;

import com.quiz1.app.models.Competicion;
import com.quiz1.app.repositorio.CompeticionRepositorio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/competicion")
public class CompeticionController {

    @Autowired
    private CompeticionRepositorio competicionRepositorio;

    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("competiciones", competicionRepositorio.findAll());
        return "competicion/listar";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("competicion", new Competicion());
        model.addAttribute("titulo", "Nueva Competición");
        return "competicion/form";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("competicion", competicionRepositorio.findById(id).orElseThrow());
        model.addAttribute("titulo", "Editar Competición");
        return "competicion/form";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute Competicion competicion, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", competicion.getId() == null ? "Nueva Competición" : "Editar Competición");
            return "competicion/form";
        }
        competicionRepositorio.save(competicion);
        return "redirect:/competicion/listar";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        competicionRepositorio.deleteById(id);
        return "redirect:/competicion/listar";
    }
}
