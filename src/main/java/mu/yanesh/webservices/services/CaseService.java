package mu.yanesh.webservices.services;

import lombok.AllArgsConstructor;
import mu.yanesh.webservices.models.Case;
import mu.yanesh.webservices.models.Detective;
import mu.yanesh.webservices.models.Status;
import mu.yanesh.webservices.repositories.CaseRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CaseService implements BaseService<Case> {

    private final CaseRepository caseRepo;

    @Override
    public List<Case> getAll() {
        return caseRepo.findAll();
    }

    @Override
    public Optional<Case> getById(Integer id) {
        return caseRepo.findById(id);
    }

    @Override
    public Case save(Case entity) {
        return caseRepo.save(entity);
    }

    @Override
    public void delete(Integer id) {
        caseRepo.deleteById(id);
    }

    public List<Detective> getAllDetectiveByCase(int caseId) {
        Optional<Case> caseOptional = caseRepo.findById(caseId);
        if (caseOptional.isPresent()) {
            return caseOptional.get().getDetectiveList();
        }
        return Collections.emptyList();
    }

    public Optional<Detective> getLeadDetective(int caseId) {
        Optional<Case> caseOptional = getById(caseId);
        return caseOptional.map(Case::getLeadDetective);
    }

    public List<Case> getAllCasesByStatus(Status status) {
        return getAll().stream()
                .filter(c -> c.getStatus().equals(status))
                .toList();
    }
}
