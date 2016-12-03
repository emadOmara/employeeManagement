package com.mondimedia.memory;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mondimedia.memory.business.EmployeeService;
import com.mondimedia.memory.common.BusinessException;
import com.mondimedia.memory.common.Constants;
import com.mondimedia.memory.common.Employee;
import com.mondimedia.memory.common.REQ_TYPE;
import com.mondimedia.memory.common.ValidationUtil;

/**
 * Handler for all command line requests
 * 
 * @author <a href="mailto:emad.omara85@gmail.com">Emad Omara</a>
 *
 */
public class RequestHandler {

	private static RequestHandler instance;

	final Logger logger = LoggerFactory.getLogger(RequestHandler.class);
	
	private RequestHandler() {

	}

	/**
	 * create single instance of the {@link RequestHandler}
	 * 
	 * @return
	 */
	public static RequestHandler instance() {
		if (instance == null) {

			synchronized (new Object()) {
				if (instance == null) {
					instance = new RequestHandler();
				}
			}
		}
		return instance;
	}

	/**
	 * process the input from the integration point 
	 * @param input
	 * @throws BusinessException
	 */
	public void processRequest(String input) throws BusinessException {
		logger.info("Process request for input : {}",input);
		if (StringUtils.isEmpty(input)) {
			throw new BusinessException(Constants.ERROR_MSG_MAL_FORMED_REQUEST);
		}

		String[] mainSections = input.split(Constants.REQUEST_COMMAND_DELIMITER);
		if (ArrayUtils.isEmpty(mainSections) || mainSections.length != 2) {
			throw new BusinessException(Constants.ERROR_MSG_MAL_FORMED_REQUEST);
		}
		String command = mainSections[0];
		logger.trace("command value : {}",command);
		String[] fields = mainSections[1].split(Constants.REQUEST_FIELD_DELIMITER);
		if (ArrayUtils.isEmpty(fields)) {
			throw new BusinessException(Constants.ERROR_MSG_MAL_FORMED_REQUEST);
		}
		switch (command) {
		case Constants.COMMAND_ADD:
			addEmployee(fields);
			break;
		case Constants.COMMAND_DEL:
			deleteEmployee(fields);
			break;
		case Constants.COMMAND_UPDATE:
			updateEmployee(fields);
			break;
		case Constants.COMMAND_PRINT:
			printEmployee(fields);
			break;
		case Constants.COMMAND_PRINTALL:// printall <ASC/DESC>
			printAll(fields);
			break;
		default:
			throw new BusinessException(Constants.ERROR_MSG_MAL_FORMED_REQUEST);
		}
	}

	private void printAll(String[] fields) throws BusinessException {
		validateSize(REQ_TYPE.PRINT_ALL, fields);
		int order = 1;
		String type = fields[0].toLowerCase();
		switch (type) {
		case Constants.COMMAND_ORDER_ASC:
			order = Constants.ORDER_ASC_TYPE;
			break;
		case Constants.COMMAND_ORDER_DESC:
			order = Constants.ORDER_DESC_TYPE;
			break;
		default:
			throw new BusinessException(Constants.ERROR_MSG_MAL_FORMED_REQUEST);
		}
		List<Employee> empList = EmployeeService.instance().getAllEmployees(order);
		ViewHandler.renderPrintAll(empList);

	}

	private void printEmployee(String[] fields) throws BusinessException {
		Employee emp = parsePrintRequest(fields);
		Employee fetchedEmployee = EmployeeService.instance().getEmployee(emp);
		ViewHandler.renderPrint(fetchedEmployee, emp.getId());
	}

	private void updateEmployee(String[] fields) throws BusinessException {
		Employee emp = parseUpdateRequest(fields);
		Employee fetchedEmployee = EmployeeService.instance().updateEmployee(emp);
		ViewHandler.renderUpdateEmployee(fetchedEmployee, emp.getId());
	}

	private void deleteEmployee(String[] fields) throws BusinessException {
		Employee emp = parseDelRequest(fields);
		boolean deleteSuccess = EmployeeService.instance().deleteEmployee(emp);
		ViewHandler.renderDeleteEmployee(emp, deleteSuccess);

	}

	private void addEmployee(String[] fields) throws BusinessException {
		Employee emp = parseAddRequest(fields);
		Set<ConstraintViolation<Employee>> result = ValidationUtil.validate(emp);
		if (CollectionUtils.isEmpty(result)) {
			EmployeeService.instance().addEmployee(emp);
			ViewHandler.renderAddEmployeeSuccess(emp);
		} else {
			ViewHandler.renderAddEmployeeValidationErrors(result);
		}

	}

	private void validateSize(REQ_TYPE type, String[] fields) throws BusinessException {

		if (fields.length != type.getSize()) {
			throw new BusinessException("Illegal number of paramters");
		}
	}

	// print <employee id>
	private Employee parsePrintRequest(String[] fields) throws BusinessException {
		validateSize(REQ_TYPE.PRINT, fields);

		Employee emp = new Employee();
		try {
			emp.setId(Integer.parseInt(fields[0]));
		} catch (Exception e) {
			throw new BusinessException("Error parsing employee ", e);
		}
		return emp;
	}

	// update <employee id>‐<NAME/DESIG/SALARY><New Value>
	private Employee parseUpdateRequest(String[] fields) throws BusinessException {
		validateSize(REQ_TYPE.UPDATE, fields);

		Employee emp = new Employee();
		try {
			emp.setId(Integer.parseInt(fields[0]));

			String updatedField = fields[1].toLowerCase();
			String fieldValue = fields[2];
			if (StringUtils.isNotEmpty(updatedField)) {
				switch (updatedField) {
				case "name":
					emp.setEmployeeName(fieldValue);
					break;
				case "design":
					emp.setDesignation(fieldValue);
					break;
				case "salary":
					emp.setSalary(new BigDecimal(fieldValue));
					break;
				default:
					throw new BusinessException(Constants.ERROR_MSG_ILLEGAL_ARGUMENT);
				}
			}

		} catch (Exception e) {
			throw new BusinessException("Error parsing employee ", e);
		}
		return emp;
	}

	// del <employee id>
	private Employee parseDelRequest(String[] fields) throws BusinessException {
		validateSize(REQ_TYPE.DEL, fields);

		Employee emp = new Employee();
		try {
			emp.setId(Integer.parseInt(fields[0]));
		} catch (Exception e) {
			throw new BusinessException("Error parsing employee ", e);
		}
		return emp;
	}

	// add 1001‐Ali‐Java Programmer‐10000
	private Employee parseAddRequest(String[] fields) throws BusinessException {
		validateSize(REQ_TYPE.ADD, fields);

		Employee emp = new Employee();
		try {
			emp.setId(Integer.parseInt(fields[0]));
			emp.setEmployeeName(fields[1]);
			emp.setDesignation(fields[2]);
			emp.setSalary(new BigDecimal(fields[3]));
		} catch (Exception e) {
			throw new BusinessException("Error parsing employee ", e);
		}
		return emp;
	}
	
	 
}
