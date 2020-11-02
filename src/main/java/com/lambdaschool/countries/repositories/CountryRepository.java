package com.lambdaschool.countries.repositories;

import com.lambdaschool.countries.models.Country;
import org.springframework.data.repository.CrudRepository;

/**
 * Connects the rest of the Java application to the Country table
 */
public interface CountryRepository extends CrudRepository<Country, Long>
{
}
