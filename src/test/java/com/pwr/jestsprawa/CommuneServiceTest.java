package com.pwr.jestsprawa;

import com.pwr.jestsprawa.exceptions.CommuneNotFoundException;
import com.pwr.jestsprawa.exceptions.DepartmentNotFoundException;
import com.pwr.jestsprawa.model.*;
import com.pwr.jestsprawa.repositories.CategoryRepository;
import com.pwr.jestsprawa.repositories.CommuneRepository;
import com.pwr.jestsprawa.repositories.DepartmentRepository;
import com.pwr.jestsprawa.services.CommuneService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CommuneServiceTest {

    private final static Category CATEGORY_1 = new Category((short) 1, "kategoria1", Set.of(), Set.of());

    private final static Category CATEGORY_2 = new Category((short) 2, "kategoria2", Set.of(), Set.of());

    private final static Category CATEGORY_3 = new Category((short) 3, "kategoria3", Set.of(), Set.of());

    private final static Category CATEGORY_4 = new Category((short) 4, "kategoria4", Set.of(), Set.of());

    private final static Commune COMMUNE_1 = new Commune(1, "Gmina1", Set.of());

    private final static Commune COMMUNE_2 = new Commune(2, "Gmina2", Set.of());

    private final static Commune COMMUNE_3 = new Commune(3, "Gmina3", Set.of());

    private final static Commune COMMUNE_4 = new Commune(4, "Gmina4", Set.of());

    private final static Institution INSTITUTION_1 = new Institution(1, "Instytucja1", COMMUNE_1, Set.of());

    private final static Institution INSTITUTION_2 = new Institution(2, "Instytucja2", COMMUNE_2, Set.of());

    private final static Institution INSTITUTION_3 = new Institution(3, "Instytucja3", COMMUNE_2, Set.of());

    private final static Department DEPARTMENT_1 = new Department(
            1,
            "Wydzial1",
            INSTITUTION_1,
            Set.of(),
            Set.of(CATEGORY_1),
            Set.of()
    );

    private final static Department DEPARTMENT_2 = new Department(
            2,
            "Wydzial2",
            INSTITUTION_1,
            Set.of(),
            Set.of(CATEGORY_2, CATEGORY_3),
            Set.of()
    );

    private final static Department DEPARTMENT_3 = new Department(
            3,
            "Wydzial3",
            INSTITUTION_2,
            Set.of(),
            Set.of(CATEGORY_2, CATEGORY_3),
            Set.of()
    );

    private final static Department DEPARTMENT_4 = new Department(
            4,
            "Wydzial2",
            INSTITUTION_3,
            Set.of(),
            Set.of(CATEGORY_4),
            Set.of()
    );

    @Mock
    private CommuneRepository communeRepository;

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CommuneService communeService;

    @BeforeAll
    static void beforeAll() {
        COMMUNE_1.setInstitutions(Set.of(INSTITUTION_1));
        COMMUNE_2.setInstitutions(Set.of(INSTITUTION_2, INSTITUTION_3));
        INSTITUTION_1.setDepartments(Set.of(DEPARTMENT_1, DEPARTMENT_2));
        INSTITUTION_2.setDepartments(Set.of(DEPARTMENT_3));
        INSTITUTION_3.setDepartments(Set.of(DEPARTMENT_4));
    }

    @Test
    void getCategoriesInCommuneTest() {
        when(communeRepository.findOneByNameIgnoreCase(COMMUNE_1.getName())).thenReturn(Optional.of(COMMUNE_1));
        when(communeRepository.findOneByNameIgnoreCase(COMMUNE_2.getName())).thenReturn(Optional.of(COMMUNE_2));
        when(communeRepository.findOneByNameIgnoreCase(COMMUNE_3.getName())).thenThrow(CommuneNotFoundException.class);
        when(communeRepository.findOneByNameIgnoreCase(COMMUNE_4.getName())).thenReturn(Optional.of(COMMUNE_4));
        when(departmentRepository.findAllByInstitution_Commune(COMMUNE_1)).thenReturn(List.of(DEPARTMENT_1, DEPARTMENT_2));
        when(departmentRepository.findAllByInstitution_Commune(COMMUNE_2)).thenReturn(List.of(DEPARTMENT_3, DEPARTMENT_4));
        when(departmentRepository.findAllByInstitution_Commune(COMMUNE_4)).thenThrow(DepartmentNotFoundException.class);
        when(categoryRepository.findAll()).thenReturn(List.of(CATEGORY_1, CATEGORY_2, CATEGORY_3, CATEGORY_4));

        CommuneCategoriesDto communeCategoriesDto_1 = communeService.getCategoriesInCommune(COMMUNE_1.getName());
        CommuneCategoriesDto communeCategoriesDto_2 = communeService.getCategoriesInCommune(COMMUNE_2.getName());

        assertThat(List.of(CATEGORY_1, CATEGORY_2, CATEGORY_3))
                .containsExactlyInAnyOrderElementsOf(communeCategoriesDto_1.getAvailableCategories());

        assertThat(List.of(CATEGORY_2, CATEGORY_3, CATEGORY_4))
                .containsExactlyInAnyOrderElementsOf(communeCategoriesDto_2.getAvailableCategories());

        assertThrows(CommuneNotFoundException.class, () -> communeService.getCategoriesInCommune(COMMUNE_3.getName()));
        assertThrows(DepartmentNotFoundException.class, () -> communeService.getCategoriesInCommune(COMMUNE_4.getName()));
    }

}
