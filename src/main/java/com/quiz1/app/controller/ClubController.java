package com.quiz1.app.controller;

import com.quiz1.app.models.Club;
import com.quiz1.app.models.Competicion;
import com.quiz1.app.models.Jugador;
import com.quiz1.app.repositorio.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class ClubController {

    @Autowired private ClubRepositorio clubRepositorio;
    @Autowired private EntrenadorRepositorio entrenadorRepositorio;
    @Autowired private AsociacionRepositorio asociacionRepositorio;
    @Autowired private CompeticionRepositorio competicionRepositorio;
    @Autowired private JugadorRepositorio jugadorRepositorio;

    @GetMapping("/")
    public String home() {
        return "redirect:/club/listar";
    }

    @GetMapping("/club/listar")
    public String listar(Model model) {
        model.addAttribute("clubes", clubRepositorio.findAll());
        return "club/listar";
    }

    @GetMapping("/club/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("club", new Club());
        model.addAttribute("titulo", "Nuevo Club");
        agregarListas(model, new HashSet<>(), new HashSet<>());
        return "club/form";
    }

    @GetMapping("/club/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Club club = clubRepositorio.findById(id).orElseThrow();
        Set<Long> selectedCompIds = club.getCompeticiones() != null
                ? club.getCompeticiones().stream().map(Competicion::getId).collect(Collectors.toSet())
                : new HashSet<>();
        Set<Long> selectedJugIds = club.getJugadores() != null
                ? club.getJugadores().stream().map(Jugador::getId).collect(Collectors.toSet())
                : new HashSet<>();
        model.addAttribute("club", club);
        model.addAttribute("titulo", "Editar Club");
        agregarListas(model, selectedCompIds, selectedJugIds);
        return "club/form";
    }

    @PostMapping("/club/guardar")
    public String guardar(@Valid @ModelAttribute Club club, BindingResult result,
                          @RequestParam(required = false) Long asociacionId,
                          @RequestParam(required = false) Long entrenadorId,
                          @RequestParam(required = false) List<Long> competicionIds,
                          @RequestParam(required = false) List<Long> jugadorIds,
                          Model model) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", club.getId() == null ? "Nuevo Club" : "Editar Club");
            Set<Long> selComp = competicionIds != null ? new HashSet<>(competicionIds) : new HashSet<>();
            Set<Long> selJug  = jugadorIds    != null ? new HashSet<>(jugadorIds)    : new HashSet<>();
            agregarListas(model, selComp, selJug);
            return "club/form";
        }
        if (asociacionId   != null) club.setAsociacion(asociacionRepositorio.findById(asociacionId).orElse(null));
        if (entrenadorId   != null) club.setEntrenador(entrenadorRepositorio.findById(entrenadorId).orElse(null));
        if (competicionIds != null) club.setCompeticiones(competicionRepositorio.findAllById(competicionIds));
        if (jugadorIds     != null) club.setJugadores(jugadorRepositorio.findAllById(jugadorIds));
        clubRepositorio.save(club);
        return "redirect:/club/listar";
    }

    @GetMapping("/club/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        clubRepositorio.deleteById(id);
        return "redirect:/club/listar";
    }

    private void agregarListas(Model model, Set<Long> selectedCompeticionIds, Set<Long> selectedJugadorIds) {
        model.addAttribute("entrenadores", entrenadorRepositorio.findAll());
        model.addAttribute("asociaciones", asociacionRepositorio.findAll());
        model.addAttribute("competiciones", competicionRepositorio.findAll());
        model.addAttribute("jugadores", jugadorRepositorio.findAll());
        model.addAttribute("selectedCompeticionIds", selectedCompeticionIds);
        model.addAttribute("selectedJugadorIds", selectedJugadorIds);
    }
}
