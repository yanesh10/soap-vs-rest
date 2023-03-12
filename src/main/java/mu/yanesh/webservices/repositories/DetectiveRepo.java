package mu.yanesh.webservices.repositories;

import mu.yanesh.webservices.models.BaseClass;
import mu.yanesh.webservices.models.Detective;
import org.hibernate.NotYetImplementedFor6Exception;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class DetectiveRepo implements CrudRepository<Detective, Integer> {

    List<Detective> detectiveList = Collections.synchronizedList(Stream.of(MockData.detective1, MockData.detective2, MockData.detective3, MockData.detective4)
            .collect(Collectors.toList()));

    private int generateId() {
        int id = detectiveList.stream()
                .map(BaseClass::getId)
                .max(Comparator.comparing(Integer::intValue))
                .orElse(1);
        return ++id;
    }

    @Override
    public <S extends Detective> S save(S entity) {
        if (Objects.isNull(entity.getId())) {
            entity.setId(generateId());
            entity.setVersion(1);
        }
        if (existsById(entity.getId())) {
            entity.setVersion(findById(entity.getId()).map(BaseClass::getVersion).orElse(0) + 1);
        }
        detectiveList.add(entity);
        return entity;
    }

    @Override
    public <S extends Detective> Iterable<S> saveAll(Iterable<S> entities) {
        entities.forEach(e -> detectiveList.add(e));
        return entities;
    }

    @Override
    public Optional<Detective> findById(Integer integer) {
        return detectiveList.stream().filter(d -> d.getId().equals(integer)).findFirst();
    }

    @Override
    public boolean existsById(Integer integer) {
        return detectiveList.stream().anyMatch(d -> d.getId().equals(integer));
    }

    @Override
    public Iterable<Detective> findAll() {
        return detectiveList;
    }

    @Override
    public Iterable<Detective> findAllById(Iterable<Integer> integers) {
        List<Detective> detectives = new ArrayList<>();
        integers.forEach(id -> detectives.addAll(detectiveList.stream().filter(d -> d.getId().equals(id)).toList()));
        return detectives;
    }

    @Override
    public long count() {
        return detectiveList.size();
    }

    @Override
    public void deleteById(Integer integer) {
        Optional<Detective> detective = findById(integer);
        detective.ifPresent(value -> detectiveList.remove(value));
    }

    @Override
    public void delete(Detective entity) {
        Objects.requireNonNull(entity.getId());
        deleteById(entity.getId());
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {
        throw new NotYetImplementedFor6Exception();
    }

    @Override
    public void deleteAll(Iterable<? extends Detective> entities) {
        throw new NotYetImplementedFor6Exception();
    }

    @Override
    public void deleteAll() {
        throw new NotYetImplementedFor6Exception();
    }
}
