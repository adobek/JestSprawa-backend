package com.pwr.jestsprawa.model;

import com.pwr.jestsprawa.exceptions.IssueStatusNotFoundException;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Comparator;

@Data
@RequiredArgsConstructor
public class BasicIssueDataDto {

    private int id;

    private double latitude;

    private double longitude;

    private Category category;

    private IssueStatus currentIssueStatus;

    private LocalDateTime creationDate;

    public static BasicIssueDataDto fromIssue(Issue issue) {
        IssueStatus currentIssueStatus = issue.getStatusesOfIssue()
                .stream()
                .max(Comparator.comparing(IssueStatus::getDate))
                .orElseThrow(IssueStatusNotFoundException::new);
        BasicIssueDataDto basicIssueDataDto = new BasicIssueDataDto();
        basicIssueDataDto.setId(issue.getId());
        basicIssueDataDto.setLatitude(issue.getLatitude());
        basicIssueDataDto.setLongitude(issue.getLongitude());
        basicIssueDataDto.setCategory(issue.getCategory());
        basicIssueDataDto.setCurrentIssueStatus(currentIssueStatus);
        basicIssueDataDto.setCreationDate(issue.getCreationDate());
        return basicIssueDataDto;
    }

}
