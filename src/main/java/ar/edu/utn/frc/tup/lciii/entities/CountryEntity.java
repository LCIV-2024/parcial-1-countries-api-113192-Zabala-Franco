package ar.edu.utn.frc.tup.lciii.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Map;

@Entity
@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "countries")
public class CountryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private long population;
    private double area;
    private String code;
//    private String region;
//    private List<String> borders;
//    private Map<String, String> languages;
}
