package com.buildweekjava.watermyplant2.repository;

import com.buildweekjava.watermyplant2.models.Plant;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface PlantRepository extends CrudRepository<Plant, Long> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE plant SET nickname = :nickname," + "last_modified_by = :uname, " +
            "last_modified_date = current_timestamp WHERE plantid = :plantid", nativeQuery = true)

            void updatePlantName(
            String uname,
            long plantid,
            String nickname);

}
