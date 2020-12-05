package com.pwr.jestsprawa;

import com.pwr.jestsprawa.model.*;
import com.pwr.jestsprawa.repositories.*;
import com.pwr.jestsprawa.services.AzureStorageService;
import com.pwr.jestsprawa.services.IssueService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
public class IssueServiceTest {

    private final static Category CATEGORY_1 = new Category((short) 1, "kategoria1", Set.of(), Set.of());

    private final static Commune COMMUNE_1 = new Commune(1, "Gmina1", Set.of());

    private final static Institution INSTITUTION_1 = new Institution(1, "Instytucja1", COMMUNE_1, Set.of());

    private final static Department DEPARTMENT_1 = new Department(
            1,
            "Wydzial1",
            INSTITUTION_1,
            Set.of(),
            Set.of(CATEGORY_1),
            Set.of()
    );

    private final static User USER_1 = new User(
            1,
            "imie1",
            "nazwisko1",
            "email1@email.com",
            "haslo_1234",
            new Role(RoleType.ROLE_APPLICANT.getName()),
            Set.of(),
            Set.of(),
            Set.of()
    );

    private final static User USER_2 = new User(
            2,
            "imie2",
            "nazwisko2",
            "email2@email.com",
            "haslo_1234",
            new Role(RoleType.ROLE_APPLICANT.getName()),
            Set.of(),
            Set.of(),
            Set.of()
    );

    private final static User USER_3 = new User(
            3,
            "imie3",
            "nazwisko3",
            "email3@email.com",
            "haslo_1234",
            new Role(RoleType.ROLE_APPLICANT.getName()),
            Set.of(),
            Set.of(),
            Set.of()
    );

    private final static Issue ISSUE_1 = new Issue(
            1,
            LocalDateTime.of(2020, 3, 21, 14, 34),
            "opis1",
            10.3,
            53.2,
            null,
            null,
            null,
            "miejscowosc1",
            false,
            CATEGORY_1,
            USER_1,
            DEPARTMENT_1,
            Set.of(),
            Set.of(),
            Set.of()
    );

    private final static Issue ISSUE_2 = new Issue(
            2,
            LocalDateTime.of(2020, 3, 21, 14, 34),
            "opis2",
            10.3,
            53.2,
            null,
            null,
            null,
            "miejscowosc2",
            false,
            CATEGORY_1,
            USER_1,
            DEPARTMENT_1,
            Set.of(),
            Set.of(),
            Set.of()
    );

    private final static Issue ISSUE_3 = new Issue(
            3,
            LocalDateTime.of(2020, 3, 21, 14, 34),
            "opis3",
            10.3,
            53.2,
            null,
            null,
            null,
            "miejscowosc3",
            false,
            CATEGORY_1,
            USER_1,
            DEPARTMENT_1,
            Set.of(),
            Set.of(),
            Set.of()
    );

    private final static Status STATUS_1 = new Status((short) 1, "oczekująca",  Set.of());

    private final static IssueStatus ISSUE_STATUS_1 = new IssueStatus(
            1,
            LocalDateTime.now(),
            null,
            ISSUE_1,
            STATUS_1
    );

    private final static IssueStatus ISSUE_STATUS_2 = new IssueStatus(
            2,
            LocalDateTime.now(),
            null,
            ISSUE_2,
            STATUS_1
    );

    private final static IssueStatus ISSUE_STATUS_3 = new IssueStatus(
            3,
            LocalDateTime.now(),
            null,
            ISSUE_3,
            STATUS_1
    );

    private final AddIssueDto addIssueDto = new AddIssueDto(
            12.3,
            53.2,
            null,
            null,
            null,
            "miejscowosc1",
            COMMUNE_1.getName(),
            "opis1",
            false,
            CATEGORY_1.getId()
    );

    private final String URL_1 = "URL_1";

    private final String URL_2 = "URL_2";

    private final Set<Issue> firstUserIssues = Set.of(ISSUE_1, ISSUE_2);

    private final Set<Issue> secondUserIssues = Set.of(ISSUE_3);

    @Mock
    private IssuesRepository issuesRepository;

