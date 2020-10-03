package com.empapplicationdemo.coontroller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.empapplicationdemo.dao.EmployeeDao;
import com.empapplicationdemo.dto.Employeedto;
import com.empapplicationdemo.model.Department;
import com.empapplicationdemo.model.Employee;

@RestController
public class EmployeeController {
	
	@Autowired
	public EmployeeDao empdao;
	
	@GetMapping(value="/display")
	public List<Employee> EmployeeData() {
		List<Employee> list = empdao.getEmployees();
		return list;
	}
	
	@PostMapping(value="/insert")
	public String Insert(@RequestBody Employee employee) {
		empdao.insertEmployee(employee);
		return "Inserted Successfully";
	}
	
	@GetMapping(value="/getdepartment")
	public List<Department> getdept(){
		return empdao.getdept();
	}
	
	@DeleteMapping(value="/delete/{id}")
	public String delete(@PathVariable int id) {
		empdao.deleteData(id);
		return "Deleted Successfully";
	}
	
	@PutMapping(value="/update")
	public String update(@RequestBody Employeedto employeedto) {
		empdao.updateData(employeedto);
		return "Updated Successfully";
	}
	
	@GetMapping(value="/getdata")
	public List<Map<String,Object>> getdata(){
		return empdao.getdata();
	}
	
	
	
	@PostMapping(value="/get")
	public String getdate(@RequestParam String name) {
		return name;
	}
	
}
