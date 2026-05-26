package com.quiz1.app.controller;

import com.quiz1.app.models.Asociacion;
import com.quiz1.app.repositorio.AsociacionRepositorio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/asociacion")
public class AsociacionController {

    @Autowired
    private AsociacionRepositorio asociacionRepositorio;

    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("asociaciones", asociacionRepositorio.findAll());
        return "asociacion/listar";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("asociacion", new Asociacion());
        model.addAttribute("titulo", "Nueva Asociación");
        return "asociacion/form";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("asociacion", asociacionRepositorio.findById(id).orElseThrow());
        model.addAttribute("titulo", "Editar Asociación");
        return "asociacion/form";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute Asociacion asociacion, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", asociacion.getId() == null ? "Nueva Asociación" : "Editar Asociación");
            return "asociacion/form";
        }
        asociacionRepositorio.save(asociacion);
        return "redirect:/asociacion/listar";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        asociacionRepositorio.deleteById(id);
        return "redirect:/asociacion/listar";
    }
}
