package ru.sberbank.project.repository.relation;

import ru.sberbank.project.model.Relation;

public interface RelationRepository {

    Relation save(Relation relation);

    void delete(Relation relation);

    Relation find(int authUserId, int verifiableUserId);
}
