package com.pwr.jestsprawa.controllers;


import com.pwr.jestsprawa.model.CommuneCategoriesDto;
import com.pwr.jestsprawa.model.LocationDataDto;
import com.pwr.jestsprawa.services.CommuneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommuneController {

    private final CommuneService communeService;

    @GetMapping("/commune")
    public ResponseEntity<LocationDataDto> getLocationData(@RequestParam("lat") double latitude, @RequestParam("lon") double longitude) {
        LocationDataDto locationDataDto = communeService.getLocationData(latitude, longitude);
        return ResponseEntity.ok(locationDataDto);
    }

    @GetMapping("/commune/{communeName}/categories")
    public ResponseEntity<CommuneCategoriesDto> getCategoriesInCommune(@PathVariable String communeName) {
        CommuneCategoriesDto communeCategoriesDto = communeService.getCategoriesInCommune(communeName);
        return ResponseEntity.ok(communeCategoriesDto);
    }

}
