package com.nagarro.travelportal.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.travelportal.model.Employee;

/**
 * The Interface EmployeeDao.
 */
public interface EmployeeDao extends JpaRepository<Employee, String>{

}
