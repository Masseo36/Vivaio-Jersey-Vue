package com.topjavatutorial.service;

import com.topjavatutorial.entità.Employee;
import com.topjavatutorial.entità.Mezzo;

import java.util.List;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.topjavatutorial.dao.EmployeeDAO;

public class EmployeeService {

	private EmployeeDAO employeeDAO = new EmployeeDAO();

	// Ottengo tutti gli employees
	public List<Employee> getEmployees() {
		List<Employee> employees = employeeDAO.getEmployees();
		return employees;
	}

	// Creo un employee
	public Response addEmployee(Employee emp) {
		employeeDAO.addEmployee(emp);
		return Response.ok().build();
	}
	
	//Cerco employee per id
	public List<Employee> findEmployeesById(int id) {
		List<Employee> employeesById = employeeDAO.findEmployeeById(id);
		return employeesById;

	}

	// Cerco un employee per età
	public List<Employee> findEmployee(@PathParam("age") int age) {
		List<Employee> employeesByAge = employeeDAO.findEmployee(age);
		return employeesByAge;
	}

	// Cerco un employee per nome
	public List<Employee> findEmployeeByname(@PathParam("name") String name) {
		List<Employee> foundedEmployees = employeeDAO.findEmployeeByName(name);
		return foundedEmployees;
	}

	// Cerco un employee per cognome
	public List<Employee> findEmployeeBySurname(@PathParam("surname") String surname) {
		List<Employee> foundedEmployees = employeeDAO.findEmployeeBySurname(surname);
		return foundedEmployees;
	}

	// Cerco un employee per età e nome
	public List<Employee> findEmployeeByAgeAndName(@PathParam("age") int age, @PathParam("name") String name) {
		List<Employee> foundedEmployees = employeeDAO.findEmployeeByAgeAndName(age, name);
		return foundedEmployees;
	}

	// Cerco un employee per età e cognome
	public List<Employee> findEmployeeByAgeAndSurname(@PathParam("age") int age, @PathParam("surname") String surname) {
		List<Employee> foundedEmployees = employeeDAO.findEmployeeByAgeAndSurname(age, surname);
		return foundedEmployees;
	}

	// Cerco un employee per nome e cognome
	public List<Employee> findEmployeeByNameAndSurname(@PathParam("name") String name,
			@PathParam("surname") String surname) {
		List<Employee> foundedEmployees = employeeDAO.findEmployeeByNameAndSurname(name, surname);
		return foundedEmployees;
	}

	// Cerco un employee con tutti i campi
	public List<Employee> findEmployeeComplete(@PathParam("age") int age, @PathParam("name") String name,
			@PathParam("surname") String surname) {
		List<Employee> foundedEmployees = employeeDAO.findEmployeeComplete(age, name, surname);
		return foundedEmployees;
	}
	
	//Aggiorno employee
	public Response updateEmployee(int id, Employee emp) {
		int count = employeeDAO.updateEmployee(id, emp);
		if (count == 0) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		return Response.ok().build();
	}

	// Cancello employee
	public Response deleteEmployee(@PathParam("id") int id) {
		int count = employeeDAO.deleteEmployee(id);
		if (count == 0) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		return Response.ok().build();
	}
}
