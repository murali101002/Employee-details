package com.practice.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practice.api.entity.Employee;
import com.practice.api.exception.BadRequestException;
import com.practice.api.exception.EntityNotFoundException;
import com.practice.api.respository.EmployeeRepositiry;
@Service //Acts similar to @Component/@Controller and responsible for handling the exceptions like 400 or 404 etc.,
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepositiry empRepository;
	
	@Override
	@Transactional(readOnly=true)
	public List<Employee> findAll() {
		empRepository.findAll();
		return null;
	}

	@Override
	@Transactional(readOnly=true)
	public Employee findOne(String id) {
		Employee existing = empRepository.findOne(id);
		if(existing == null){
			throw new EntityNotFoundException("Employee not found");
		}
		return existing;
	}

	@Override
	@Transactional
	public Employee create(Employee emp) {
		Employee existing = empRepository.findByEmail(emp.getEmail());
		if(existing != null){
			throw new BadRequestException("Employee already exists");
		}
		return empRepository.create(emp);
	}

	@Override
	@Transactional
	public Employee update(String id, Employee emp) {
		Employee existing = empRepository.findOne(id);
		if(existing == null){
			throw new EntityNotFoundException("Employee not found");
		}
		return empRepository.update(id, emp);
	}

	@Override
	@Transactional
	public void delete(String id) {
		Employee existing = empRepository.findOne(id);
		if(existing == null){
			throw new EntityNotFoundException("Employee not found");
		}
		empRepository.delete(existing);
		
	}

}
