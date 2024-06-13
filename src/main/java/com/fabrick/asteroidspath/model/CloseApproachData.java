package com.fabrick.asteroidspath.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Classe model di "mappaura" "trasporto" dati ricevuti dall'interrogazione del servizio rest.
 * applicato anche qui il lombok
 */
@Getter
@Setter
@NoArgsConstructor
public class CloseApproachData {
    @JsonProperty("close_approach_date")
    private String closeApproachDate;

    @JsonProperty("orbiting_body")
    private String orbitingBody;
}
