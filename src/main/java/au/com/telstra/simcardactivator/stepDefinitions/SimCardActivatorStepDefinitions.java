package au.com.telstra.simcardactivator.stepDefinitions;

import au.com.telstra.simcardactivator.model.SimCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SimCardActivatorStepDefinitions {

    @Autowired
    private RestTemplate restTemplate;

    private String iccid;
    private String customerEmail;
    private ResponseEntity<String> response;
    private SimCard simCard;

    @Given("a SIM card with ICCID {string} and customer email {string}")
    public void givenASimCard(String iccid, String customerEmail) {
        this.iccid = iccid;
        this.customerEmail = customerEmail;
    }

    @When("I request to activate the SIM card")
    public void whenIRequestToActivateTheSimCard() {
        String url = "http://localhost:8080/api/sim-cards/activate";
        SimCard simCard = new SimCard();
        simCard.setIccid(this.iccid);
        simCard.setCustomerEmail(this.customerEmail);
        response = restTemplate.postForEntity(url, simCard, String.class);
    }

    @Then("the activation should be successful")
    public void thenTheActivationShouldBeSuccessful() {
        assertEquals("Activation successful", response.getBody());
    }

    @Then("the activation should fail")
    public void thenTheActivationShouldFail() {
        assertEquals("Activation failed", response.getBody());
    }

    @Then("I should be able to query the SIM card status and see it as activated")
    public void thenIShouldBeAbleToQueryTheSimCardStatusAndSeeItAsActivated() {
        // Assuming the ID increments starting from 1
        Long simCardId = 1L; // Update this based on your scenario
        String url = "http://localhost:8080/api/sim-cards/status?simCardId=" + simCardId;
        simCard = restTemplate.getForObject(url, SimCard.class);

        assertNotNull(simCard);
        assertEquals(this.iccid, simCard.getIccid());
        assertEquals(this.customerEmail, simCard.getCustomerEmail());
        assertEquals(true, simCard.isActive());
    }

    @Then("I should be able to query the SIM card status and see it as not activated")
    public void thenIShouldBeAbleToQueryTheSimCardStatusAndSeeItAsNotActivated() {
        // Assuming the ID increments starting from 1
        Long simCardId = 2L; // Update this based on your scenario
        String url = "http://localhost:8080/api/sim-cards/status?simCardId=" + simCardId;
        simCard = restTemplate.getForObject(url, SimCard.class);

        assertNotNull(simCard);
        assertEquals(this.iccid, simCard.getIccid());
        assertEquals(this.customerEmail, simCard.getCustomerEmail());
        assertEquals(false, simCard.isActive());
    }
}
