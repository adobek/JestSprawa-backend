package com.pwr.jestsprawa.model.nominatim;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class NominatimAddress implements Serializable {

    private String street;

    private String road;

    private String city;

    private String town;

    private String village;

    private String hamlet;

    private String municipality;

    private String house_number;

    private String postcode;

    private String commune;

    private String country;

}
