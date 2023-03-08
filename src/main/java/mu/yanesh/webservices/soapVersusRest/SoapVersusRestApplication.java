package mu.yanesh.webservices.soapVersusRest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("mu.yanesh.webservices")
public class SoapVersusRestApplication {
	public static void main(String[] args) {
		SpringApplication.run(SoapVersusRestApplication.class, args);
	}

}
