package com.quiz1.app;

import com.quiz1.app.models.*;
import com.quiz1.app.repositorio.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {

    private final AsociacionRepositorio asociacionRepo;
    private final EntrenadorRepositorio entrenadorRepo;
    private final JugadorRepositorio jugadorRepo;
    private final CompeticionRepositorio competicionRepo;
    private final ClubRepositorio clubRepo;

    public DataInitializer(AsociacionRepositorio asociacionRepo,
                           EntrenadorRepositorio entrenadorRepo,
                           JugadorRepositorio jugadorRepo,
                           CompeticionRepositorio competicionRepo,
                           ClubRepositorio clubRepo) {
        this.asociacionRepo = asociacionRepo;
        this.entrenadorRepo = entrenadorRepo;
        this.jugadorRepo = jugadorRepo;
        this.competicionRepo = competicionRepo;
        this.clubRepo = clubRepo;
    }

    @Override
    public void run(String... args) {
        if (clubRepo.count() > 0) return;

        // Asociaciones
        Asociacion fifa = new Asociacion();
        fifa.setNombre("FIFA"); fifa.setPais("Internacional"); fifa.setPresidente("Gianni Infantino");
        asociacionRepo.save(fifa);

        Asociacion uefa = new Asociacion();
        uefa.setNombre("UEFA"); uefa.setPais("Europa"); uefa.setPresidente("Aleksander Ceferin");
        asociacionRepo.save(uefa);

        Asociacion conmebol = new Asociacion();
        conmebol.setNombre("CONMEBOL"); conmebol.setPais("Sudamerica"); conmebol.setPresidente("Alejandro Dominguez");
        asociacionRepo.save(conmebol);

        // Entrenadores
        Entrenador ancelotti = new Entrenador();
        ancelotti.setNombre("Carlo"); ancelotti.setApellido("Ancelotti");
        ancelotti.setEdad(65); ancelotti.setNacionalidad("Italiano");
        entrenadorRepo.save(ancelotti);

        Entrenador guardiola = new Entrenador();
        guardiola.setNombre("Pep"); guardiola.setApellido("Guardiola");
        guardiola.setEdad(53); guardiola.setNacionalidad("Español");
        entrenadorRepo.save(guardiola);

        Entrenador klopp = new Entrenador();
        klopp.setNombre("Jurgen"); klopp.setApellido("Klopp");
        klopp.setEdad(57); klopp.setNacionalidad("Aleman");
        entrenadorRepo.save(klopp);

        // Jugadores
        Jugador vinicius = jugadorRepo.save(jugador("Vinicius", "Junior", 7, "Delantero"));
        Jugador modric   = jugadorRepo.save(jugador("Luka", "Modric", 10, "Mediocampista"));
        Jugador courtois = jugadorRepo.save(jugador("Thibaut", "Courtois", 1, "Portero"));

        Jugador haaland  = jugadorRepo.save(jugador("Erling", "Haaland", 9, "Delantero"));
        Jugador debruyne = jugadorRepo.save(jugador("Kevin", "De Bruyne", 17, "Mediocampista"));
        Jugador ederson  = jugadorRepo.save(jugador("Ederson", "Moraes", 31, "Portero"));

        Jugador salah    = jugadorRepo.save(jugador("Mohamed", "Salah", 11, "Delantero"));
        Jugador vandijk  = jugadorRepo.save(jugador("Virgil", "Van Dijk", 4, "Defensa"));
        Jugador alisson  = jugadorRepo.save(jugador("Alisson", "Becker", 1, "Portero"));

        // Competiciones
        Competicion champions = new Competicion();
        champions.setNombre("Champions League"); champions.setMontoPremio(10000000);
        champions.setFechaInicio(LocalDate.of(2024, 9, 17)); champions.setFechaFin(LocalDate.of(2025, 5, 31));
        competicionRepo.save(champions);

        Competicion laliga = new Competicion();
        laliga.setNombre("La Liga"); laliga.setMontoPremio(4000000);
        laliga.setFechaInicio(LocalDate.of(2024, 8, 15)); laliga.setFechaFin(LocalDate.of(2025, 5, 25));
        competicionRepo.save(laliga);

        Competicion premier = new Competicion();
        premier.setNombre("Premier League"); premier.setMontoPremio(5000000);
        premier.setFechaInicio(LocalDate.of(2024, 8, 10)); premier.setFechaFin(LocalDate.of(2025, 5, 25));
        competicionRepo.save(premier);

        // Clubes con listas mutables (ArrayList)
        Club realMadrid = new Club();
        realMadrid.setNombre("Real Madrid"); realMadrid.setCiudad("Madrid"); realMadrid.setAnioFundacion(1902);
        realMadrid.setEntrenador(ancelotti); realMadrid.setAsociacion(fifa);
        realMadrid.setJugadores(new ArrayList<>(Arrays.asList(vinicius, modric, courtois)));
        realMadrid.setCompeticiones(new ArrayList<>(Arrays.asList(champions, laliga)));
        clubRepo.save(realMadrid);

        Club manCity = new Club();
        manCity.setNombre("Manchester City"); manCity.setCiudad("Manchester"); manCity.setAnioFundacion(1880);
        manCity.setEntrenador(guardiola); manCity.setAsociacion(uefa);
        manCity.setJugadores(new ArrayList<>(Arrays.asList(haaland, debruyne, ederson)));
        manCity.setCompeticiones(new ArrayList<>(Arrays.asList(champions, premier)));
        clubRepo.save(manCity);

        Club liverpool = new Club();
        liverpool.setNombre("Liverpool FC"); liverpool.setCiudad("Liverpool"); liverpool.setAnioFundacion(1892);
        liverpool.setEntrenador(klopp); liverpool.setAsociacion(uefa);
        liverpool.setJugadores(new ArrayList<>(Arrays.asList(salah, vandijk, alisson)));
        liverpool.setCompeticiones(new ArrayList<>(Arrays.asList(premier)));
        clubRepo.save(liverpool);
    }

    private Jugador jugador(String nombre, String apellido, int numero, String posicion) {
        Jugador j = new Jugador();
        j.setNombre(nombre); j.setApellido(apellido);
        j.setNumero(numero); j.setPosicion(posicion);
        return j;
    }
}
