package com.toy4.domain.department.repository;

import com.toy4.domain.department.domain.Department;
import com.toy4.domain.department.type.DepartmentType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Optional<Department> findByType(DepartmentType type);
}
