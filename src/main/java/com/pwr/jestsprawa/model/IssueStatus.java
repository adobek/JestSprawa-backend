package com.pwr.jestsprawa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "IssuesStatuses")
@Data
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"issue", "status"})
public class IssueStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(insertable = false)
    private LocalDateTime date;
    
    @Column(length = 400)
    private String description;

    @NonNull
    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "issue_id")
    private Issue issue;


    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "status_id")
    private Status status;

}
