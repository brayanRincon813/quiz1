package com.quiz1.app;

import com.quiz1.app.models.*;
import com.quiz1.app.repositorio.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

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

        Asociacion uefa = new Asociacion();
        uefa.setNombre("UEFA"); uefa.setPais("Europa"); uefa.setPresidente("Aleksander Ceferin");

        Asociacion conmebol = new Asociacion();
        conmebol.setNombre("CONMEBOL"); conmebol.setPais("Sudamerica"); conmebol.setPresidente("Alejandro Dominguez");

        asociacionRepo.saveAll(List.of(fifa, uefa, conmebol));

        // Entrenadores
        Entrenador ancelotti = new Entrenador();
        ancelotti.setNombre("Carlo"); ancelotti.setApellido("Ancelotti");
        ancelotti.setEdad(65); ancelotti.setNacionalidad("Italiano");

        Entrenador guardiola = new Entrenador();
        guardiola.setNombre("Pep"); guardiola.setApellido("Guardiola");
        guardiola.setEdad(53); guardiola.setNacionalidad("Español");

        Entrenador klopp = new Entrenador();
        klopp.setNombre("Jurgen"); klopp.setApellido("Klopp");
        klopp.setEdad(57); klopp.setNacionalidad("Aleman");

        entrenadorRepo.saveAll(List.of(ancelotti, guardiola, klopp));

        // Jugadores
        Jugador vinicius = jugador("Vinicius", "Junior", 7, "Delantero");
        Jugador modric   = jugador("Luka", "Modric", 10, "Mediocampista");
        Jugador courtois = jugador("Thibaut", "Courtois", 1, "Portero");

        Jugador haaland  = jugador("Erling", "Haaland", 9, "Delantero");
        Jugador debruyne = jugador("Kevin", "De Bruyne", 17, "Mediocampista");
        Jugador ederson  = jugador("Ederson", "Moraes", 31, "Portero");

        Jugador salah    = jugador("Mohamed", "Salah", 11, "Delantero");
        Jugador vandijk  = jugador("Virgil", "Van Dijk", 4, "Defensa");
        Jugador alisson  = jugador("Alisson", "Becker", 1, "Portero");

        jugadorRepo.saveAll(List.of(vinicius, modric, courtois, haaland, debruyne, ederson, salah, vandijk, alisson));

        // Competiciones
        Competicion champions = new Competicion();
        champions.setNombre("Champions League"); champions.setMontoPremio(10000000);
        champions.setFechaInicio(LocalDate.of(2024, 9, 17)); champions.setFechaFin(LocalDate.of(2025, 5, 31));

        Competicion laliga = new Competicion();
        laliga.setNombre("La Liga"); laliga.setMontoPremio(4000000);
        laliga.setFechaInicio(LocalDate.of(2024, 8, 15)); laliga.setFechaFin(LocalDate.of(2025, 5, 25));

        Competicion premier = new Competicion();
        premier.setNombre("Premier League"); premier.setMontoPremio(5000000);
        premier.setFechaInicio(LocalDate.of(2024, 8, 10)); premier.setFechaFin(LocalDate.of(2025, 5, 25));

        competicionRepo.saveAll(List.of(champions, laliga, premier));

        // Clubes
        Club realMadrid = new Club();
        realMadrid.setNombre("Real Madrid"); realMadrid.setCiudad("Madrid"); realMadrid.setAnioFundacion(1902);
        realMadrid.setEntrenador(ancelotti); realMadrid.setAsociacion(fifa);
        realMadrid.setJugadores(List.of(vinicius, modric, courtois));
        realMadrid.setCompeticiones(List.of(champions, laliga));

        Club manCity = new Club();
        manCity.setNombre("Manchester City"); manCity.setCiudad("Manchester"); manCity.setAnioFundacion(1880);
        manCity.setEntrenador(guardiola); manCity.setAsociacion(uefa);
        manCity.setJugadores(List.of(haaland, debruyne, ederson));
        manCity.setCompeticiones(List.of(champions, premier));

        Club liverpool = new Club();
        liverpool.setNombre("Liverpool FC"); liverpool.setCiudad("Liverpool"); liverpool.setAnioFundacion(1892);
        liverpool.setEntrenador(klopp); liverpool.setAsociacion(uefa);
        liverpool.setJugadores(List.of(salah, vandijk, alisson));
        liverpool.setCompeticiones(List.of(premier));

        clubRepo.saveAll(List.of(realMadrid, manCity, liverpool));
    }

    private Jugador jugador(String nombre, String apellido, int numero, String posicion) {
        Jugador j = new Jugador();
        j.setNombre(nombre); j.setApellido(apellido);
        j.setNumero(numero); j.setPosicion(posicion);
        return j;
    }
}
