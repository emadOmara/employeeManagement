package com.mondimedia.memory;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import com.mondimedia.memory.business.EmployeeService;
import com.mondimedia.memory.common.BusinessException;
import com.mondimedia.memory.common.Employee;
/**
 * Handler to render response after each selected action 
 * @author <a href="mailto:emad.omara85@gmail.com">Emad Omara</a>
 *
 */
public class ViewHandler {

	public static void renderAddEmployeeSuccess(Employee emp) throws BusinessException {
		long size = EmployeeService.instance().getAllEmployeesSize();
		StringBuilder str = new StringBuilder("Employee ").append(emp.getEmployeeName())
				.append(" added successfully. Total no of employees =").append(size);
		System.out.println(str);
	}

	public static void renderAddEmployeeValidationErrors(Set<ConstraintViolation<Employee>> result) {
		for (ConstraintViolation<Employee> item : result) {
			System.out.println(item.getPropertyPath() + ":" + item.getMessage());
		}

	}

	public static void renderDeleteEmployee(Employee emp, boolean deleteSuccess) throws BusinessException {
		if (deleteSuccess) {
			long size = EmployeeService.instance().getAllEmployeesSize();
			System.out.println("Employee " + emp.getId() + " Deleted Successfully. Total no of employees "+size);
		} else {
			System.out.println("Employee " + emp.getId() + " not found ");
		}

	}

	public static void renderUpdateEmployee(Employee emp, int id) {
		if (emp == null) {
			System.out.println("Employee " + id + " not found");
		} else {
			System.out.println("Employee " + emp.getId() + " updated. " + emp.toString());
		}

	}

	public static void renderPrintAll(List<Employee> empList) {
		for (Employee emp : empList) {
			System.out.println(emp.toString());
		}

	}

	public static void renderPrint(Employee emp, int id) {
		if (emp == null) {
			System.out.println("Employee " + id + " not found");
		} else {
			System.out.println(emp.toString());
		}

	}
}
