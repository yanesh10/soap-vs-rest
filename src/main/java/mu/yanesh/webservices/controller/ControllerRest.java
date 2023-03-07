package mu.yanesh.webservices.controller;

import lombok.AllArgsConstructor;
import mu.yanesh.webservices.models.Case;
import mu.yanesh.webservices.models.Detective;
import mu.yanesh.webservices.models.Status;
import mu.yanesh.webservices.services.CaseService;
import mu.yanesh.webservices.services.DetectiveService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/rest")
public class ControllerRest {

    private final CaseService caseService;
    private final DetectiveService detectiveService;

    @GetMapping("/cases")
    public List<Case> getAllCases() {
        return caseService.getAll();
    }

    @GetMapping("/cases/{caseId}")
    public List<Detective> getAllDetectiveForCase(@PathVariable int caseId) {
        return caseService.getAllDetectiveByCase(caseId);
    }

    @GetMapping("/cases/{caseId}/lead")
    public Detective getLeadDetective(@PathVariable int caseId) {
        Optional<Detective> detective = caseService.getLeadDetective(caseId);
        return detective.orElse(Detective.builder().build());
    }

    @GetMapping("/cases/status/{status}")
    public List<Case> getCaseByStatus(@PathVariable Status status) {
        return caseService.getAllCasesByStatus(status);
    }

    @GetMapping("/detectives")
    public List<Detective> getAllDetective() {
        return detectiveService.getAll();
    }
}
