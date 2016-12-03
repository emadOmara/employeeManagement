package com.mondimedia.memory.common.sort;

import java.util.Comparator;

import com.mondimedia.memory.common.Employee;

public class EmployeeComparatorAsc implements Comparator<Employee> {

	@Override
	public int compare(Employee first, Employee second) {
		return first.getEmployeeName().compareTo(second.getEmployeeName());
	}

}
