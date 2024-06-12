package com.fabrick.asteroidspath.model;

import lombok.*;

import java.time.LocalDate;

/*
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
*/
public class AsteroidPath {
    private LocalDate fromDate;
    private LocalDate toDate;
    private String fromPlanet;
    private String toPlanet;

    public AsteroidPath(LocalDate fromDate, LocalDate toDate, String fromPlanet, String toPlanet) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.fromPlanet = fromPlanet;
        this.toPlanet = toPlanet;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public String getFromPlanet() {
        return fromPlanet;
    }

    public void setFromPlanet(String fromPlanet) {
        this.fromPlanet = fromPlanet;
    }

    public String getToPlanet() {
        return toPlanet;
    }

    public void setToPlanet(String toPlanet) {
        this.toPlanet = toPlanet;
    }
}