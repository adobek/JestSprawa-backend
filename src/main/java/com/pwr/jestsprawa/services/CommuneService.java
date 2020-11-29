package com.pwr.jestsprawa.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.pwr.jestsprawa.exceptions.CommuneNotFoundException;
import com.pwr.jestsprawa.exceptions.DepartmentNotFoundException;
import com.pwr.jestsprawa.model.*;
import com.pwr.jestsprawa.model.nominatim.NominatimAddress;
import com.pwr.jestsprawa.repositories.CategoryRepository;
import com.pwr.jestsprawa.repositories.CommuneRepository;
import com.pwr.jestsprawa.repositories.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommuneService {

    private final CommuneRepository communeRepository;

    private final DepartmentRepository departmentRepository;

    private final CategoryRepository categoryRepository;

    private NominatimAddress getAddress(double latitude, double longitude) {
        String baseUrl = "https://nominatim.openstreetmap.org";
        String requestUrl = baseUrl + "/reverse?format=json&lat=" + latitude + "&lon=" + longitude;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(requestUrl, String.class);
        JsonObject jsonObjectResponse = JsonParser.parseString(response.getBody()).getAsJsonObject();
        Gson gson = new GsonBuilder().create();
        NominatimAddress address = gson.fromJson(jsonObjectResponse.getAsJsonObject("address"), NominatimAddress.class);
        String commune = address.getMunicipality() != null && !address.getMunicipality().isEmpty()
                ? StringUtils.removeStartIgnoreCase(address.getMunicipality(), "gmina ")
                : (address.getCity() != null ? address.getCity() : address.getTown());
        address.setCommune(commune);
        return address;
    }

    public LocationDataDto getLocationData(double latitude, double longitude) {
        NominatimAddress address = getAddress(latitude, longitude);
        Optional<Commune> commune = communeRepository.findOneByNameIgnoreCase(address.getCommune());
        LocationDataDto locationDataDto = new LocationDataDto();
        locationDataDto.setAddress(address);
        if (!address.getCountry_code().equalsIgnoreCase("pl") || commune.isEmpty()) {
            locationDataDto.setIsAvailable(false);
            return locationDataDto;
        }

        List<Department> departmentsInCommune = departmentRepository.findAllByInstitution_Commune(commune.get());
        if (departmentsInCommune.isEmpty()) {
            locationDataDto.setIsAvailable(false);
            return locationDataDto;
        }

        locationDataDto.setIsAvailable(true);
        return locationDataDto;
    }

    public CommuneCategoriesDto getCategoriesInCommune(String communeName) {
        Commune commune = communeRepository.findOneByNameIgnoreCase(communeName)
                .orElseThrow(CommuneNotFoundException::new);
        List<Department> departmentsInCommune = departmentRepository.findAllByInstitution_Commune(commune);
        if (departmentsInCommune.isEmpty())
            throw new DepartmentNotFoundException();
        List<Category> availableCategories = departmentsInCommune.stream()
                .flatMap(dep -> dep.getCategories().stream()).collect(Collectors.toList());
        List<Category> allCategories = categoryRepository.findAll();

        CommuneCategoriesDto communeCategoriesDto = new CommuneCategoriesDto();
        communeCategoriesDto.setAvailableCategories(availableCategories);
        communeCategoriesDto.setAllCategories(allCategories);
        return communeCategoriesDto;
    }

}
