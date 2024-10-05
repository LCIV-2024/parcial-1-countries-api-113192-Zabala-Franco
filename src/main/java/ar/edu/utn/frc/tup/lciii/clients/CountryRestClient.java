package ar.edu.utn.frc.tup.lciii.clients;

import ar.edu.utn.frc.tup.lciii.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CountryRestClient {


    @Autowired
    private RestTemplate restTemplate;

    String baseResourceUrl = "https://restcountries.com/v3.1/all";

    public ResponseEntity<Country[]> getAllCountries(){
        return restTemplate.getForEntity(baseResourceUrl, Country[].class);
    }

      public ResponseEntity<Country[]> getContryByName(String name){
        return restTemplate.getForEntity(baseResourceUrl + "?name=" + name, Country[].class);
    }


}

