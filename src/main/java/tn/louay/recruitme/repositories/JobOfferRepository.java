package tn.louay.recruitme.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import tn.louay.recruitme.entities.JobOffer;

@RepositoryRestResource(path = "offers")
@CrossOrigin
public interface JobOfferRepository extends JpaRepository<JobOffer, Integer> {

}