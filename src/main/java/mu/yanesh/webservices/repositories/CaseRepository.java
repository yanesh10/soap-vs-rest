package mu.yanesh.webservices.repositories;

import mu.yanesh.webservices.models.Case;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaseRepository extends JpaRepository<Case, Integer> {
}
