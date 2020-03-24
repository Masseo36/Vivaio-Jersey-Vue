package com.topjavatutorial.service;

import java.util.List;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.topjavatutorial.entità.Employee;
import com.topjavatutorial.dao.EmployeeDAO;

public class EmployeeService {

	private EmployeeDAO employeeDAO = new EmployeeDAO();

	// Login
	public List<Employee> login(String username, String password) {
		List<Employee> employees = employeeDAO.login(username, password);
		return employees;

	}

	// Send e-mail
	public void sendEmail(@PathParam("username") String username, @PathParam("password") String password) {
		employeeDAO.sendEmail(username, password);
	}

	// Registro employee
	public Response registraEmployee(Employee emp) {
		employeeDAO.registraEmployee(emp);
		return Response.ok().build();
	}
	
	// Recupera password
		public List<String> recuperaPassword(@PathParam("username") String username) {
			List<String> passwordRecuperata = employeeDAO.recuperaPassword(username);
			return passwordRecuperata;
		}

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

	// Cerco employee per id
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

	// Aggiorno employee
	public Response updateEmployee(int id, Employee emp) {
		int count = employeeDAO.updateEmployee(id, emp);
		if (count == 0) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		return Response.ok().build();
	}

	// Cambia password iniziale
	public Response cambiaPasswordIniziale(int id, String nuovaPassword, Employee emp) {
		int count = employeeDAO.cambiaPasswordIniziale(id, nuovaPassword, emp);
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
