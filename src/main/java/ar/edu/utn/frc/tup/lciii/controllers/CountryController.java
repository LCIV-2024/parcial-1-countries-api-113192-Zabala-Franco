package ar.edu.utn.frc.tup.lciii.controllers;


import ar.edu.utn.frc.tup.lciii.dtos.common.CountryDTO;
import ar.edu.utn.frc.tup.lciii.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CountryController {
    @Autowired
    private CountryService countryService;

    @GetMapping("/countries")
    public ResponseEntity<List<CountryDTO>> getAllCountries() {
        return ResponseEntity.ok(countryService.mapToDTOList());
    }
    @GetMapping("/countries/{country}")
    public ResponseEntity<CountryDTO> getCountryByName(@PathVariable(name = "country") String country) {
        return ResponseEntity.ok(countryService.getCountryByName(country));
    }
    @GetMapping("/countries/{code}")
    public ResponseEntity<CountryDTO> getCountryByCode(@PathVariable(name = "code") String code) {
        return ResponseEntity.ok(countryService.getCountryByCode(code));
    }
    @GetMapping("countries/{continent}/continent")
    public ResponseEntity<List<CountryDTO>> getContriesByContinent(@PathVariable(value = "continent") String continent) {
        return ResponseEntity.ok(countryService.getContriesByContinent(continent));
    }
    @GetMapping("/countries/{language}/language")
    public ResponseEntity<List<CountryDTO>> getCountriesByLanguage(@PathVariable(value = "language") String language) {
        return ResponseEntity.ok(countryService.getCountriesByLanguage(language));
    }
    @GetMapping("/countries/most-borders")
    public ResponseEntity<CountryDTO> getCountryMostBorders() {
        return ResponseEntity.ok(countryService.getCountryWithMostBorders());
    }
}