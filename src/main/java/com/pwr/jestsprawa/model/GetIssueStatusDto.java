package com.pwr.jestsprawa.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class GetIssueStatusDto {
    private int issueId;
    private String status;
    private String description;
    private LocalDateTime date;


    public static GetIssueStatusDto fromIssueStatus(IssueStatus issueStatus) {
        return new GetIssueStatusDto(
                issueStatus.getIssue().getId(),
                issueStatus.getStatus().getName(),
                issueStatus.getDescription(),
                issueStatus.getDate()
        );
    }
}
