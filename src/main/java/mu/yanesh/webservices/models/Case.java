package mu.yanesh.webservices.models;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@Data
@SuperBuilder
public class Case extends BaseClass {

    private String description;
    private LocalDate date;
    private List<Detective> detectiveList;
    private Detective leadDetective;
    private Status status;
}
