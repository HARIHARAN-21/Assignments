package com.empapplicationdemo.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.empapplicationdemo.dto.Employeedto;
import com.empapplicationdemo.model.Department;
import com.empapplicationdemo.model.Employee;
import com.empapplicationdemo.repository.EmployeeInterface;

@Repository
public class EmployeeDao implements EmployeeInterface{
	
	@Autowired
	public JdbcTemplate jdbctemplate;
	
	public Boolean checkDepartment(String deptname) {
		String sql = "Select deptname from depart";
		List<String> dept = jdbctemplate.queryForList(sql, String.class);
		return dept.contains(deptname);
	}
	
	
	public List<Department> getdept(){
		String sql = "Select deptname from depart";
		List<Department> dept = jdbctemplate.query(sql, new BeanPropertyRowMapper<Department>(Department.class));
		return dept; 
	}
	
	public Integer getIdbyName(String name) {
		String sql = "Select id from depart where deptname='"+name+"'";
		Integer deptid = jdbctemplate.queryForObject(sql, Integer.class);
		return deptid;
	}
	
	
	public List<Map<String,Object>> getdata(){
		String sql = "Select emp.*,dept.deptname from employeemanage emp join depart dept on emp.dept_id=dept.id";
		List<Map<String,Object>> list = jdbctemplate.queryForList(sql);
		return list;
	}
	
	
	@Override
	public List<Employee> getEmployees() {
		String sql = "Select emp.*,dept.id as deptid from employeemanage emp join depart dept on emp.dept_id=dept.id";
		List<Employee> list = jdbctemplate.query(sql, new BeanPropertyRowMapper(Employee.class));
		return list;
	}

	@Override
	public void insertEmployee(Employee employee) {
		String deptname=employee.getDepartment().getDeptname();
		if(!checkDepartment(deptname)) {
			String sql="Insert into depart values(1,?)";
			jdbctemplate.update(sql, deptname);
		}
		String sql = "Insert into employeemanage values(?,?,?,?,?)";
		jdbctemplate.update(sql, new Object[] {1,employee.getName(),employee.getCity(),getIdbyName(deptname),employee.getMobile_no()}); 
	}
	
	@Override
	public void updateData(Employeedto employeedto) {
		String sql = "update employeemanage set city=?,mobile_no=? where id=?";
		jdbctemplate.update(sql,new Object[] {employeedto.getCity(),employeedto.getMobile_no(),employeedto.getId()});
	}
	
	@Override
	public void deleteData(int id) {
		String sql = "delete from employeemanage where id=?";
		jdbctemplate.update(sql,new Object[] {id}); 
	}
	
}
