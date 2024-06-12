package com.fabrick.asteroidspath.model;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Classe di "mappatura" dei dati di response
 */
public class NasaApiResponse {
    @JsonProperty("close_approach_data")
    private List<CloseApproachData> closeApproachData;

    public List<CloseApproachData> getCloseApproachData() {
        return closeApproachData;
    }

    public void setCloseApproachData(List<CloseApproachData> closeApproachData) {
        this.closeApproachData = closeApproachData;
    }
}