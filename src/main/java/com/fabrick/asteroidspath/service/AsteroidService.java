package com.fabrick.asteroidspath.service;

import com.fabrick.asteroidspath.model.AsteroidPath;
import com.fabrick.asteroidspath.model.CloseApproachData;
import com.fabrick.asteroidspath.model.NasaApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AsteroidService {
    @Value("${nasa.api.key}")
    private String apiKey;
    @Value("${nasa.api.url}")
    private String apiUrl;
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    public List<AsteroidPath> getAsteroidPaths(String asteroidId, LocalDate fromDate, LocalDate toDate) {
        String url = String.format("%s%s?api_key=%s", apiUrl, asteroidId, apiKey);
        NasaApiResponse response = this.restTemplate().getForObject(url, NasaApiResponse.class);

        if (response == null || response.getCloseApproachData() == null) {
            return new ArrayList<>();
        }

        List<AsteroidPath> paths = new ArrayList<>();
        var dati = response.getCloseApproachData().stream().
                sorted(Comparator.comparing(CloseApproachData::getCloseApproachDate)).collect(Collectors.toList());

        LocalDate fromCADate=null;
        LocalDate toCADate= null;
        String fromCAPlanet=null;
        String toCAPlanet=null;
        List<AsteroidPath> asteroidPathsTemp = new ArrayList<>();
        for (var data : dati ) {
            System.out.println("data.getCloseApproachDate() : " + data.getCloseApproachDate() );
            System.out.println("data.getOrbitingBody() : " + data.getOrbitingBody() );

            LocalDate date = LocalDate.parse(data.getCloseApproachDate(), DateTimeFormatter.ISO_DATE);
            if (date.isBefore(fromDate) || date.isAfter(toDate)) {
                continue;
            }

            if(fromCADate==null){
                fromCADate = date;
            } else if (fromCADate!=null && toCADate==null) {
                toCADate = date;
            } else {
                // TODO to be verify
            }

            String planet = data.getOrbitingBody();

            if(fromCAPlanet==null){
                fromCAPlanet = planet;
            } else if (fromCAPlanet!=null && toCAPlanet==null) {
                toCAPlanet = planet;
            } else {
            // TODO .... to be verify
            }


            if(fromCADate!=null && toCADate!=null && fromCAPlanet!=null && toCAPlanet!=null ){
                if( !fromCAPlanet.equalsIgnoreCase(toCAPlanet) ) {
                    paths.add(new AsteroidPath(fromCADate, toCADate, fromCAPlanet, toCAPlanet));

                    fromCADate=null;
                    toCADate=null;
                    fromCAPlanet=null;
                    toCAPlanet=null;
                }
            }
        }

        return paths;
    }



}
