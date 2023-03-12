package mu.yanesh.webservices;

import mu.yanesh.webservices.repositories.CaseRepository;
import mu.yanesh.webservices.repositories.DetectiveRepository;
import mu.yanesh.webservices.repositories.MockData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = {DetectiveRepository.class, CaseRepository.class})
public class SoapVersusRestApplication {
    public static void main(String[] args) {
        SpringApplication.run(SoapVersusRestApplication.class, args);
    }

    @Bean
    @Profile("create-data")
    public CommandLineRunner run(DetectiveRepository detectiveRepository, CaseRepository caseRepository) throws Exception {
        return (String[] args) -> {
            detectiveRepository.saveAll(List.of(MockData.detective1,
                    MockData.detective2, MockData.detective3, MockData.detective4));
            caseRepository.saveAll(List.of(MockData.case1,
                    MockData.case2, MockData.case3));
        };
    }

}
