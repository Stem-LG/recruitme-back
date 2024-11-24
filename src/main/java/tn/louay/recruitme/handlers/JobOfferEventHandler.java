package tn.louay.recruitme.handlers;

import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.HandleBeforeDelete;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import tn.louay.recruitme.entities.JobOffer;

@Component
@RepositoryEventHandler(JobOffer.class)
public class JobOfferEventHandler {

    @HandleBeforeCreate
    public void handleBeforeCreate(JobOffer jobOffer) {

        jobOffer.setCreatedBy(getUserId());

    }

    @HandleBeforeSave
    @HandleBeforeDelete
    public void handleBeforeWriteOperation(JobOffer jobOffer) {

        if (!jobOffer.getCreatedBy().equals(getUserId())) {
            throw new RuntimeException("Not authorized to modify this job offer");
        }

    }

    private String getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = ((JwtAuthenticationToken) authentication).getToken();
        return jwt.getSubject();
    }
}
