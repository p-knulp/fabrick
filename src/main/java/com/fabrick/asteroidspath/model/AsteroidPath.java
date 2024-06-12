package com.fabrick.asteroidspath.model;

import lombok.*;

import java.time.LocalDate;

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