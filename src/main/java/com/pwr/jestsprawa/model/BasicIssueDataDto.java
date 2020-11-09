package com.pwr.jestsprawa.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class BasicIssueDataDto {

    private double latitude;

    private double longitude;

    private Category category;

    public static BasicIssueDataDto fromIssue(Issue issue) {
        BasicIssueDataDto basicIssueDataDto = new BasicIssueDataDto();
        basicIssueDataDto.setLatitude(issue.getLatitude());
        basicIssueDataDto.setLongitude(issue.getLongitude());
        basicIssueDataDto.setCategory(issue.getCategory());
        return basicIssueDataDto;
    }

}
