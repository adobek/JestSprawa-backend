package com.pwr.jestsprawa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Images")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 260)
    private String path;

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "issue_id")
    private Issue issue;
}
