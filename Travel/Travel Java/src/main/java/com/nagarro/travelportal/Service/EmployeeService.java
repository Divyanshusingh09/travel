package com.nagarro.travelportal.Service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.travelportal.dao.EmployeeDao;
import com.nagarro.travelportal.model.Employee;


/**
 * The Class EmployeeService.
 */
@Service
public class EmployeeService {

	/** The empdao. */
	@Autowired
	private EmployeeDao empdao;

	/**
	 * Adds the employee.
	 *
	 * @param employee the employee
	 * @return the employee
	 */
	public Employee addEmployee(Employee employee) {
		return empdao.save(employee);
	}

	/**
	 * Gets the employee by username.
	 *
	 * @param username the username
	 * @return the employee by username
	 * @throws NoSuchElementException the no such element exception
	 */
	public Employee getEmployeeByUsername(String username) throws NoSuchElementException {
		return empdao.findById(username).get();
	}

	/**
	 * Gets the all employee.
	 *
	 * @return the all employee
	 */
	public List<Employee> getAllEmployee() {
		return empdao.findAll();
	}

	/**
	 * User already exist.
	 *
	 * @param employee the employee
	 * @return true, if successful
	 */
	public boolean userAlreadyExist(Employee employee) {
		// Logger.getLogger(EmployeeService.class).info(empdao.existsById(employee.getUsername()));
		if (empdao.existsById(employee.getEmail())) {
			return true;
		}
		return false;
	}
}
