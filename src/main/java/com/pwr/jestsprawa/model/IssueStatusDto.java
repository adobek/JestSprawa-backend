package com.pwr.jestsprawa.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class IssueStatusDto {
    private int issueId;
    private int statusId;
    private String description;
    private LocalDateTime date;

    public static IssueStatusDto fromIssueStatus(IssueStatus issueStatus) {
        return new IssueStatusDto(
                issueStatus.getIssue().getId(),
                issueStatus.getStatus().getId(),
                issueStatus.getDescription(),
                issueStatus.getDate()
        );
    }
}
