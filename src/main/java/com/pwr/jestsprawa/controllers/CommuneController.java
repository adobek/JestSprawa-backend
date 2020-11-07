package com.pwr.jestsprawa.controllers;


import com.pwr.jestsprawa.model.LocationDataDto;
import com.pwr.jestsprawa.services.CommuneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
