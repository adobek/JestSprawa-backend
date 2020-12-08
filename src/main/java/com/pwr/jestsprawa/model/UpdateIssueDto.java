package com.pwr.jestsprawa.model;

import com.pwr.jestsprawa.exceptions.IssueStatusNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Comparator;

@Data
@AllArgsConstructor

public class UpdateIssueDto {
    private Department department;
    private Category category;

    public static UpdateIssueDto fromIssue(Issue issue) {
        return new UpdateIssueDto(
                issue.getDepartment(),
                issue.getCategory()
        );
    }
}
