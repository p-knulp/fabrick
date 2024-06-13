package com.fabrick.asteroidspath.controller;

import com.fabrick.asteroidspath.model.AsteroidPath;
import com.fabrick.asteroidspath.service.AsteroidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Classe di controller per esposizione endpoint
 * <p></p>
 * <p>GET /api/fabrick/v1.0/asteroids/{asteroidId}/paths</p>
 */
@RestController
@RequestMapping("/api/fabrick/v1.0/asteroids")
public class AsteroidController {
    @Autowired
    private AsteroidService asteroidService;  // asteroid service
    @GetMapping("/{asteroidId}/paths")
    public List<AsteroidPath> getAsteroidPaths(
            @PathVariable String asteroidId,
            @RequestParam(value = "fromDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @RequestParam(value = "toDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate) {

        System.out.println("--> Esegue richiesta da endpoint esposto dal medesimo servizio.");
        if (fromDate == null) {
            fromDate = LocalDate.now().minusYears(100); // -100 anni
        }
        if (toDate == null) {
            toDate = LocalDate.now();
        }

        return asteroidService.getAsteroidPaths(asteroidId, fromDate, toDate);
    }
}