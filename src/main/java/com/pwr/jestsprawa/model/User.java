package com.pwr.jestsprawa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Users")
@Data
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"departments", "notes", "issues", "role"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "first_name", length = 50)
    private String firstName;

    @NotNull
    @Column(name = "last_name", length = 50)
    private String lastName;

    @NotNull
    private String email;

    @JsonIgnore
    @Column(name = "password_hash", length = 60)
    private String passwordHash;

    @ManyToOne(optional = false)
    @JoinColumn(name = "role_id")
    private Role role;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Set<Issue> issues;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Set<Note> notes;

    @JsonIgnore
    @ManyToMany(mappedBy = "employees")
    private Set<Department> departments;
}
