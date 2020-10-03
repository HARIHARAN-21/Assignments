package com.empapplicationdemo.repository;

import java.util.List;

import com.empapplicationdemo.dto.Employeedto;
import com.empapplicationdemo.model.Employee;

public interface EmployeeInterface {
	List<Employee> getEmployees();
	
	void insertEmployee(Employee employee);
	
	void updateData(Employeedto employeedto);
	
	void deleteData(int id);
}
