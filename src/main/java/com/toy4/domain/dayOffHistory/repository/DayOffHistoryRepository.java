package com.toy4.domain.dayOffHistory.repository;

import com.toy4.domain.dayOffHistory.domain.DayOffHistory;
import com.toy4.domain.schedule.RequestStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DayOffHistoryRepository extends JpaRepository<DayOffHistory, Long> {

    List<DayOffHistory> findByEmployeeId(Long employeeId);

    @Query("SELECT s FROM DayOffHistory s " +
        "WHERE s.employee.id = :employeeId " +
        "AND s.startDate <= :endDate " +
        "AND s.endDate >= :startDate")
    List<DayOffHistory> findByEmployeeIdAndDateRange(Long employeeId, LocalDate startDate, LocalDate endDate);

    @Query("SELECT s FROM DayOffHistory s " +
        "WHERE s.employee.id = :employeeId " +
        "AND s.status = :status")
    List<DayOffHistory> findByEmployeeIdAndStatus(Long employeeId, RequestStatus status);

    List<DayOffHistory> findAll();
    Optional<DayOffHistory> findById(Long id);

    @Query("SELECT s FROM DayOffHistory  s " +
        "WHERE s.employee.id = :employeeId " +
        "AND :date BETWEEN s.startDate AND s.endDate " +
        "AND s.status IN ( :#{T(com.toy4.domain.schedule.RequestStatus).REQUESTED}, :#{T(com.toy4.domain.schedule.RequestStatus).ACCEPTED} )" +
        "AND s.dayOff.type IN ( :#{T(com.toy4.domain.dayoff.type.DayOffType).NORMAL_DAY_OFF}, :#{T(com.toy4.domain.dayoff.type.DayOffType).SPECIAL_DAY_OFF})")
    Optional<DayOffHistory> findOverlappedDate(Long employeeId, LocalDate date);
}
