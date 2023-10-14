package com.reserveme.backend.repository.establishment;

import com.reserveme.backend.model.entity.establishment.Establishment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstablishmentRepository extends JpaRepository<Establishment, Long> {

}
