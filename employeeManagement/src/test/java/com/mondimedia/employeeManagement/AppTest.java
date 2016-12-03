package com.mondimedia.employeeManagement;

import com.mondimedia.memory.RequestHandler;
import com.mondimedia.memory.business.EmployeeService;
import com.mondimedia.memory.common.BusinessException;
import com.mondimedia.memory.common.Employee;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


public class AppTest extends TestCase {
	 
	public AppTest(String testName) {
		super(testName);
	}

	 
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		String add1="add 1-Anas1-programmer-1000";
		String add2="add 2-Anas2-designer-1000";
		String add3="add 3-Anas3-manager-1000";
		String add4="add 4-Anas4-doctor-1000";
		try {
			RequestHandler.instance().processRequest(add1);
			RequestHandler.instance().processRequest(add2);
			RequestHandler.instance().processRequest(add3);
			RequestHandler.instance().processRequest(add4);
		} catch (BusinessException e) {
		}
	}
	public void testAddEmployeeSuccess() {
		String add="add 5-Habiba-doctor-1000";
		try {
			RequestHandler.instance().processRequest(add);
			long allEmployeesSize = EmployeeService.instance().getAllEmployeesSize();
			assertEquals(allEmployeesSize, 5); 
		} catch (BusinessException e) {
			 fail();
		}
	}
	public void testAddEmployeeMalformedCommand() {
		String add="add 1-programmer-0";
		try {
			RequestHandler.instance().processRequest(add);
			 fail();
		} catch (BusinessException e) {
			assertTrue(true);
		}
	}
	public void testUpdateEmployeeSuccess() {
		String update="update 1-salary-6000";
		try {
			RequestHandler.instance().processRequest(update);
			Employee emp=new Employee();
			emp.setId(1);
			Employee employee = EmployeeService.instance().getEmployee(emp);
			assertEquals(employee.getSalary().intValue(), 6000);
		} catch (BusinessException e) {
			fail();
		}
	}
	public void testUpdateEmployeeNotFoundSuccess() {
		String update="update 52-salary-6000";
		try {
			RequestHandler.instance().processRequest(update);
			assertTrue(true);
		} catch (BusinessException e) {
			fail();
		}
	}
	public void testUpdateEmployeeMalformed() {
		String update="update 52--6000";
		try {
			RequestHandler.instance().processRequest(update);
			assertTrue(true);
		} catch (BusinessException e) {
			fail();
		}
	}
	public void testDeleteEmployeeSuccess() {
		String update="del 1";
		try {
			RequestHandler.instance().processRequest(update);
			long allEmployeesSize = EmployeeService.instance().getAllEmployeesSize();
			assertEquals(allEmployeesSize, 3);
		} catch (BusinessException e) {
			fail();
		}
	}
	public void testDeleteEmployeeNotFoundSuccess() {
		String del="del 5000";
		try {
			RequestHandler.instance().processRequest(del);
			long allEmployeesSize = EmployeeService.instance().getAllEmployeesSize();
			assertEquals(allEmployeesSize, 4);
		} catch (BusinessException e) {
			fail();
		}
	}
	public void testPrintEmployeeSuccess() {
		String print="print 1";
		try {
			RequestHandler.instance().processRequest(print);
			assertTrue(true);
		} catch (BusinessException e) {
			fail();
		}
	}
	public void testPrintEmployeeMalFormedSuccess() {
		String print="print ";
		try {
			RequestHandler.instance().processRequest(print);
			fail();
		} catch (BusinessException e) {
			assertTrue(true);
		}
	}
	
	public void testPrintAllEmployeeSuccess() {
		String print="printall asc";
		try {
			RequestHandler.instance().processRequest(print);
			assertTrue(true);
		} catch (BusinessException e) {
			fail();
		}
	}
}
