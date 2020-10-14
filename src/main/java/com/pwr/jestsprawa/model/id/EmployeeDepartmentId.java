package com.pwr.jestsprawa.model.id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDepartmentId implements Serializable {

    private Integer user;

    private Integer department;

}
