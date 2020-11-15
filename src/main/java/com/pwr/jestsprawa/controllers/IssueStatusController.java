package com.pwr.jestsprawa.controllers;

import com.pwr.jestsprawa.model.*;
import com.pwr.jestsprawa.repositories.IssueStatusRepository;
import com.pwr.jestsprawa.repositories.IssuesRepository;
import com.pwr.jestsprawa.repositories.StatusRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class IssueStatusController {
    private final IssueStatusRepository issueStatusRepository;
    private final IssuesRepository issueRepository;
    private final StatusRepository statusRepository;

    public IssueStatusController(IssueStatusRepository issueStatusRepository, IssuesRepository issueRepository, StatusRepository statusRepository) {
        this.issueStatusRepository = issueStatusRepository;
        this.issueRepository = issueRepository;
        this.statusRepository = statusRepository;
    }
    
    @GetMapping("/issuesStatuses")
    public Iterable<GetIssueStatusDto> getStatusesByIssueId(@RequestParam int issueId){
        return issueStatusRepository.findAllByIssueId(issueId).stream().map(GetIssueStatusDto::fromIssueStatus)
                .collect(Collectors.toList());
    }

    @GetMapping("/issuesStatuses/issue")
    public GetIssueStatusDto getIssueStatus(@RequestParam int issueId){
        IssueStatus issueStatus = issueStatusRepository.findByIssueId(issueId);
        return GetIssueStatusDto.fromIssueStatus(issueStatus);

    }

    @PostMapping("/issuesStatuses")
    AddIssueStatusDto newIssueStatus(@RequestBody GetIssueStatusDto newIssueStatus) {
        Optional<Issue> issue = issueRepository.findIssueById(newIssueStatus.getIssueId());
        Optional<Status> status = statusRepository.findById(newIssueStatus.getIssueStatusId());
        Issue currentIssue = issue.orElseThrow();
        Status newStatus = status.orElseThrow();
        IssueStatus issueStatus = new IssueStatus(currentIssue, newStatus);
        issueStatus = issueStatusRepository.save(issueStatus);
        return AddIssueStatusDto.fromIssueStatus(issueStatus);
    }
}
