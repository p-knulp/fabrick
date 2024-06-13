package com.fabrick.asteroidspath.model;

import lombok.*;

import java.time.LocalDate;

/**
 * Classe model AteroidPath che verrà poi ritornata dal medesimo servizio rest esposto
 * dalla medesima applicazione di test
 * <p>AsteroidPath</p>
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class AsteroidPath {
    private LocalDate fromDate;
    private LocalDate toDate;
    private String fromPlanet;
    private String toPlanet;
}
