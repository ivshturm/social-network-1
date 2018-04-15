package ru.sberbank.project.repository.relation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.sberbank.project.model.Relation;

@Repository
public class DataJpaRelationRepository implements RelationRepository {

    @Autowired
    private CrudRelationRepository crudRelationRepository;

    @Override
    public Relation save(Relation relation) {
        return crudRelationRepository.save(relation);
    }

    @Override
    public void delete(Relation relation) {
        crudRelationRepository.delete(relation.getFollowerId(), relation.getFollowingId());
    }

    @Override
    public Relation find(int authUserId, int verifiableUserId) {
        return crudRelationRepository.find(authUserId, verifiableUserId);
    }
}
