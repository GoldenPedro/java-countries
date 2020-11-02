package com.lambdaschool.countries.controllers;

import com.lambdaschool.countries.models.Country;

/**
 * Used to check if a country objects matches a certain criteria.
 * Used for filtering countries from a list.
 */
public interface CheckCountry
{
    /**
     * Returns true or false based on the implementation of the method
     *
     * @param c The country object on which the method is to operate
     * @return true or false based on the implementation of the method
     */
    boolean test(Country c);
}
