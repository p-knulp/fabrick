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

/**
 * Classe di service per la gestione della lista di output paths asteroidi.
 * <p>AsteroidService</p>
 */
@Service
public class AsteroidService {
    // si prelvano i dati dal file di properties
    @Value("${nasa.api.key}")
    private String apiKey;  // nasa.api.key=DEMO_KEY
    @Value("${nasa.api.url}") // nasa.api.url=
    private String apiUrl;
    // creazione bean template
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    /**
     * Metodo preposto ad eseguire il servizio di chiamata all'endpoint della nasa ed eseguire la parte di elaborazione output.
     * <p>getAsteroidPaths</p>
     * @param asteroidId id dell'asteroide SPK-ID
     * @param fromDate dalla data
     * @param toDate alla data
     * @return
     */
    public List<AsteroidPath> getAsteroidPaths(String asteroidId, LocalDate fromDate, LocalDate toDate) {
        String url = String.format("%s%s?api_key=%s", apiUrl, asteroidId, apiKey);
        // esegue chiamata rest alla nasa link e key deno nel file di properties
        System.out.println("--> Esegue chimata servizio rest esposto da nasa url : " + url);
        NasaApiResponse response = this.restTemplate().getForObject(url, NasaApiResponse.class);
        // verifica il contenuto della response se null ritorna lista vuota ( evitiamo errori a Runtime [unchecked] )
        // poi si preferisce ritornare una lista vuota.
        if (response == null || response.getCloseApproachData() == null) {
            return new ArrayList<>();
        }

        List<AsteroidPath> paths = new ArrayList<>();
        // ci si assicura il sort asc della getCloseApproachDate dei risultati recuperati dal endpoint esposti dalla NASA
        var dataList = response.getCloseApproachData().stream().
                sorted(Comparator.comparing(CloseApproachData::getCloseApproachDate)).collect(Collectors.toList());

        // inizializzazione varibili esterne
        LocalDate fromCADate=null;
        LocalDate toCADate= null;
        String fromCAPlanet=null;
        String toCAPlanet=null;

        //  iterazione sui dati in response dall'endpoint della nasa
        for (var data : dataList ) {
            LocalDate date = LocalDate.parse(data.getCloseApproachDate(), DateTimeFormatter.ISO_DATE);
            if (date.isBefore(fromDate) || date.isAfter(toDate)) {
                continue;
            }

            if(fromCADate==null){
                fromCADate = date;
            } else if (fromCADate!=null && toCADate==null) {
                toCADate = date;
            } else {
                // TODO Verificare se necessita di logicain questa parte in fase di test
            }

            // Nome del pianeta
            String planet = data.getOrbitingBody();

            if(fromCAPlanet==null){
                fromCAPlanet = planet;
            } else if (fromCAPlanet!=null && toCAPlanet==null) {
                toCAPlanet = planet;
            } else {
                // TODO .... da verificare se necesssita logica questa parte in fase di test
            }

            // se le quattro varibili sono valorizzate procede con l'if successivo
            if(fromCADate!=null && toCADate!=null && fromCAPlanet!=null && toCAPlanet!=null ){
                // se il fromCAPlanet è diverso da toCAPlanet inerisce tupla nella lista dei paths
                if( !fromCAPlanet.equalsIgnoreCase(toCAPlanet) ) {
                    paths.add(new AsteroidPath(fromCADate, toCADate, fromCAPlanet, toCAPlanet));
                    // reset variabili
                    fromCADate=null;
                    toCADate=null;
                    fromCAPlanet=null;
                    toCAPlanet=null;
                }
            }
        }
        System.out.println("--> Ritorna la lista delle occorrenze totale num : " + paths.size());
        return paths;
    }
}