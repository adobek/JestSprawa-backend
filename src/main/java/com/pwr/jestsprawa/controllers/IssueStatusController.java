package com.pwr.jestsprawa.controllers;

import com.pwr.jestsprawa.model.GetIssueStatusDto;
import com.pwr.jestsprawa.model.IssueStatus;
import com.pwr.jestsprawa.model.AddIssueStatusDto;
import com.pwr.jestsprawa.repositories.IssueStatusRepository;
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
    public Iterable<GetIssueStatusDto> getStatusesByIssueId(@RequestParam int issueId){
        return issueStatusRepository.findAllByIssueId(issueId).stream().map(GetIssueStatusDto::fromIssueStatus)
                .collect(Collectors.toList());
    }

    @PostMapping("/issuesStatuses")
    AddIssueStatusDto newIssueStatus(@RequestBody IssueStatus newIssueStatus) {
        issueStatusRepository.save(newIssueStatus);
        return AddIssueStatusDto.fromIssueStatus(newIssueStatus);
    }
}
