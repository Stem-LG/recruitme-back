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
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/application")
@CrossOrigin
public class ApplicationController {
    @Autowired
    private ApplicationService applicationService;

    @PostMapping
    public ResponseEntity<Application> createApplication(@RequestBody Application application) {

        application.setCreatedBy(getUserId());
        application.setStatus(ApplicationStatus.pending);

        return ResponseEntity.ok(applicationService.createApplication(application));
    }

    @PutMapping("/{applicationId}/status")
    public ResponseEntity<Application> updateApplicationStatus(
            @PathVariable int applicationId,
            @RequestParam ApplicationStatus status) {

        Application application = applicationService.getApplication(applicationId);
        if (!application.getJobOffer().getCreatedBy().equals(getUserId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        application.setStatus(status);
        return ResponseEntity.ok(applicationService.updateApplication(application));
    }

    @GetMapping("/{applicationId}")
    public ResponseEntity<Application> getApplication(@PathVariable int applicationId) {

        Application application = applicationService.getApplication(applicationId);
        if (!application.getJobOffer().getCreatedBy().equals(getUserId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok(application);
    }

    @DeleteMapping("/{applicationId}")
    public ResponseEntity<Void> deleteApplication(@PathVariable int applicationId) {

        Application application = applicationService.getApplication(applicationId);
        if (!application.getJobOffer().getCreatedBy().equals(getUserId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        applicationService.deleteApplication(applicationId);
        return ResponseEntity.ok().build();
    }

    private String getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = ((JwtAuthenticationToken) authentication).getToken();
        return jwt.getSubject();
    }
}