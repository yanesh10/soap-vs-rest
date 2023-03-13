package mu.yanesh.webservices.soapVersusRest;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@CucumberContextConfiguration
@SpringBootTest
public abstract class SpringIntegrationTest<T> {

    protected void checkSize(int value, List<T> resultList) {
        assertEquals(resultList.size(), value);
    }

}
