package ar.edu.utn.frc.tup.lciii;

import ar.edu.utn.frc.tup.lciii.dtos.common.CountryDTO;
import ar.edu.utn.frc.tup.lciii.model.Country;
import ar.edu.utn.frc.tup.lciii.repository.CountryRepository;
import ar.edu.utn.frc.tup.lciii.service.CountryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ApplicationTests {


        @Mock
        private CountryRepository countryRepository;

        @Mock
        private RestTemplate restTemplate;

        @Mock
        private ModelMapper modelMapper;

        @InjectMocks
        private CountryService countryService;

        @BeforeEach
        void setUp() {
            MockitoAnnotations.openMocks(this);
        }

        @Test
        void testGetAllCountries() {
            List<Map<String, Object>> mockResponse = new ArrayList<>();
            Map<String, Object> country1 = new HashMap<>();
            country1.put("name", Map.of("common", "Argentina"));
            country1.put("population", 45000000);
            country1.put("area", 2780400.0);
            country1.put("region", "Americas");
            country1.put("cca3", "ARG");
            country1.put("borders", List.of("BOL", "BRA", "CHL", "PRY", "URY"));
            country1.put("languages", Map.of("spa", "Spanish"));
            mockResponse.add(country1);

            when(restTemplate.getForObject(anyString(), eq(List.class))).thenReturn(mockResponse);

            List<Country> result = countryService.getAllCountries();

            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals("Argentina", result.get(0).getName());
            assertEquals("ARG", result.get(0).getCode());
        }

        @Test
        void testMapToDTOList() {
            List<Country> mockCountries = new ArrayList<>();
            Country country = new Country("ARG", 400000, 45000000L, "2780400.0", "Americas", List.of("BOL", "BRA"), Map.of("spa", "Spanish"));
            mockCountries.add(country);

            when(restTemplate.getForObject(anyString(), eq(List.class))).thenReturn(new ArrayList<>());
            when(modelMapper.map(any(), eq(CountryDTO.class))).thenReturn(new CountryDTO("ARG", "Argentina"));

            List<CountryDTO> result = countryService.mapToDTOList();

            assertNotNull(result);
            assertEquals(2, result.size());
            assertEquals("Argentina", result.get(0).getName());
            assertEquals("ARG", result.get(0).getCode());
        }

        @Test
        void testGetCountryByName() {
            List<CountryDTO> mockDTOs = new ArrayList<>();
            mockDTOs.add(new CountryDTO("ARG", "Argentina"));
            mockDTOs.add(new CountryDTO("BRA", "Brazil"));

            when(restTemplate.getForObject(anyString(), eq(List.class))).thenReturn(new ArrayList<>());
            when(modelMapper.map(any(), eq(CountryDTO.class))).thenReturn(new CountryDTO("ARG", "Argentina"));

            CountryDTO result = countryService.getCountryByName("Argentina");

            assertNotNull(result);
            assertEquals("Argentina", result.getName());
            assertEquals("ARG", result.getCode());
        }

        @Test
        void testGetCountryByCode() {
            List<CountryDTO> mockDTOs = new ArrayList<>();
            mockDTOs.add(new CountryDTO("ARG", "Argentina"));
            mockDTOs.add(new CountryDTO("BRA", "Brazil"));

            when(restTemplate.getForObject(anyString(), eq(List.class))).thenReturn(new ArrayList<>());
            when(modelMapper.map(any(), eq(CountryDTO.class))).thenReturn(new CountryDTO("ARG", "Argentina"));

            CountryDTO result = countryService.getCountryByCode("ARG");

            assertNotNull(result);
            assertEquals("Argentina", result.getName());
            assertEquals("ARG", result.getCode());
        }

        @Test
        void testGetCountriesByContinent() {
            List<Map<String, Object>> mockResponse = new ArrayList<>();
            Map<String, Object> country1 = new HashMap<>();
            country1.put("name", Map.of("common", "Argentina"));
            country1.put("population", 45000000);
            country1.put("area", 2780400.0);
            country1.put("region", "Americas");
            country1.put("cca3", "ARG");
            mockResponse.add(country1);

            when(restTemplate.getForObject(anyString(), eq(List.class))).thenReturn(mockResponse);

            List<CountryDTO> result = countryService.getContriesByContinent("Americas");

            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals("Argentina", result.get(0).getName());
            assertEquals("ARG", result.get(0).getCode());
        }

        @Test
        void testGetCountriesByLanguage() {
            List<Map<String, Object>> mockResponse = new ArrayList<>();
            Map<String, Object> country1 = new HashMap<>();
            country1.put("name", Map.of("common", "Argentina"));
            country1.put("population", 45000000);
            country1.put("area", 2780400.0);
            country1.put("region", "Americas");
            country1.put("cca3", "ARG");
            country1.put("languages", Map.of("spa", "Spanish"));
            mockResponse.add(country1);

            when(restTemplate.getForObject(anyString(), eq(List.class))).thenReturn(mockResponse);

            List<CountryDTO> result = countryService.getCountriesByLanguage("Spanish");

            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals("Argentina", result.get(0).getName());
            assertEquals("ARG", result.get(0).getCode());
        }

        @Test
        void testGetCountryMostBorders() {
            List<Map<String, Object>> mockResponse = new ArrayList<>();
            Map<String, Object> country1 = new HashMap<>();
            country1.put("name", Map.of("common", "Brazil"));
            country1.put("population", 212000000);
            country1.put("area", 8515767.0);
            country1.put("region", "Americas");
            country1.put("cca3", "BRA");
            country1.put("borders", List.of("ARG", "BOL", "COL", "GUF", "GUY", "PRY", "PER", "SUR", "URY", "VEN"));
            mockResponse.add(country1);

            Map<String, Object> country2 = new HashMap<>();
            country2.put("name", Map.of("common", "Argentina"));
            country2.put("population", 45000000);
            country2.put("area", 2780400.0);
            country2.put("region", "Americas");
            country2.put("cca3", "ARG");
            country2.put("borders", List.of("BOL", "BRA", "CHL", "PRY", "URY"));
            mockResponse.add(country2);

            when(restTemplate.getForObject(anyString(), eq(List.class))).thenReturn(mockResponse);

            CountryDTO result = countryService.getCountryWithMostBorders();

            assertNotNull(result);
            assertEquals("Brazil", result.getName());
            assertEquals("BRA", result.getCode());
        }


    }


