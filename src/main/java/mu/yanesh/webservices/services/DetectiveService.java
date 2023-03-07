package mu.yanesh.webservices.services;

import lombok.AllArgsConstructor;
import mu.yanesh.webservices.models.Detective;
import mu.yanesh.webservices.repositories.DetectiveRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class DetectiveService implements BaseService<Detective>{

    private final DetectiveRepo detectiveRepo;

    @Override
    public List<Detective> getAll() {
        return StreamSupport.stream(detectiveRepo.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Detective> getById(Integer id) {
        return detectiveRepo.findById(id);
    }


}
