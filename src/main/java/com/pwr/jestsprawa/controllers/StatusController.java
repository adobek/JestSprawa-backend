package com.pwr.jestsprawa.controllers;

import com.pwr.jestsprawa.model.Status;
import com.pwr.jestsprawa.repositories.StatusRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class StatusController {

    private final StatusRepository statusRepository;

    public StatusController(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @GetMapping("/statuses")
    public Iterable<Status> getStatuses(){
        return statusRepository.findAll();
    }
}
