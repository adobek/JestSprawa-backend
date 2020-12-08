package com.pwr.jestsprawa.controllers;

import com.pwr.jestsprawa.exceptions.CommuneNotFoundException;
import com.pwr.jestsprawa.model.BasicIssueDataDto;
import com.pwr.jestsprawa.model.Category;
import com.pwr.jestsprawa.model.Commune;
import com.pwr.jestsprawa.repositories.CategoryRepository;
import com.pwr.jestsprawa.repositories.CommuneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
@RequiredArgsConstructor
public class DepartmentController {

    private final CategoryRepository categoryRepository;
    private final CommuneRepository communeRepository;

    @GetMapping("/departments/{departmentId}/categories")
    public ResponseEntity<List<Category>> getCategoriesForDepartment(@PathVariable int departmentId){
        List<Category> categories = categoryRepository.findAllByDepartments_Id(departmentId);
        return ResponseEntity.ok(categories);
    }
    @GetMapping("/departments/{departmentId}/commune")
    public ResponseEntity<Commune> getCommuneForDepartment(@PathVariable int departmentId){
        Commune commune = communeRepository.findByDepartmentId(departmentId).orElseThrow(CommuneNotFoundException::new);
        return ResponseEntity.ok(commune);
    }


}
