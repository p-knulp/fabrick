package com.fabrick.asteroidspath.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Classe model di "mappatura" dei dati di response
 * <p>NasaApiResponse</p>
 */
@Getter
@Setter
@NoArgsConstructor
public class NasaApiResponse {
    @JsonProperty("close_approach_data")
    private List<CloseApproachData> closeApproachData;
}