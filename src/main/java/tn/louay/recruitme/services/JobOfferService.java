package tn.louay.recruitme.services;

import java.util.List;

import tn.louay.recruitme.entities.JobOffer;

public interface JobOfferService {

    public List<JobOffer> getJobOfferBySkill(String skill);

}
