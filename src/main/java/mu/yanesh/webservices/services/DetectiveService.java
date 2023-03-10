package mu.yanesh.webservices.services;

import lombok.AllArgsConstructor;
import mu.yanesh.webservices.models.Detective;
import mu.yanesh.webservices.repositories.DetectiveRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DetectiveService implements BaseService<Detective> {

    private final DetectiveRepository detectiveRepo;

    @Override
    public List<Detective> getAll() {
        return detectiveRepo.findAll();
    }

    @Override
    public Optional<Detective> getById(Integer id) {
        return detectiveRepo.findById(id);
    }

    public Detective save(Detective detective) {
        detective = detectiveRepo.save(detective);
        return detective;
    }

    public void delete(Integer id) {
        detectiveRepo.deleteById(id);
    }


}
