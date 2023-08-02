package com.toy4.domain.status.repository;

import com.toy4.domain.status.domain.Status;
import com.toy4.domain.status.type.StatusType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {

    Optional<Status> findByType(StatusType type);
}
