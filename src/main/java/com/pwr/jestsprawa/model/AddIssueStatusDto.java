package com.pwr.jestsprawa.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class AddIssueStatusDto {
    private int issueId;
    private int statusId;
    private String description;


    public static AddIssueStatusDto fromIssueStatus(IssueStatus issueStatus) {
        return new AddIssueStatusDto(
                issueStatus.getIssue().getId(),
                issueStatus.getStatus().getId(),
                issueStatus.getDescription()
        );
    }
}
