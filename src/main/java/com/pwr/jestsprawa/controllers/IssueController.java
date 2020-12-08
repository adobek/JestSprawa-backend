package com.pwr.jestsprawa.controllers;

import com.pwr.jestsprawa.exceptions.*;
import com.pwr.jestsprawa.model.*;
import com.pwr.jestsprawa.repositories.IssuesRepository;
import com.pwr.jestsprawa.repositories.UserRepository;
import com.pwr.jestsprawa.services.IssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import javax.validation.Valid;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin
@RequiredArgsConstructor
public class IssueController {

    private final IssuesRepository issuesRepository;

    private final IssueService issueService;

    private final UserRepository userRepository;

    @GetMapping("/issues")
    public Iterable<IssueDto> getIssuesByStatusId(@RequestParam int statusId) {
        return issuesRepository.findAllByStatusId(statusId).stream().map(IssueDto::fromIssue).collect(Collectors.toList());
    }

    @GetMapping("/issues/basic")
    public ResponseEntity<List<BasicIssueDataDto>> getAllIssuesBasicData() {
        List<BasicIssueDataDto> issues = issueService.getAllIssuesBasicData();
        return ResponseEntity.ok(issues);
    }

    @GetMapping("/issues/{id}")
    public IssueDto getIssueById(@PathVariable int id){
        Issue issue = issuesRepository.findIssueById(id).orElseThrow(IssueNotFoundException::new);
        return IssueDto.fromIssue(issue);
    }
    @PutMapping("/issues/{id}")
    IssueDto updateIssue(@RequestBody UpdateIssueDto newIssue, @PathVariable int id) {
        Issue issue = issuesRepository.findIssueById(id).orElseThrow(IssueNotFoundException::new);

        issue.setCategory(newIssue.getCategory());
        issue.setDepartment(newIssue.getDepartment());
        issuesRepository.save(issue);
        return IssueDto.fromIssue(issue);
    }

    @PostMapping("/issues/add")
    public ResponseEntity<IssueDto> addIssue(@RequestPart AddIssueDto addIssueDto, @RequestPart List<MultipartFile> files, Authentication authentication) {
        User user = userRepository.findOneByEmailIgnoreCase(authentication.getName())
                .orElseThrow(UserNotFoundException::new);
        Issue issue = issueService.addIssue(addIssueDto, user, files);
        return ResponseEntity.ok(IssueDto.fromIssue(issue));
    }

    @GetMapping("/issues/me")
    public ResponseEntity<List<IssueDto>> getMyIssues(Authentication authentication) {
        User user = userRepository.findOneByEmailIgnoreCase(authentication.getName())
                .orElseThrow(UserNotFoundException::new);
        List<IssueDto> myIssues = issueService.getMyIssues(user);
        return ResponseEntity.ok(myIssues);
    }

    @ExceptionHandler(UploadFileException.class)
    public ResponseEntity<Object> handleUploadFileException(UploadFileException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CommuneNotFoundException.class)
    public ResponseEntity<Object> handleCommuneNotFoundException(CommuneNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<Object> handleDepartmentNotFoundException(DepartmentNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(StatusNotFoundException.class)
    public ResponseEntity<Object> handleStatusNotFoundException(StatusNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<Object> handleCategoryNotFoundException(CategoryNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

}
