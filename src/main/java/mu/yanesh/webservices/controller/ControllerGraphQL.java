package mu.yanesh.webservices.controller;

import lombok.AllArgsConstructor;
import mu.yanesh.webservices.exceptions.DetectiveNotFoundException;
import mu.yanesh.webservices.models.Detective;
import mu.yanesh.webservices.models.Rank;
import mu.yanesh.webservices.services.DetectiveService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Controller
public class ControllerGraphQL {
    private final DetectiveService detectiveService;

    @QueryMapping
    public List<Detective> getDetectives(@Argument int count) {
        return detectiveService.getAll()
                .stream()
                .limit(count)
                .toList();
    }

    @MutationMapping
    public Detective saveDetective(@Argument String firstName, @Argument String lastName, @Argument String rank) {
        Detective detective = Detective.builder()
                .firstName(firstName)
                .lastName(lastName)
                .rank(Rank.valueOf(rank.toUpperCase()))
                .build();
        return detectiveService.save(detective);
    }

    @MutationMapping()
    public Boolean deleteDetective(@Argument int id) {
        detectiveService.delete(id);
        return true;
    }

    @MutationMapping()
    public Detective updateDetective(@Argument int id, @Argument String firstName, @Argument String lastName, @Argument String rank) {
        Detective detective = detectiveService.getById(id).orElseThrow(DetectiveNotFoundException::new);
        detective.setRank(Objects.nonNull(rank) ? Rank.valueOf(rank.toUpperCase()) : detective.getRank());
        detective.setFirstName(Objects.nonNull(firstName) ? firstName : detective.getFirstName());
        detective.setLastName(Objects.nonNull(lastName) ? lastName : detective.getLastName());
        return detectiveService.save(detective);
    }

    @QueryMapping()
    public Detective getDetectiveById(@Argument int id) {
        return detectiveService.getById(id).orElseThrow(DetectiveNotFoundException::new);
    }
}
