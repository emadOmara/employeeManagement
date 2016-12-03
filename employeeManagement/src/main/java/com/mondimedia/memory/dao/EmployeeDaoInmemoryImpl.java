package com.mondimedia.memory.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.mondimedia.memory.common.BusinessException;
import com.mondimedia.memory.common.Constants;
import com.mondimedia.memory.common.Employee;
import com.mondimedia.memory.common.sort.EmployeeComparatorAsc;
import com.mondimedia.memory.common.sort.EmployeeComparatorDesc;

class EmployeeDaoInmemoryImpl implements EmployeeDao {

	private static EmployeeDaoInmemoryImpl instance;
	private Map<Integer, Employee> employeeMap = new ConcurrentHashMap<Integer,Employee>();
	private EmployeeComparatorAsc asc = new EmployeeComparatorAsc();
	private EmployeeComparatorDesc desc = new EmployeeComparatorDesc();

	private EmployeeDaoInmemoryImpl() { 
	}

	public static EmployeeDao instance() {
		if (instance == null) {

			synchronized (new Object()) {
				if (instance == null) {
					instance = new EmployeeDaoInmemoryImpl();
				}
			}
		}
		return instance;
	}

	@Override
	public List<Employee> getAllEmployees(int order) throws BusinessException {
		List<Employee> allEmployees = new ArrayList<>(employeeMap.values());
		switch (order) {
		case Constants.ORDER_ASC_TYPE:
			Collections.sort(allEmployees, asc);
			break;
		case Constants.ORDER_DESC_TYPE:
			Collections.sort(allEmployees, desc);
			break;
		}
		return allEmployees;
	}

	@Override
	public Employee getEmployee(int id) throws BusinessException {
		return employeeMap.get(id);
	}

	@Override
	public Employee updateEmployee(Employee emp) throws BusinessException {
		Employee original = employeeMap.get(emp.getId());
		if (original == null) {
			return null;
		}

		if (emp.getDesignation() != null) {
			original.setDesignation(emp.getDesignation());
		}
		if (emp.getEmployeeName() != null) {
			original.setEmployeeName(emp.getEmployeeName());
		}
		if (emp.getSalary() != null) {
			original.setSalary(emp.getSalary());
		}
		return original;
	}

	@Override
	public boolean deleteEmployee(Employee emp) throws BusinessException {
		Employee deletedObj = employeeMap.remove(emp.getId());
		return deletedObj != null;

	}

	@Override
	public void addEmployee(Employee emp) throws BusinessException {
		employeeMap.put(emp.getId(), emp);

	}

	@Override
	public long getAllEmployeesSize() throws BusinessException {
		return employeeMap.size();
	}

	@Override
	public boolean exists(Employee emp) {
		return employeeMap.containsKey(emp.getId());
	}
	
	 

}
