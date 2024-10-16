package tn.louay.recruitme.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.louay.recruitme.entities.JobOffer;
import tn.louay.recruitme.repositories.JobOfferRepository;

@Service
public class JobOfferServiceImpl implements JobOfferService {

    @Autowired
    private JobOfferRepository jobOfferRepository;

    public List<JobOffer> getJobOfferBySkill(String skill) {
        return jobOfferRepository.findBySkill(skill);
    }

}
