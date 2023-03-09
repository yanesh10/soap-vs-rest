package mu.yanesh.webservices.repositories;

import mu.yanesh.webservices.models.BaseClass;
import mu.yanesh.webservices.models.Case;
import org.hibernate.NotYetImplementedFor6Exception;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class CaseRepo implements CrudRepository<Case, Integer> {

    List<Case> caseList = Stream.of(MockData.case1, MockData.case2, MockData.case3)
            .collect(Collectors.toList());

    private int generateId() {
        int id = caseList.stream()
                .map(BaseClass::getId)
                .max(Comparator.comparing(Integer::intValue))
                .orElse(1);
        return ++id;
    }

    @Override
    public <S extends Case> S save(S entity) {
        if (Objects.isNull(entity.getId())) {
            entity.setId(generateId());
        }
        if (existsById(entity.getId())) {
            entity.setVersion(findById(entity.getId()).map(BaseClass::getVersion).orElse(0) + 1);
        }
        caseList.add(entity);
        return entity;
    }

    @Override
    public <S extends Case> Iterable<S> saveAll(Iterable<S> entities) {
        entities.forEach(caseList::add);
        return entities;
    }

    @Override
    public Optional<Case> findById(Integer integer) {
        return caseList.stream().filter(c -> c.getId().equals(integer)).findFirst();
    }

    @Override
    public boolean existsById(Integer integer) {
        return caseList.stream().anyMatch(c -> c.getId().equals(integer));
    }

    @Override
    public Iterable<Case> findAll() {
        return caseList;
    }

    @Override
    public Iterable<Case> findAllById(Iterable<Integer> integers) {
        List<Case> cases = new ArrayList<>();
        integers.forEach(id -> cases.addAll(
                caseList.stream().filter(d -> d.getId().equals(id)).toList()));
        return cases;
    }

    @Override
    public long count() {
        return caseList.size();
    }

    @Override
    public void deleteById(Integer integer) {
        Optional<Case> foundCase = findById(integer);
        foundCase.ifPresent(aCase -> caseList.remove(aCase));
    }

    @Override
    public void delete(Case entity) {
        Objects.requireNonNull(entity.getId());
        deleteById(entity.getId());
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {
        throw new NotYetImplementedFor6Exception();
    }

    @Override
    public void deleteAll(Iterable<? extends Case> entities) {
        throw new NotYetImplementedFor6Exception();
    }

    @Override
    public void deleteAll() {
        throw new NotYetImplementedFor6Exception();
    }
}
