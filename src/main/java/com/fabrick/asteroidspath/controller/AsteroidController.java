package com.fabrick.asteroidspath.controller;

import com.fabrick.asteroidspath.model.AsteroidPath;
import com.fabrick.asteroidspath.service.AsteroidService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/fabrick/v1.0/asteroids")
public class AsteroidController {

    private final AsteroidService asteroidService;

    public AsteroidController(AsteroidService asteroidService) {
        this.asteroidService = asteroidService;
    }

    @GetMapping("/{asteroidId}/paths")
    public List<AsteroidPath> getAsteroidPaths(
            @PathVariable String asteroidId,
            @RequestParam(value = "fromDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @RequestParam(value = "toDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate) {

        if (fromDate == null) {
            fromDate = LocalDate.now().minusYears(100);
        }
        if (toDate == null) {
            toDate = LocalDate.now();
        }

        return asteroidService.getAsteroidPaths(asteroidId, fromDate, toDate);
    }
}