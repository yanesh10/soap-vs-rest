package mu.yanesh.webservices.models;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public final class Detective extends BaseClass {

    private String firstName;
    private String lastName;
    private Rank rank;

}
