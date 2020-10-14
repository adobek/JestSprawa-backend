package com.pwr.jestsprawa.model.id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReactionId implements Serializable {

    private Integer issue;

    private Integer user;

}
