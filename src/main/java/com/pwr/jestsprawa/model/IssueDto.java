package com.pwr.jestsprawa.model;

import com.pwr.jestsprawa.exceptions.IssueStatusNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class IssueDto {
    private Integer id;
    private LocalDateTime creationDate;
    private String description;
    private double latitude;
    private double longitude;
    private String street;
    private String houseNumber;
    private String postcode;
    private String locality;
    private Boolean isAnonymous;
    private Category category;
    private User user;
    private Department department;
    private Set<String> images;
    private IssueStatus currentIssueStatus;

    public static IssueDto fromIssue(Issue issue){
        IssueStatus issueStatus = issue.getStatusesOfIssue()
                .stream()
                .max(Comparator.comparing(IssueStatus::getDate))
                .orElseThrow(IssueStatusNotFoundException::new);

        return new IssueDto(
                issue.getId(),
                issue.getCreationDate(),
                issue.getDescription(),
                issue.getLatitude(),
                issue.getLongitude(),
                issue.getStreet(),
                issue.getHouseNumber(),
                issue.getPostcode(),
                issue.getLocality(),
                issue.getIsAnonymous(),
                issue.getCategory(),
                issue.getUser(),
                issue.getDepartment(),
                issue.getImages().stream().map(Image::getPath).collect(Collectors.toSet()),
                issueStatus
              //  issue.getStatusesOfIssue(),
              //  issue.getNotes()

        );
    }

}
