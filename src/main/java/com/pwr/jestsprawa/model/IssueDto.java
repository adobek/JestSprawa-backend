package com.pwr.jestsprawa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.Set;

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
    private String category;
    private Integer user;
    private Integer department;
 //   private Set<String> images;
  //  private Set<String> statusesOfIssue;
  //  private Set<String> notes;

    public static IssueDto fromIssue(Issue issue){
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
                issue.getCategory().getName(),
                issue.getUser().getId(),
                issue.getDepartment().getId()
              //  issue.getImages(),
              //  issue.getStatusesOfIssue(),
              //  issue.getNotes()

        );
    }

}
