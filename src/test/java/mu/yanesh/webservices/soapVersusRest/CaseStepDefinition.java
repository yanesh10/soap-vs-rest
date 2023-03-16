package mu.yanesh.webservices.soapVersusRest;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import mu.yanesh.webservices.models.Case;
import mu.yanesh.webservices.models.Detective;
import mu.yanesh.webservices.models.Rank;
import mu.yanesh.webservices.models.Status;
import mu.yanesh.webservices.repositories.CaseRepository;
import mu.yanesh.webservices.repositories.MockData;
import mu.yanesh.webservices.services.CaseService;
import org.springframework.format.datetime.DateFormatter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CaseStepDefinition extends SpringIntegrationTest<Case> {

    public static final String SEPARATOR = " ";
    private final CaseRepository caseRepository;
    private CaseService caseService;

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    List<Case> caseList;
    Case result;

    public CaseStepDefinition(CaseRepository caseRepository) {
        this.caseRepository = caseRepository;
    }

    @Given("Call case service")
    public void callCaseService() {
        caseService = new CaseService(caseRepository);
    }

    @When("Get all cases")
    public void getAllCases() {
        caseList = caseService.getAll();
    }

    @Then("There should be a total of {int} cases")
    public void thereShouldBeATotalOfCases(int value) {
        assertEquals(caseList.size(), value);
    }

    @When("Case id is {int}")
    public void caseIdIs(int value) throws Exception {
        result = caseService.getById(value).orElseThrow(Exception::new);
    }

    @Then("I should get case id {int}")
    public void iShouldGetCaseId(int value) {
        assertEquals(value, result.getId());
    }

    @And("Having {string} {string}")
    public void havingDescriptionDescription(String field, String expectedValue) {
        switch (field) {
            case "description" -> assertEquals(expectedValue, result.getDescription());
            case "status" -> assertEquals(Status.valueOf(expectedValue.toUpperCase()), result.getStatus());
            case "lead detective" -> {
                assertNotNull(result.getLeadDetective());
                String actualValue = result.getLeadDetective().getFirstName() +
                        SEPARATOR +
                        result.getLeadDetective().getLastName();
                assertEquals(expectedValue, actualValue);
            }
            case "date" -> {
                assertEquals(result.getDate(), LocalDate.parse(expectedValue, dateTimeFormatter));
            }
        }
    }

    @Then("I should get {word} {word}")
    public void iShouldGet(String firstName, String lastName) {
        assertNotNull(result.getLeadDetective());
        assertEquals(result.getLeadDetective().getFirstName(), firstName);
        assertEquals(result.getLeadDetective().getLastName(), lastName);
    }

    @And("Case status is {word}")
    public void caseStatusIs(String status) {
        caseList = caseList.stream()
                .filter(c -> c.getStatus().equals(Status.valueOf(status.toUpperCase())))
                .toList();
    }

    @And("Verify the following values")
    public void verifyTheFollowingValues(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        List<Case> expectedResult = new ArrayList<>();

        for (Map<String, String> columns : rows) {
            String[] name = columns.get("leadDetective").split(SEPARATOR);
            Detective leadDetective = Detective.builder()
                    .id(3)
                    .version(0)
                    .firstName(name[0])
                    .lastName(name[1])
                    .rank(Rank.LIEUTENANT)
                    .build();

            Case actualValue = Case.builder()
                    .id(Integer.valueOf(columns.get("caseId")))
                    .date(LocalDate.parse(columns.get("date"), dateTimeFormatter))
                    .description(columns.get("description"))
                    .leadDetective(leadDetective)
                    .status(Status.valueOf(columns.get("status").toUpperCase()))
                    .detectiveList(getDetectiveList(columns.get("detectiveList")))
                    .version(0)
                    .build();
            expectedResult.add(actualValue);
        }

        assertEquals(expectedResult.size(), caseList.size());
        expectedResult.forEach(c -> assertThat(caseList, hasItem(c)));
    }

    private static List<Detective> getDetectiveList(String field) {
        List<Detective> detectiveList = new ArrayList<>();
        List<Integer> detectiveId = Stream.of(field
                        .split(","))
                        .map(String::trim)
                        .map(Integer::valueOf)
                        .toList();
        detectiveId.forEach(id -> {
            switch (id) {
                case 1 -> detectiveList.add(MockData.detective1);
                case 2 -> detectiveList.add(MockData.detective2);
                case 3 -> detectiveList.add(MockData.detective3);
                case 4 -> detectiveList.add(MockData.detective4);
            }
        });
        return detectiveList;
    }
}
