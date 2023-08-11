package com.toy4.domain.loginHistory.repository;


import com.toy4.domain.employee.domain.Employee;
import com.toy4.domain.loginHistory.domain.LoginHistory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginHistoryRepository extends JpaRepository<LoginHistory, Integer> {

    List<LoginHistory> findLoginHistoriesByEmployeeOrderByCreatedAtDesc(Employee employee);

}
