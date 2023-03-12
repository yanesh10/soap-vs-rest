package mu.yanesh.webservices.repositories;

import mu.yanesh.webservices.models.Detective;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetectiveRepository extends JpaRepository<Detective, Integer> {
}
