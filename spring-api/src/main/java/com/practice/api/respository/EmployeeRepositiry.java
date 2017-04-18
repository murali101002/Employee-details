package com.practice.api.respository;

import java.util.List;

import com.practice.api.entity.Employee;

public interface EmployeeRepositiry {
	public List<Employee> findAll();
	public Employee findOne(String id);
	public Employee findByEmail(String email);
	public Employee create(Employee emp);
	public Employee update(String id, Employee emp);
	public void delete(Employee emp);
}
