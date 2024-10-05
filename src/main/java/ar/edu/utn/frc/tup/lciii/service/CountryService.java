package ar.edu.utn.frc.tup.lciii.service;

import ar.edu.utn.frc.tup.lciii.model.Country;
import ar.edu.utn.frc.tup.lciii.dtos.common.CountryDTO;
import ar.edu.utn.frc.tup.lciii.repository.CountryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
//@RequiredArgsConstructor
public class CountryService {
        @Autowired
        private  CountryRepository countryRepository;
        @Autowired
        private RestTemplate restTemplate;
        @Autowired
        private ModelMapper modelMapper;

        public List<Country> getAllCountries() {
                String url = "https://restcountries.com/v3.1/all";
                List<Map<String, Object>> response = restTemplate.getForObject(url, List.class);
                return response.stream().map(this::mapToCountry).collect(Collectors.toList());
        }

        /**
         * Agregar mapeo de campo cca3 (String)
         * Agregar mapeo campos borders ((List<String>))
         */
        private Country mapToCountry(Map<String, Object> countryData) {
                Map<String, Object> nameData = (Map<String, Object>) countryData.get("name");
                return Country.builder()
                        .name((String) nameData.get("common"))
                        .population(((Number) countryData.get("population")).longValue())
                        .area(((Number) countryData.get("area")).doubleValue())
                        .region((String) countryData.get("region"))
                        .code((String) countryData.get("cca3"))
                        .borders((List<String>) countryData.get("borders"))
                        .languages((Map<String, String>) countryData.get("languages"))
                        .build();
        }

        private CountryDTO mapToDTO(Country country) {
                return new CountryDTO(country.getCode(), country.getName());
        }

        public List<CountryDTO> mapToDTOList() {
                List<Country> countries = getAllCountries();
                List<CountryDTO> response = new ArrayList<>();
                countries.forEach(c -> {
                        response.add(modelMapper.map(c, CountryDTO.class));
                        response.add(mapToDTO(c));
                });
                return response;
        }
        public CountryDTO getCountryByName(String country) {
                List<CountryDTO> allCountries = mapToDTOList();
                for (CountryDTO c : allCountries) {
                        if (c.getName().equals(country)) {
                                return c;
                        }
                }
                return null;
        }


        public CountryDTO getCountryByCode(String code) {
                return getAllCountries().stream()
                        .filter(c -> c.getCode().equalsIgnoreCase(code))
                        .findFirst()
                        .map(this::mapToDTO)
                        .orElse(null);
        }
        public List<CountryDTO> getContriesByContinent(String continent) {
                List<CountryDTO> response = new ArrayList<>();
                List<Country> allCountries = getAllCountries();

                for (Country c : allCountries) {
                        if (c.getRegion().equals(continent)) {
                                response.add(mapToDTO(c));
                        }
                }

                return response;
        }
        public List<CountryDTO> getCountriesByLanguage(String language) {
                return getAllCountries().stream()
                        .filter(c -> c.getLanguages().containsValue(language))
                        .map(this::mapToDTO)
                        .collect(Collectors.toList());
                        }



        public CountryDTO getCountryWithMostBorders() {
                return getAllCountries().stream()
                        .max(Comparator.comparingInt(c -> c.getBorders().size()))
                        .map(this::mapToDTO)
                        .orElse(null);
        }
}