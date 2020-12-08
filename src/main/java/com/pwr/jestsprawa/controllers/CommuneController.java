package com.pwr.jestsprawa.controllers;


import com.pwr.jestsprawa.exceptions.CommuneNotFoundException;
import com.pwr.jestsprawa.model.Commune;
import com.pwr.jestsprawa.model.CommuneCategoriesDto;
import com.pwr.jestsprawa.model.Department;
import com.pwr.jestsprawa.model.LocationDataDto;
import com.pwr.jestsprawa.repositories.CommuneRepository;
import com.pwr.jestsprawa.repositories.DepartmentRepository;
import com.pwr.jestsprawa.services.CommuneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommuneController {

    private final CommuneService communeService;
    private final CommuneRepository communeRepository;
    private final DepartmentRepository departmentRepository;

    @GetMapping("/commune")
    public ResponseEntity<LocationDataDto> getLocationData(@RequestParam("lat") double latitude, @RequestParam("lon") double longitude) {
        LocationDataDto locationDataDto = communeService.getLocationData(latitude, longitude);
        return ResponseEntity.ok(locationDataDto);
    }

    @GetMapping("/communes/{communeName}/categories")
    public ResponseEntity<CommuneCategoriesDto> getCategoriesInCommune(@PathVariable String communeName) {
        CommuneCategoriesDto communeCategoriesDto = communeService.getCategoriesInCommune(communeName);
        return ResponseEntity.ok(communeCategoriesDto);
    }

    @GetMapping("/communes/{communeId}/departments")
    public ResponseEntity<List<Department>> getDepartmentsInCommune(@PathVariable int communeId) {
        Commune commune = communeRepository.findById(communeId).orElseThrow(CommuneNotFoundException::new);
        List<Department> departments = departmentRepository.findAllByInstitution_Commune(commune);
        return ResponseEntity.ok(departments);
    }

}
