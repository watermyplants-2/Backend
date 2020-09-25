package com.buildweekjava.watermyplant2.repository;

import com.buildweekjava.watermyplant2.models.User;
import com.buildweekjava.watermyplant2.views.UserNameCountPlants;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository
        extends CrudRepository<User, Long>
{
    @Query(value = "SELECT u.username as usernamerpt, count(h.plantid) as countplants" +
    "FROM users u LEFT JOIN plant p" +
    "ON u.userid = h.userid" +
    "WHERE NOT h.completed GROUP BY u.username " +
    "ORDER BY u.username", nativeQuery = true)
            List<UserNameCountPlants> getCountUserPlants();

    User findByUsername(String username);

    List<User> findByUsernameContainingIgnoreCase(String name);
}
