package com.pwr.jestsprawa.controllers;

import com.pwr.jestsprawa.model.BasicIssueDataDto;
import com.pwr.jestsprawa.model.Category;
import com.pwr.jestsprawa.repositories.CategoryRepository;
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

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getCategoriesForDepartment(@RequestParam int departmentId){
        List<Category> categories = categoryRepository.findAllByDepartments_Id(departmentId);
        return ResponseEntity.ok(categories);
    }

}
