package com.example.demo.repository;

import com.example.demo.domain.OperatingCarrier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface OperatorCarrierRepository extends CrudRepository<OperatingCarrier, Long> {
}
