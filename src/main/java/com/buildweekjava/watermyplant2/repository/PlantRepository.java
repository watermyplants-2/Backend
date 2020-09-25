package com.buildweekjava.watermyplant2.repository;

import com.buildweekjava.watermyplant2.models.Plant;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface PlantRepository extends CrudRepository<Plant, Long> {

}
