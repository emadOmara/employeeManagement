package com.mondimedia.memory.business;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mondimedia.memory.common.BusinessException;
import com.mondimedia.memory.common.Constants;
import com.mondimedia.memory.common.Employee;
import com.mondimedia.memory.dao.DaoFactory;
import com.mondimedia.memory.dao.EmployeeDao;

/**
 * This class is responsible for the business logic for employee management.
 *
 * @author <a href="mailto:emad.omara85@gmail.com">Emad Omara</a>
 *
 */
public class EmployeeService {

	/**
	 * Singleton instance of the service
	 */
	private static EmployeeService instance;
	private EmployeeDao employeeDao;

	final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

	private EmployeeService() throws BusinessException {
		Properties props = new Properties();
		try {
			InputStream inStream = this.getClass().getClassLoader().getResourceAsStream("config.properties");
			props.load(inStream);
			String property = props.getProperty(Constants.DAO_TYPE_KEY);
			int daoType = Integer.parseInt(property);
			logger.debug("Dao type configured : {}",daoType);
			employeeDao = DaoFactory.getEmployeeDaoInstance(daoType);
			inStream.close();
		} catch (Exception e) {
			logger.error("Couldn't read dao meta dat", e);
			throw new BusinessException(e.getMessage(), e);
		}
	}

	/**
	 * create single instance of this service
	 *
	 * @return
	 * @throws BusinessException
	 */
	public static EmployeeService instance() throws BusinessException {
		if (instance == null) {
			synchronized (new Object()) {
				if (instance == null) {
					instance = new EmployeeService();
				}
			}
		}
		return instance;
	}

	public void addEmployee(Employee emp) throws BusinessException {
		if(employeeDao.exists(emp)){
			throw new BusinessException("Employee Id :"+emp.getId()+" Already exists");
		}
		employeeDao.addEmployee(emp);
	}

	public boolean deleteEmployee(Employee emp) throws BusinessException {
		return employeeDao.deleteEmployee(emp);

	}

	public Employee updateEmployee(Employee emp) throws BusinessException {
		emp = employeeDao.updateEmployee(emp);
		return emp;
	}

	public Employee getEmployee(Employee emp) throws BusinessException {
		return employeeDao.getEmployee(emp.getId());

	}

	public List<Employee> getAllEmployees(int order) throws BusinessException {
		return employeeDao.getAllEmployees(order);
	}

	public long getAllEmployeesSize() throws BusinessException {
		return employeeDao.getAllEmployeesSize();
	}

}
