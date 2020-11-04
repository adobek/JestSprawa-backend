package com.pwr.jestsprawa.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class AddIssueDto {

    @NotNull
    private double latitude;

    @NotNull
    private double longitude;

    private String street;

    private String houseNumber;

    private String postcode;

    @NotEmpty
    private String locality;

    @NotEmpty
    private String commune;

    @NotEmpty
    private String description;

    @NotNull
    private Boolean isAnonymous;

    @NotNull
    private Category category;

}
