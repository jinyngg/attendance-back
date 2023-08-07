package com.toy4.domain.dayOffHistory.repository.info;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.toy4.domain.dayOffHistory.domain.QDayOffHistory;
import com.toy4.domain.employee.domain.QEmployee;
import com.toy4.domain.employee.dto.EmployeeDayOffInfoResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class DayOffHistoryCustomRepositoryImpl implements DayOffHistoryCustomRepository {
	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<EmployeeDayOffInfoResponse> getEmployeeDayOff() {
		QEmployee employee = QEmployee.employee;
		QDayOffHistory dayOffHistory = QDayOffHistory.dayOffHistory;

		Expression<String> hireDate = Expressions.stringTemplate("DATE_FORMAT({0}, '%Y-%m-%d')", employee.hireDate);

		return jpaQueryFactory
			.select(Projections.constructor(EmployeeDayOffInfoResponse.class,
				employee.id, employee.name, employee.department.type, employee.position.type,
				hireDate, employee.dayOffRemains,
				dayOffHistory.totalAmount.sum().coalesce(0f),
				dayOffHistory.totalAmount.sum().coalesce(0f).add(employee.dayOffRemains)))
			.from(employee)
			.leftJoin(dayOffHistory).on(employee.id.eq(dayOffHistory.employee.id))
			.groupBy(employee.id, employee.name, employee.email, employee.dayOffRemains)
			.orderBy(employee.id.desc())
			.fetch();
	}
}
