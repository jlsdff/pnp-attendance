package com.example.pnpattendance.repositories;


import com.example.pnpattendance.models.Officer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OfficerRepository extends CrudRepository<Officer, Long> {

    Optional<Officer> findByBadgeNumber(long id);

}
