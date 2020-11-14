package com.pwr.jestsprawa.model;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Communes")
@Data
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@AllArgsConstructor
public class Commune {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "commune")
    private Set<Institution> institutions;

}
