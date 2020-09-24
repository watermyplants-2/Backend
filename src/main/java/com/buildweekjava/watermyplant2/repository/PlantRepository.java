package com.buildweekjava.watermyplant2.repository;

import com.buildweekjava.watermyplant2.models.Plant;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface PlantRepository extends CrudRepository<Plant, Long> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE plant SET name = :nickname," + "last_modified_by = :uname, " +
            "last_modified_date = current_timestamp WHERE plantid = :plantid", nativeQuery = true)

    void updateRoleName(
            String uname,
            long plantid,
            String name);
}
