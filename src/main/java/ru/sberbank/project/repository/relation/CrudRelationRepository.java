package ru.sberbank.project.repository.relation;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.sberbank.project.model.Relation;

@Transactional
public interface CrudRelationRepository extends CrudRepository<Relation, Integer> {

    @Override
    Relation save(Relation entity);

    @Modifying
    @Query("DELETE FROM Relation r WHERE r.followerId=:authUserId AND r.followingId=:verifiableUserId")
    void delete(@Param("authUserId") int authUserId, @Param("verifiableUserId") int verifiableUserId);

    @Query("SELECT r FROM Relation r WHERE (r.followerId=:authUserId) AND (r.followingId=:verifiableUserId)")
    Relation find(@Param("authUserId") int authUserId, @Param("verifiableUserId") int verifiableUserId);
}
