package com.pwr.jestsprawa.controllers;

import com.pwr.jestsprawa.model.IssueDto;
import com.pwr.jestsprawa.model.IssueStatus;
import com.pwr.jestsprawa.model.IssueStatusDto;
import com.pwr.jestsprawa.repositories.IssueStatusRepository;
import com.pwr.jestsprawa.repositories.IssuesRepository;
import org.springframework.web.bind.annotation.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class IssueStatusController {
    private final IssueStatusRepository issueStatusRepository;
    public IssueStatusController(IssueStatusRepository issueStatusRepository) {
        this.issueStatusRepository = issueStatusRepository;
    }
    @GetMapping("/issuesStatuses")
    public Iterable<IssueStatusDto> getIssuesStatuses(){
        return issueStatusRepository.findAll().stream().map(IssueStatusDto::fromIssueStatus).collect(Collectors.toList());
    }
    @PostMapping("/issuesStatuses")
    IssueStatusDto newIssueStatus(@RequestBody IssueStatus newIssueStatus) {
        issueStatusRepository.save(newIssueStatus);
        return IssueStatusDto.fromIssueStatus(newIssueStatus);
    }
}
