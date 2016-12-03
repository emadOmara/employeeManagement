package com.mondimedia.memory.dao;

import java.util.List;

import com.mondimedia.memory.common.BusinessException;
import com.mondimedia.memory.common.Employee;

public interface EmployeeDao {

	List<Employee> getAllEmployees(int order) throws BusinessException;

	Employee getEmployee(int id) throws BusinessException;

	Employee updateEmployee(Employee emp) throws BusinessException;

	boolean deleteEmployee(Employee emp) throws BusinessException;

	void addEmployee(Employee emp) throws BusinessException;

	long getAllEmployeesSize() throws BusinessException;

	boolean exists(Employee emp);

}
