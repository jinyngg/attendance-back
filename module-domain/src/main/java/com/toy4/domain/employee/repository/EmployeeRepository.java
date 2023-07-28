package com.toy4.domain.employee.repository;

import com.toy4.domain.employee.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

  // 이메일 중복 여부 확인
  boolean existsByEmail(String email);

}
