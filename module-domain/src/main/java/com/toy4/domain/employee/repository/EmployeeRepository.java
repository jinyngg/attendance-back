package com.toy4.domain.employee.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.toy4.domain.employee.domain.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

  // 이메일 중복 여부 확인
  boolean existsByEmail(String email);
  Optional<Employee> findById(Long id);
}
