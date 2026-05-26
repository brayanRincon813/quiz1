package com.quiz1.app.controller;

import com.quiz1.app.models.Entrenador;
import com.quiz1.app.repositorio.EntrenadorRepositorio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/entrenador")
public class EntrenadorController {

    @Autowired
    private EntrenadorRepositorio entrenadorRepositorio;

    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("entrenadores", entrenadorRepositorio.findAll());
        return "entrenador/listar";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("entrenador", new Entrenador());
        model.addAttribute("titulo", "Nuevo Entrenador");
        return "entrenador/form";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("entrenador", entrenadorRepositorio.findById(id).orElseThrow());
        model.addAttribute("titulo", "Editar Entrenador");
        return "entrenador/form";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute Entrenador entrenador, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", entrenador.getId() == null ? "Nuevo Entrenador" : "Editar Entrenador");
            return "entrenador/form";
        }
        entrenadorRepositorio.save(entrenador);
        return "redirect:/entrenador/listar";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        entrenadorRepositorio.deleteById(id);
        return "redirect:/entrenador/listar";
    }
}
