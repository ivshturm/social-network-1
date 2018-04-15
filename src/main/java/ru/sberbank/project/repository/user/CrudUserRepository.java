package ru.sberbank.project.repository.user;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.sberbank.project.model.User;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudUserRepository extends JpaRepository<User, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM User u WHERE u.id=:id")
    int delete(@Param("id") int id);

    @Override
    @Transactional
    @Modifying
    User save(User user);

    @Override
    Optional<User> findById(Integer id);

    @Override
    List<User> findAll(Sort sort);

    User getByEmail(String email);

    @Query(value = "SELECT * FROM users WHERE (users.name ILIKE %:param%) OR (users.last_name ILIKE %:param%)", nativeQuery = true)
    List<User> findAllByNameOrLastName(@Param("param") String param);

    @Query(value = "SELECT * FROM users u INNER JOIN " +
            "(SELECT * FROM relations WHERE following_id = :id) r ON u.id = r.follower_id", nativeQuery = true)
    List<User> getAllFollowersByUserId(@Param("id") Integer id);

    @Query(value = "SELECT * FROM users u INNER JOIN " +
            "(SELECT * FROM relations WHERE follower_id = :id) r ON u.id = r.following_id", nativeQuery = true)
    List<User> getAllFollowingByUserId(@Param("id") Integer id);
}
