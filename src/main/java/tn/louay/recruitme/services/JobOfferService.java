package tn.louay.recruitme.services;

import java.util.List;

import tn.louay.recruitme.entities.JobOffer;

public interface JobOfferService {

    public List<JobOffer> getAllJobOffers();

    public List<JobOffer> getJobOfferByTitle(String title);

    public List<JobOffer> getJobOfferBySkill(String skill);

    public List<JobOffer> getJobOfferByCompany(String company);
}
