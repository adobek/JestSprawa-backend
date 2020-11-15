package com.pwr.jestsprawa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Statuses")
@Data
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@AllArgsConstructor
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @NotNull
    @Column(length = 50)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "status")
    private Set<IssueStatus> issuesOfStatus;
}
