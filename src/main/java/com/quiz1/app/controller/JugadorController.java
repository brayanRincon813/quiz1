package com.quiz1.app.controller;

import com.quiz1.app.models.Jugador;
import com.quiz1.app.repositorio.JugadorRepositorio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/jugador")
public class JugadorController {

    @Autowired
    private JugadorRepositorio jugadorRepositorio;

    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("jugadores", jugadorRepositorio.findAll());
        return "jugador/listar";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("jugador", new Jugador());
        model.addAttribute("titulo", "Nuevo Jugador");
        return "jugador/form";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("jugador", jugadorRepositorio.findById(id).orElseThrow());
        model.addAttribute("titulo", "Editar Jugador");
        return "jugador/form";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute Jugador jugador, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", jugador.getId() == null ? "Nuevo Jugador" : "Editar Jugador");
            return "jugador/form";
        }
        jugadorRepositorio.save(jugador);
        return "redirect:/jugador/listar";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        jugadorRepositorio.deleteById(id);
        return "redirect:/jugador/listar";
    }
}
