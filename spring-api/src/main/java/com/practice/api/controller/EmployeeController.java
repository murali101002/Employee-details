package com.practice.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.practice.api.entity.Employee;
import com.practice.api.service.EmployeeService;

//@Controller /*Acts similar to @Component but name differs wrt context*/
//@ResponseBody /*Response from the given Request; Also called "View Resolver"*/
@RestController //Combination of @Controller and @ResponseBody
@RequestMapping(value="employees") //Generic to all methods in this class passing the value to every method
public class EmployeeController {
	
	@Autowired
	private EmployeeService empService;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Employee> findAll(){
		empService.findAll();
		return null;
	}
	@RequestMapping(method=RequestMethod.GET,value="{id}")
	public Employee findOne(@PathVariable("id") String empid){
		empService.findOne(empid);
		return null;
	}
	@RequestMapping(method=RequestMethod.POST)
	public Employee create(@RequestBody Employee emp){
		empService.create(emp);
		return null;
	}
	@RequestMapping(method=RequestMethod.PUT,value="{id}")
	public Employee update(@PathVariable("id") String empid,@RequestBody Employee emp){
		empService.update(empid, emp);
		return null;
	}
	@RequestMapping(method=RequestMethod.DELETE,value="{id}")
	public void delete(@PathVariable("id") String empid){
		empService.delete(empid);
	}
}
