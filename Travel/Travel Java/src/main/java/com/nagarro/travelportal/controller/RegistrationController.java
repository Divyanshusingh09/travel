package com.nagarro.travelportal.controller;

import javax.validation.Valid;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.travelportal.Service.EmailService;
import com.nagarro.travelportal.Service.EmployeeService;
import com.nagarro.travelportal.model.Employee;

/**
 * The Class RegistrationController.
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class RegistrationController {

	/** The log. */
	private Logger log = Logger.getLogger(RegistrationController.class);

	/** The emp service. */
	@Autowired
	private EmployeeService empService;

	/** The email service. */
	@Autowired
	private EmailService emailService;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	/**
	 * Creates the user as employee.
	 *
	 * @param employee the employee
	 * @return the response entity
	 */
	@PostMapping("/register")
	public ResponseEntity<?> createUserAsEmployee(@Valid @RequestBody Employee employee) {

		// check if logged in employee already in system then he cannot register.
		if (empService.userAlreadyExist(employee)) {
			log.info("User " + employee.getEmail() + " is already registered");
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}

		// send a welcomeMail.
		String password = emailService.welcomeMail(employee.getEmail());

		// set password for new user.
		// set username as email address.
		employee.setUsername(employee.getEmail());
		employee.setPasswordAsString(password);
		employee.setPassword(bcryptEncoder.encode(password));
		try {
			if (empService.addEmployee(employee) != null) {
				log.info("User " + employee.getUsername() + " is succesfully registered");
				return new ResponseEntity<>(HttpStatus.OK);
			}
			;

		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
	}
}
