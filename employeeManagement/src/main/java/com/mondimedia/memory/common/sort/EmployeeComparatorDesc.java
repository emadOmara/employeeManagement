package com.mondimedia.memory.common.sort;

import java.util.Comparator;

import com.mondimedia.memory.common.Employee;

public class EmployeeComparatorDesc implements Comparator<Employee> {

	@Override
	public int compare(Employee first, Employee second) {
		return second.getEmployeeName().compareTo(first.getEmployeeName());
	}

}
