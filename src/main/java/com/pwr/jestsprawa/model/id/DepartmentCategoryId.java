package com.pwr.jestsprawa.model.id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentCategoryId implements Serializable {

    private Integer department;

    private short category;

}
