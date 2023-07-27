package com.toy4.domain.position.repository;

import com.toy4.domain.position.domain.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Long> {

}
