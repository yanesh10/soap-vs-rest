package mu.yanesh.webservices.soapVersusRest;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import mu.yanesh.webservices.exceptions.DetectiveNotFoundException;
import mu.yanesh.webservices.models.Detective;
import mu.yanesh.webservices.models.Rank;
import mu.yanesh.webservices.repositories.DetectiveRepository;
import mu.yanesh.webservices.services.DetectiveService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class DetectiveStepDefinition extends SpringIntegrationTest<Detective> {

    private final DetectiveRepository detectiveRepository;
    private DetectiveService detectiveService;
    private Detective result;

    private List<Detective> resultList;

    public DetectiveStepDefinition(DetectiveRepository detectiveRepository) {
        this.detectiveRepository = detectiveRepository;
    }

    @Given("Call detective service")
    public void callDetectiveService() {
        detectiveService = new DetectiveService(detectiveRepository);
    }

    @Given("Pass {word} {word}")
    public void passFirstNameAndLastName(String firstName, String lastName) {
        Optional<Detective> detective = getDetective(firstName, lastName);
        result = detective.orElseThrow(DetectiveNotFoundException::new);
    }

    @Then("I should get an detective id {int}")
    public void iShouldGet(int value) {
        assertEquals(value, result.getId());
    }

    @Then("Having detective {string} {word}")
    public void havingDetective(String field, String value) {
        switch (field) {
            case "first name" -> assertEquals(value, result.getFirstName());
            case "last name" -> assertEquals(value, result.getLastName());
            case "rank" -> assertEquals(Rank.valueOf(value.toUpperCase()), result.getRank());
        }
    }

    @When("Create the detective {word} {word} as an {word}")
    public void createTheDetective(String firstName, String lastName, String rank) {
        Detective detective = Detective.builder()
                .firstName(firstName)
                .lastName(lastName)
                .rank(Rank.valueOf(rank.toUpperCase()))
                .build();
        result = detectiveService.save(detective);
    }

    @And("Having detective version {int}")
    public void havingDetectiveVersion(int value) {
        assertEquals(value, result.getVersion());
    }

    @When("Update {string} for the detective with id {int} to {word}")
    public void updateDetective(String field, int id, String value) {
        Detective detective = detectiveService.getById(id).orElseThrow(DetectiveNotFoundException::new);
        switch (field) {
            case "first name" -> detective.setFirstName(value);
            case "last name" -> detective.setLastName(value);
            case "rank" -> detective.setRank(Rank.valueOf(value.toUpperCase()));
        }
        result = detectiveService.save(detective);
    }

    @When("Delete detective {word} {word}")
    public void deleteDetective(String firstName, String lastName) {
        Detective detective = getDetective(firstName, lastName)
                .orElseThrow(DetectiveNotFoundException::new);
        detectiveService.delete(detective.getId());
    }

    private Optional<Detective> getDetective(String firstName, String lastName) {
        List<Detective> list = detectiveService.getAll();
        return list.stream().filter(d -> d.getFirstName().equals(firstName) && d.getLastName().equals(lastName))
                .findFirst();
    }

    @Then("Check if detective {word} {word} still exist")
    public void searchingDetectiveShouldReturnAnError(String firstName, String lastName) {
        assertFalse(getDetective(firstName, lastName).isPresent());
    }

    @When("Get all detectives")
    public void getAllDetectives() {
        resultList = detectiveService.getAll();
    }

    @Then("There should be a total of {int} detectives")
    public void checkIfSizeIs(int value) {
        checkSize(value, resultList);
    }

    @Then("I should get an error")
    public void iShouldGetAnError() {
        Optional<Detective> detective = getDetective("Nom", "Nom");
        assertThrows(DetectiveNotFoundException.class,
                () -> detective.orElseThrow(DetectiveNotFoundException::new));
    }
}
