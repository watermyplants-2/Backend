package com.buildweekjava.watermyplant2.repository;

import com.buildweekjava.watermyplant2.models.User;
import com.buildweekjava.watermyplant2.views.UserNameCountPlants;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * The CRUD repository connecting User to the rest of the application
 */
public interface UserRepository
        extends CrudRepository<User, Long>
{
    @Query(value = "SELECT u.username as usernamerpt, count(h.plantid) as countplants" +
    "FROM users u LEFT JOIN plant p" +
    "ON u.userid = h.userid" +
    "WHERE NOT h.completed GROUP BY u.username " +
    "ORDER BY u.username", nativeQuery = true)
            List<UserNameCountPlants> getCountUserPlants();
    /**
     * Find a user based off over username
     *
     * @param username the name (String) of user you seek
     * @return the first user object with the name you seek
     */
    User findByUsername(String username);

    /**
     * Find all users whose name contains a given substring ignoring case
     *
     * @param name the substring of the names (String) you seek
     * @return List of users whose name contain the given substring ignoring case
     */
    List<User> findByUsernameContainingIgnoreCase(String name);
}
