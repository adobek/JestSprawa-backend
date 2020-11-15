package com.pwr.jestsprawa.services;

import com.pwr.jestsprawa.exceptions.CategoryNotFoundException;
import com.pwr.jestsprawa.exceptions.CommuneNotFoundException;
import com.pwr.jestsprawa.exceptions.DepartmentNotFoundException;
import com.pwr.jestsprawa.exceptions.StatusNotFoundException;
import com.pwr.jestsprawa.model.*;
import com.pwr.jestsprawa.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IssueService {

    private final IssuesRepository issuesRepository;

    private final AzureStorageService azureStorageService;

    private final ImageRepository imageRepository;

    private final CommuneRepository communeRepository;

    private final StatusRepository statusRepository;

    private final IssueStatusRepository issueStatusRepository;

    private final DepartmentRepository departmentRepository;

    private final CategoryRepository categoryRepository;

    @Transactional
    public Issue addIssue(AddIssueDto addIssueDto, User user, List<MultipartFile> files) {
        Commune commune = communeRepository.findOneByNameIgnoreCase(addIssueDto.getCommune())
                .orElseThrow(CommuneNotFoundException::new);
        Department department = departmentRepository
                .findByInstitution_CommuneAndCategories_Id(commune, addIssueDto.getCategoryId())
                .orElseThrow(DepartmentNotFoundException::new);
        Status waitingStatus = statusRepository.findByNameIgnoreCase("oczekująca")
                .orElseThrow(StatusNotFoundException::new);
        Category category = categoryRepository.findById(addIssueDto.getCategoryId())
                .orElseThrow(CategoryNotFoundException::new);

        Issue issue = new Issue();
        issue.setCreationDate(LocalDateTime.now());
        issue.setDescription(addIssueDto.getDescription());
        issue.setLatitude(addIssueDto.getLatitude());
        issue.setLongitude(addIssueDto.getLongitude());
        issue.setStreet(addIssueDto.getStreet());
        issue.setHouseNumber(addIssueDto.getHouseNumber());
        issue.setPostcode(addIssueDto.getPostcode());
        issue.setLocality(addIssueDto.getLocality());
        issue.setIsAnonymous(addIssueDto.getIsAnonymous());
        issue.setUser(user);
        issue.setDepartment(department);
        issue.setCategory(category);
        issuesRepository.save(issue);

        Set<Image> images = new HashSet<>();
        for (MultipartFile file : files) {
            URI uri = azureStorageService.uploadFile(file);
            Image image = new Image();
            image.setPath(uri.toString());
            image.setIssue(issue);
            imageRepository.save(image);
            images.add(image);
        }

        IssueStatus issueStatus = new IssueStatus();
        issueStatus.setDate(LocalDateTime.now());
        issueStatus.setStatus(waitingStatus);
        issueStatus.setIssue(issue);
        issueStatusRepository.save(issueStatus);

        issue.setImages(images);
        issue.setStatusesOfIssue(Collections.singleton(issueStatus));
        return issue;
    }

    public List<BasicIssueDataDto> getAllIssuesBasicData() {
        List<Issue> issues = issuesRepository.findAll();
        return issues.stream()
                .map(BasicIssueDataDto::fromIssue).collect(Collectors.toList());
    }

    public List<IssueDto> getMyIssues(User user) {
        return issuesRepository.findAllByUser(user)
                .stream().map(IssueDto::fromIssue).collect(Collectors.toList());
    }

}
