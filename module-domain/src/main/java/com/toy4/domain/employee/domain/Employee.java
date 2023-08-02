package com.toy4.domain.employee.domain;

import com.toy4.domain.BaseEntity;
import com.toy4.domain.department.domain.Department;
import com.toy4.domain.employee.type.EmployeeRole;
import com.toy4.domain.position.domain.Position;
import com.toy4.domain.status.domain.Status;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Employee extends BaseEntity {

  @Column(length = 255)
  private String profileImagePath;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "position_id", referencedColumnName = "id", nullable = false)
  private Position position;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "department_id", referencedColumnName = "id", nullable = false)
  private Department department;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "status_id", referencedColumnName = "id", nullable = false)
  private Status status;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "email", unique = true, nullable = false)
  private String email;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "hire_date", nullable = false)
  private LocalDate hireDate;

  @Column(name = "quit_date")
  private LocalDate quitDate;

  @Column(name = "day_off_remains", nullable = false)
  private int dayOffRemains;

  @Column(name = "role", nullable = false)
  private EmployeeRole role;

  @Column(name = "phone", nullable = false)
  private String phone;

  @Column(name = "birthdate", nullable = false)
  private LocalDate birthdate;

  @Column(name = "zip_address", columnDefinition = "CHAR(5)")
  private String zipAddress;

  @Column(name = "road_address", length = 255)
  private String roadAddress;

  @Column(name = "detail_address", length = 255)
  private String detailAddress;

}
