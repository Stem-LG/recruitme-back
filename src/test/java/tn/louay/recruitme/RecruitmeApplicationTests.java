package tn.louay.recruitme;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tn.louay.recruitme.entities.Application;
import tn.louay.recruitme.entities.JobOffer;
import tn.louay.recruitme.services.ApplicationService;

@SpringBootTest
class RecruitmeApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private ApplicationService applicationService;

	@Test
	public void testCreateApplication() {

		Application application = new Application(
				"testName",
				"testEmail",
				"testResume",
				"someUrl",
				new JobOffer(1, "", "", "", "", 1, new Date()));

		System.out.println(applicationService.createApplication(application));
	}

	@Test
	public void testGetAllApplicationsWithJobOfferId() {
		System.out.println(applicationService.getApplicationsByJobOfferId(1));
	}

	@Test
	public void testGetApplication() {

		List<Application> applications = applicationService.getApplicationsByJobOfferId(1);

		System.out.println(applicationService.getApplication(applications.get(0).getId()));
	}

	@Test
	public void testUpdateApplication() {

		List<Application> applications = applicationService.getApplicationsByJobOfferId(1);

		Application originalApplication = applications.get(0);

		originalApplication.setName("updatedName");

		System.out.println(applicationService.updateApplication(originalApplication));
	}

	@Test
	public void testDeleteApplication() {

		List<Application> applications = applicationService.getApplicationsByJobOfferId(1);

		applicationService.deleteApplication(applications.get(0).getId());

	}

}
