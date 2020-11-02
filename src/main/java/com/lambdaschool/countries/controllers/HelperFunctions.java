package com.lambdaschool.countries.controllers;

import com.lambdaschool.countries.models.Country;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains functions that are used throughout the application
 */
public class HelperFunctions
{
    /**
     * Taking a list and lambda expression, return a list of Countries that pass the filter represented by the lambda expression
     * @param fullList The list we wish to filter
     * @param tester the Lambda Expression of how we are going to filter
     *
     * @return The filtered list
     */
    public static List<Country> findCountries(List<Country> fullList, CheckCountry tester)
    {
        List<Country> tempCountry = new ArrayList<>();

        for (Country c : fullList)
        {
            if (tester.test(c))
            {
                tempCountry.add(c);
            }
        }

        return tempCountry;
    }
}