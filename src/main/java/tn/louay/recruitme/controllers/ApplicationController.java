package tn.louay.recruitme.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.louay.recruitme.entities.Application;
import tn.louay.recruitme.enums.ApplicationStatus;
import tn.louay.recruitme.services.ApplicationService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/application")
@CrossOrigin
public class ApplicationController {
    @Autowired
    private ApplicationService applicationService;

    // get all applications is never needed

    @GetMapping("/{applicationId}")
    public Application getApplication(@PathVariable int applicationId) {
        return applicationService.getApplication(applicationId);
    }

    @PostMapping
    public Application createApplication(@RequestBody Application application) {

        application.setStatus(ApplicationStatus.pending);

        return applicationService.createApplication(application);
    }

    @PutMapping
    public Application updateApplication(@RequestBody Application application) {
        return applicationService.updateApplication(application);
    }

    @DeleteMapping("/{applicationId}")
    public void deleteApplication(@PathVariable int applicationId) {
        applicationService.deleteApplication(applicationId);
    }

}