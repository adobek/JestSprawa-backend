package com.pwr.jestsprawa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Categories")
@Data
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    private Short id;

    @NotNull
    @Column(length = 40)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private Set<Issue> issues;

    @JsonIgnore
    @ManyToMany(mappedBy = "categories")
    private Set<Department> departments;
}
