package com.example.demo.repository;

import com.example.demo.domain.FlightInfoUpdate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Repository
public interface FlightInfoUpdateRepository extends CrudRepository<FlightInfoUpdate, Long> {
}
