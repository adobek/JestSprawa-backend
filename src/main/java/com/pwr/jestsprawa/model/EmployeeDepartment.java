package com.pwr.jestsprawa.model;

import com.pwr.jestsprawa.model.id.EmployeeDepartmentId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "EmployeesDepartments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(EmployeeDepartmentId.class)
public class EmployeeDepartment {

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "department_id")
    private Department department;

}
