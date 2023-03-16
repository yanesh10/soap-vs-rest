package mu.yanesh.webservices.soapVersusRest;

import io.cucumber.spring.CucumberContextConfiguration;
import lombok.NoArgsConstructor;
import mu.yanesh.webservices.models.Detective;
import mu.yanesh.webservices.services.DetectiveService;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@CucumberContextConfiguration
@SpringBootTest
@NoArgsConstructor
public abstract class SpringIntegrationTest<T> {

    protected DetectiveService detectiveService;

    protected void checkSize(int value, List<T> resultList) {
        assertEquals(resultList.size(), value);
    }

    protected Optional<Detective> getDetective(String firstName, String lastName) {
        List<Detective> list = detectiveService.getAll();
        return list.stream().filter(d -> d.getFirstName().equals(firstName) && d.getLastName().equals(lastName))
                .findFirst();
    }

}
