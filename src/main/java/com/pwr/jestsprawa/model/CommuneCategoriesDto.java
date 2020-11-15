package com.pwr.jestsprawa.model;

import lombok.Data;

import java.util.List;

@Data
public class CommuneCategoriesDto {

    private List<Category> availableCategories;

    private List<Category> allCategories;

}
