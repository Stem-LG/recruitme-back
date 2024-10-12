package tn.louay.recruitme.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.louay.recruitme.entities.Application;
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

    // get all applications is never needed

    @GetMapping("/{jobOfferId}")
    public ResponseEntity<List<Application>> getApplicationsByJobOfferId(@PathVariable int jobOfferId) {
        return ResponseEntity.ok(applicationService.getApplicationsByJobOfferId(jobOfferId));
    }
}