package tn.louay.recruitme.services;

import tn.louay.recruitme.entities.Application;
import java.util.*;

public interface ApplicationService {

    public List<Application> getApplicationsByJobOfferId(int jobOfferId);

    public List<Application> getApplicationsByJobOfferIdAndName(int jobOfferId, String name);

    public Application getApplication(int id);

    public Application createApplication(Application application);

    public Application updateApplication(Application application);

    public void deleteApplication(int id);

}
