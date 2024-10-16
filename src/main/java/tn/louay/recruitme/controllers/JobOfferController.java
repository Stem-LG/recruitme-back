package tn.louay.recruitme.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.louay.recruitme.entities.JobOffer;
import tn.louay.recruitme.services.JobOfferService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/offers")
@CrossOrigin
public class JobOfferController {

    @Autowired
    private JobOfferService jobOfferService;

    @GetMapping("search/")
    public List<JobOffer> getJobOffersBySkill(@RequestParam String skill) {
        return jobOfferService.getJobOfferBySkill(skill);
    }

}
