package mu.yanesh.webservices.models;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@XmlRootElement
@Entity
@Table(name = "cases")
@NoArgsConstructor
@AllArgsConstructor
public final class Case extends BaseClass {

    private String description;
    private LocalDate date;
    @OneToMany(mappedBy = "id")
    private List<Detective> detectiveList;
    @OneToOne
    @JoinColumn(name = "detectiveId")
    private Detective leadDetective;
    private Status status;
}
