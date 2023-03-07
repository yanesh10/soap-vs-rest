package mu.yanesh.webservices.services;

import java.util.List;
import java.util.Optional;

public interface BaseService<T> {

    List<T> getAll();
    Optional<T> getById(Integer id);

}
