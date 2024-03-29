package mu.yanesh.webservices.repositories;

import lombok.experimental.UtilityClass;
import mu.yanesh.webservices.models.Case;
import mu.yanesh.webservices.models.Detective;
import mu.yanesh.webservices.models.Rank;
import mu.yanesh.webservices.models.Status;

import java.time.LocalDate;
import java.util.List;

@UtilityClass
public class MockData {

    public static final Detective detective1 = Detective.builder()
            .id(1)
            .version(0)
            .firstName("John")
            .lastName("David")
            .rank(Rank.SERGEANT)
            .build();

    public static final Detective detective2 = Detective.builder()
            .id(2)
            .version(0)
            .firstName("Lionel")
            .lastName("Messi")
            .rank(Rank.DETECTIVE)
            .build();

    public static final Detective detective3 = Detective.builder()
            .id(3)
            .version(0)
            .firstName("Crist")
            .lastName("Ron")
            .rank(Rank.LIEUTENANT)
            .build();

    public static final Detective detective4 = Detective.builder()
            .id(4)
            .version(0)
            .firstName("David")
            .lastName("Deck")
            .rank(Rank.OFFICER)
            .build();

    public static final Case case1 = Case.builder()
            .id(1)
            .version(0)
            .date(LocalDate.of(2010, 1, 4))
            .description("Description 1")
            .status(Status.OPEN)
            .leadDetective(detective1)
            .detectiveList(List.of(detective1, detective2))
            .build();

    public static final Case case2 = Case.builder()
            .id(2)
            .version(0)
            .date(LocalDate.of(1990, 1, 4))
            .description("Description 2")
            .status(Status.CLOSED)
            .leadDetective(detective2)
            .detectiveList(List.of(detective1, detective2))
            .build();

    public static final Case case3 = Case.builder()
            .id(3)
            .version(0)
            .date(LocalDate.of(2018, 1, 4))
            .description("Description 3")
            .status(Status.OPEN)
            .leadDetective(detective3)
            .detectiveList(List.of(detective3, detective4))
            .build();
}
