package com.pwr.jestsprawa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Departments")
@Data
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    @Id
    private Integer id;

    @Column(length = 150)
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "institution_id")
    private Institution institution;

    @JsonIgnore
    @OneToMany(mappedBy = "department")
    private Set<Issue> issues;

    @JsonIgnore
    @OneToMany(mappedBy = "department")
    private Set<DepartmentCategory> categoriesOfDepartment;

}
