package ar.edu.utn.frc.tup.lciii.repository;

import ar.edu.utn.frc.tup.lciii.entities.CountryEntity;
import ar.edu.utn.frc.tup.lciii.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Completar segun corresponda
 */

//@Service
@Repository
public interface CountryRepository extends JpaRepository<CountryEntity, Integer> {

    Country findByName(String name);
    Country findByCode(String code);

//    List<Country> findByNameContainingIgnoreCase(String name);
//    List<Country> findByCodeContainingIgnoreCase(String code);
//    List<Country> findByContinentContainingIgnoreCase(String continent);
//    List<Country> findByLanguagesContainingIgnoreCase(String language);
}
