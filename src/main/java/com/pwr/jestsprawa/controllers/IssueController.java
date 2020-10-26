package com.pwr.jestsprawa.controllers;

import com.pwr.jestsprawa.exceptions.IssueNotFoundException;
import com.pwr.jestsprawa.model.Issue;
import com.pwr.jestsprawa.model.IssueDto;
import com.pwr.jestsprawa.repositories.IssuesRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class IssueController {

    private final IssuesRepository issuesRepository;

    public IssueController(IssuesRepository issuesRepository) {
        this.issuesRepository = issuesRepository;
    }

    @GetMapping("/issues")
    public Iterable<IssueDto> getIssuesByStatusId(@RequestParam int statusId) {
        return issuesRepository.findAllByStatusId(statusId).stream().map(IssueDto::fromIssue).collect(Collectors.toList());
    }
    @GetMapping("/issues/{id}")
    public IssueDto getIssueById(@PathVariable int id){
        Issue issue = issuesRepository.findIssueById(id).orElseThrow(IssueNotFoundException::new);
        return IssueDto.fromIssue(issue);
    }

}
