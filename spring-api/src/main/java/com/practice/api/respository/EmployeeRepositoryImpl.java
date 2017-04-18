package com.practice.api.respository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.practice.api.entity.Employee;
@Repository
public class EmployeeRepositoryImpl implements EmployeeRepositiry {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Employee> findAll() {
		TypedQuery<Employee> query = em.createNamedQuery("Employee.findAll",Employee.class);
		return query.getResultList();
	}

	@Override
	public Employee findOne(String id) {
		return em.find(Employee.class, id);
	}

	@Override
	public Employee create(Employee emp) {
		em.persist(emp);
		return emp;
	}

	@Override
	public Employee update(String id, Employee emp) {
		return em.merge(emp);
	}

	@Override
	public void delete(Employee emp) {
		em.remove(emp);
	}

	@Override
	public Employee findByEmail(String email) {
		return null;
	}

}
