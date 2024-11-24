package tn.louay.recruitme.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tn.louay.recruitme.entities.Application;
import tn.louay.recruitme.entities.JobOffer;
import tn.louay.recruitme.repositories.JobOfferRepository;
import tn.louay.recruitme.services.ApplicationService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/applications")
@CrossOrigin
public class ApplicationsController {
    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private JobOfferRepository jobOfferRepository;

    @GetMapping("/{jobOfferId}")
    public ResponseEntity<List<Application>> getApplicationsByJobOfferId(@PathVariable int jobOfferId) {

        JobOffer jobOffer = jobOfferRepository.findById(jobOfferId).get();

        if (!jobOffer.getCreatedBy().equals(getUserId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        List<Application> applications = applicationService.getApplicationsByJobOfferId(jobOfferId);

        return ResponseEntity.ok(applications);
    }

    @GetMapping("/{jobOfferId}/search")
    public ResponseEntity<List<Application>> getApplicationsByJobOfferIdAndName(@PathVariable int jobOfferId,
            @RequestParam String name) {

        JobOffer jobOffer = jobOfferRepository.findById(jobOfferId).get();

        if (!jobOffer.getCreatedBy().equals(getUserId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        List<Application> applications = applicationService.getApplicationsByJobOfferIdAndName(jobOfferId, name);

        return ResponseEntity.ok(applications);
    }

    private String getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = ((JwtAuthenticationToken) authentication).getToken();
        return jwt.getSubject();
    }
}