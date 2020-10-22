package com.pwr.jestsprawa.model;

import com.pwr.jestsprawa.model.id.ReactionId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Reactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ReactionId.class)
public class Reaction {

    @Id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "issue_id")
    private Issue issue;

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

}
