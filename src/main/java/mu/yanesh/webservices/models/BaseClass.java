package mu.yanesh.webservices.models;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@Data
@SuperBuilder
public abstract sealed class BaseClass permits Case, Detective {

    private Integer id;
    private Integer version;
}
