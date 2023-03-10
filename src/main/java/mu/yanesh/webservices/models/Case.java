package mu.yanesh.webservices.models;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@XmlRootElement
public final class Case extends BaseClass {

    private String description;
    private LocalDate date;
    private List<Detective> detectiveList;
    private Detective leadDetective;
    private Status status;
}
