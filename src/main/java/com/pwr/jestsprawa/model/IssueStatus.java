package com.pwr.jestsprawa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "IssuesStatuses")
@Data
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@AllArgsConstructor
public class IssueStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime date;
    
    @Column(length = 400)
    private String description;

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "issue_id")
    private Issue issue;

    @ManyToOne(optional = false)
    @JoinColumn(name = "status_id")
    private Status status;

}
