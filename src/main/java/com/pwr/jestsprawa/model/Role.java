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
@Table(name = "Roles")
@Data
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    private Short id;

    @NotNull
    @Column(length = 30)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "role")
    private Set<User> users;

}
