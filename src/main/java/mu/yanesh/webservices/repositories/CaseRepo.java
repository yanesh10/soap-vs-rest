package mu.yanesh.webservices.repositories;

import mu.yanesh.webservices.models.Case;
import org.hibernate.NotYetImplementedFor6Exception;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class CaseRepo implements CrudRepository<Case, Integer> {

    List<Case> caseList = List.of(MockData.case1, MockData.case2, MockData.case3);

    @Override
    public <S extends Case> S save(S entity) {
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
        Optional<Case> case_ = findById(integer);
        case_.ifPresent(aCase -> caseList.remove(aCase));
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
