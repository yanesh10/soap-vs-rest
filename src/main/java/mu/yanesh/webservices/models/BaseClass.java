package mu.yanesh.webservices.models;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@Data
@SuperBuilder
public class BaseClass {

    private Integer id;
    private Integer version;
}
