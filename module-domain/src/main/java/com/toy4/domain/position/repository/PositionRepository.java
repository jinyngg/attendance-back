package com.toy4.domain.position.repository;

import com.toy4.domain.position.domain.Position;
import com.toy4.domain.position.type.PositionType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Long> {

    Optional<Position> findByType(PositionType type);
}