    @Mock
    private CommuneRepository communeRepository;

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private StatusRepository statusRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private IssueStatusRepository issueStatusRepository;

    @Mock
    private ImageRepository imageRepository;

    @Mock
    private AzureStorageService azureStorageService;

    @InjectMocks
    private IssueService issueService;

    @BeforeEach
    void beforeEach() {
        USER_1.setIssues(firstUserIssues);
        USER_2.setIssues(secondUserIssues);
        ISSUE_1.setStatusesOfIssue(Set.of(ISSUE_STATUS_1));
        ISSUE_2.setStatusesOfIssue(Set.of(ISSUE_STATUS_2));
        ISSUE_3.setStatusesOfIssue(Set.of(ISSUE_STATUS_3));
    }

    @Test
    void getAllUserIssuesTest() {
        when(issuesRepository.findAllByUser(USER_1)).thenReturn(new ArrayList<>(firstUserIssues));
        when(issuesRepository.findAllByUser(USER_2)).thenReturn(new ArrayList<>(secondUserIssues));
        when(issuesRepository.findAllByUser(USER_3)).thenReturn(List.of());
        List<IssueDto> userIssueDtos_1 = issueService.getMyIssues(USER_1);
        List<IssueDto> userIssueDtos_2 = issueService.getMyIssues(USER_2);
        List<IssueDto> userIssueDtos_3 = issueService.getMyIssues(USER_3);

        assertEquals(firstUserIssues.size(), userIssueDtos_1.size());
        assertTrue(userIssueDtos_1.containsAll(firstUserIssues.stream().map(IssueDto::fromIssue).collect(Collectors.toList())));

        assertEquals(secondUserIssues.size(), userIssueDtos_2.size());
        assertTrue(userIssueDtos_2.containsAll(secondUserIssues.stream().map(IssueDto::fromIssue).collect(Collectors.toList())));

        assertTrue(userIssueDtos_3.isEmpty());
    }

    @Test
    void addIssueTest() {
        MockMultipartFile multipartFile_1 = new MockMultipartFile("file_1", new byte[1]);
        MockMultipartFile multipartFile_2 = new MockMultipartFile("file_2", new byte[1]);

        when(communeRepository.findOneByNameIgnoreCase(COMMUNE_1.getName())).thenReturn(Optional.of(COMMUNE_1));
        when(departmentRepository.findByInstitution_CommuneAndCategories_Id(COMMUNE_1, CATEGORY_1.getId()))
                .thenReturn(Optional.of(DEPARTMENT_1));
        when(statusRepository.findByNameIgnoreCase(STATUS_1.getName())).thenReturn(Optional.of(STATUS_1));
        when(categoryRepository.findById(CATEGORY_1.getId())).thenReturn(Optional.of(CATEGORY_1));
        when(azureStorageService.uploadFile(multipartFile_1)).thenReturn(URI.create(URL_1));
        when(azureStorageService.uploadFile(multipartFile_2)).thenReturn(URI.create(URL_2));

        Issue addedIssue = issueService.addIssue(addIssueDto, USER_3, List.of(multipartFile_1, multipartFile_2));
        List<String> uploadedFilesUrls = addedIssue.getImages().stream().map(Image::getPath).collect(Collectors.toList());
        assertEquals(addIssueDto.getDescription(), addedIssue.getDescription());
        assertEquals(addIssueDto.getLatitude(), addedIssue.getLatitude());
        assertEquals(addIssueDto.getLongitude(), addedIssue.getLongitude());
        assertEquals(addIssueDto.getStreet(), addedIssue.getStreet());
        assertEquals(addIssueDto.getHouseNumber(), addedIssue.getHouseNumber());
        assertEquals(addIssueDto.getPostcode(), addedIssue.getPostcode());
        assertEquals(addIssueDto.getLocality(), addedIssue.getLocality());
        assertEquals(addIssueDto.getIsAnonymous(), addedIssue.getIsAnonymous());
        assertEquals(CATEGORY_1, addedIssue.getCategory());
        assertEquals(USER_3, addedIssue.getUser());
        assertThat(List.of(URL_1, URL_2)).containsExactlyInAnyOrderElementsOf(uploadedFilesUrls);
    }

}
