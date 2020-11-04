package com.pwr.jestsprawa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "Issues")
@Data
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@AllArgsConstructor
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @NotNull
    @Column(length = 400)
    private String description;

    @NotNull
    @Column(columnDefinition = "NUMERIC(10,6)")
    private double latitude;

    @NotNull
    @Column(columnDefinition = "NUMERIC(10,6)")
    private double longitude;

    @Column(length = 60)
    private String street;

    @Column(name = "house_number")
    private String houseNumber;

    @NotNull
    @Column(length = 6)
    private String postcode;

    @NotNull
    @Column(length = 60)
    private String locality;

    @NotNull
    @Column(name = "is_anonymous")
    private Boolean isAnonymous;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "department_id")
    private Department department;

    @JsonIgnore
    @OneToMany(mappedBy = "issue", cascade = CascadeType.ALL)
    private Set<Image> images;

    @JsonIgnore
    @OneToMany(mappedBy = "issue")
    private Set<IssueStatus> statusesOfIssue;

    @JsonIgnore
    @OneToMany(mappedBy = "issue")
    private Set<Note> notes;

}
