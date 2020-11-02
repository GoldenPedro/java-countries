package com.lambdaschool.countries.controllers;

import com.lambdaschool.countries.CountriesProjectApplication;
import com.lambdaschool.countries.models.Country;
import com.lambdaschool.countries.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * The entry point for clients wishing to access country data based on population.
 */
@RestController
public class CountryController
{
    /**
     * Connects this class to the Country Repository
     */
    @Autowired
    CountryRepository countryrepos;

    /**
     * Returns to the console, the total population of all the countries
     * <br>Example: <a href="http://localhost:8080/population/total">http://localhost:8080/population/total</a>
     *
     * @return Status of OK plus the total population of all countries in the console
     */
    @GetMapping(value = "/population/total",
            produces = {"application/json"})
    public ResponseEntity<?> findLargerPopulations()
    {
        List<Country> myList = new ArrayList<>();
        countryrepos.findAll().iterator().forEachRemaining(myList::add);

        long total = 0;

        for (Country c : myList)
        {
            total = total + c.getPopulation();
        }

        System.out.println("The Total Population is " + total);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Returns the country with the smallest population
     * <br>Example: <a href="http://localhost:8080/population/min">http://localhost:8080/population/min</a>
     *
     * @return JSON of the country with the smallest population
     */
    @GetMapping(value = "/population/min",
            produces = {"application/json"})
    public ResponseEntity<?> findMinPopulation()
    {
        List<Country> myList = new ArrayList<>();
        countryrepos.findAll().iterator().forEachRemaining(myList::add);

        myList.sort((c1, c2) -> (int) (c1.getPopulation() - c2.getPopulation()));

        Country rtnCountry = myList.get(0);
        return new ResponseEntity<>(rtnCountry,
                HttpStatus.OK);
    }

    /**
     * Return the country with the largest population
     * <br>Example: <a href="http://localhost:8080/population/max">http://localhost:8080/population/max</a>
     *
     * @return JSON of the country with the largest population
     */
    @GetMapping(value = "/population/max",
            produces = {"application/json"})
    public ResponseEntity<?> findMaxPopulation()
    {
        List<Country> myList = new ArrayList<>();
        countryrepos.findAll().iterator().forEachRemaining(myList::add);

        myList.sort((c1, c2) -> (int) (c2.getPopulation() - c1.getPopulation()));

        Country rtnCountry = myList.get(0);
        return new ResponseEntity<>(rtnCountry,
                HttpStatus.OK);
    }

    /**
     * Returns the country with the median population
     * <br>Example: <a href="http://localhost:8080/population/median">http://localhost:8080/population/median</a>
     *
     * @return JSON of the country with the median population
     */
    @GetMapping(value = "/population/median",
            produces = {"application/json"})
    public ResponseEntity<?> findMedianPopulation()
    {
        List<Country> myList = new ArrayList<>();
        countryrepos.findAll().iterator().forEachRemaining(myList::add);

        myList.sort((c1, c2) -> (int) (c1.getPopulation() - c2.getPopulation()));

        Country rtnCountry = myList.get((myList.size() / 2) + 1);
        return new ResponseEntity<>(rtnCountry,
                HttpStatus.OK);
    }

    /**
     * Returns a list of all countries sorted by name
     * <br>Example: <a href="http://localhost:8080/names/all">http://localhost:8080/names/all</a>
     *
     * @return JSON list of all countries sorted alphabetically by name with a status of OK
     */
    @GetMapping(value = "/names/all",
            produces = {"application/json"})
    public ResponseEntity<?> getAllCountries()
    {
        List<Country> myList = new ArrayList<>();
        countryrepos.findAll().iterator().forEachRemaining(myList::add);

        myList.sort((c1, c2) -> c1.getName()
                .compareToIgnoreCase(c2.getName()));

        return new ResponseEntity<>(myList,
                HttpStatus.OK);
    }

    /**
     * Returns a list of all countries whose name begin with the given letter, case insensitive, ordered alphabetically
     * <br>Example: <a href="http://localhost:8080/names/start/u">http://localhost:8080/names/start/u</a>
     *
     * @param letter the first letter of the name of the countries you seek
     * @return JSON list of all countries whose name begin with the given letter, case insensitive, ordered alphabetically
     */
    @GetMapping(value = "/names/start/{letter}",
            produces = {"application/json"})
    public ResponseEntity<?> getCountriesWithStartLetter(
            @PathVariable
                    char letter)
    {
        List<Country> myList = new ArrayList<>();
        countryrepos.findAll().iterator().forEachRemaining(myList::add);

        List<Country> rtnList = HelperFunctions.findCountries(myList, c -> c.getName()
                .toUpperCase()
                .charAt(0) == Character.toUpperCase(letter));

        rtnList.sort((c1, c2) -> c1.getName()
                .compareToIgnoreCase(c2.getName()));

        return new ResponseEntity<>(rtnList,
                HttpStatus.OK);
    }
}
