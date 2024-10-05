package ar.edu.utn.frc.tup.lciii.repository;

import ar.edu.utn.frc.tup.lciii.entities.CountryEntity;
import ar.edu.utn.frc.tup.lciii.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Completar segun corresponda
 */

//@Service
@Repository
public interface CountryRepository extends JpaRepository<CountryEntity, Integer> {

    Country findByName(String name);
    Country findByCode(String code);

}
