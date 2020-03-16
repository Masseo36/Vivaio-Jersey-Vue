package com.topjavatutorial.dao;

import java.util.List;
import javax.ws.rs.PathParam;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.topjavatutorial.AES;
import com.topjavatutorial.entità.Employee;

public class EmployeeDAO {

	// Login
	public List<Employee> login(@PathParam("username") String username, @PathParam("password") String password) {

		// Cripto la password con sistema AES 256 – Advanced Encryption Standard
		final String secretKey = "ssshhhhhhhhhhh!!!!";

		String originalPassword = password;
		String encryptedPassword = AES.encrypt(originalPassword, secretKey);
		// String decryptedPassword = AES.decrypt(encryptedPassword, secretKey);

		Session session = SessionUtil.getSession();
		Query query = session
				.createQuery("from Employee emp where emp.username =:username and emp.password =:password");
		query.setParameter("username", username);
		query.setParameter("password", encryptedPassword);
		List<Employee> employees = query.list();
		session.close();
		return employees;
	}

	// Registro employee
	public void registraEmployee(Employee bean) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		registraEmployee(session, bean);
		tx.commit();
		session.close();
	}

	private void registraEmployee(Session session, Employee bean) {

		Query query = session.createQuery("from Employee");
		List<Employee> employees = query.list();

		Employee employee = new Employee();

		// Cripto la password con sistema AES 256 – Advanced Encryption Standard
		final String secretKey = "ssshhhhhhhhhhh!!!!";

		String originalPassword = bean.getPassword();
		String encryptedPassword = AES.encrypt(originalPassword, secretKey);
		// String decryptedPassword = AES.decrypt(encryptedPassword, secretKey);

		employee.setName(bean.getName());
		employee.setSurname(bean.getSurname());
		employee.setAge(bean.getAge());
		employee.setUsername(bean.getUsername());
		employee.setPassword(encryptedPassword);
		session.save(employee);
	}

	// Creo un employee
	public void addEmployee(Employee bean) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		addEmployee(session, bean);
		tx.commit();
		session.close();
	}

	private void addEmployee(Session session, Employee bean) {
		Employee employee = new Employee();

		employee.setName(bean.getName());
		employee.setSurname(bean.getSurname());
		employee.setAge(bean.getAge());
		employee.setUsername(bean.getName());
		employee.setPassword("password");
		session.save(employee);
	}

	// Ottengo tutti gli employees
	public List<Employee> getEmployees() {
		Session session = SessionUtil.getSession();
		Query query = session.createQuery("from Employee");
		List<Employee> employees = query.list();
		session.close();
		return employees;
	}

	// Cerco un employee per id
	public List<Employee> findEmployeeById(int id) {
		Session session = SessionUtil.getSession();
		Query query = session.createQuery("from Employee emp where emp.id =:id");
		query.setParameter("id", id);
		List<Employee> employeesById = query.list();
		session.close();

		return employeesById;
	}

	// Cerco un employee per età
	public List<Employee> findEmployee(@PathParam("age") int age) {
		Session session = SessionUtil.getSession();
		Query query = session.createQuery("from Employee emp where emp.age =:age");
		query.setParameter("age", age);
		List<Employee> employeesByAge = query.list();
		session.close();
		return employeesByAge;
	}

	// Cerco un employee per nome
	public List<Employee> findEmployeeByName(@PathParam("name") String name) {
		Session session = SessionUtil.getSession();
		Query query = session.createQuery("from Employee emp where emp.name =:name");
		query.setParameter("name", name);
		List<Employee> foundedEmployees = query.list();
		session.close();
		return foundedEmployees;
	}

	// Cerco un employee per cognome
	public List<Employee> findEmployeeBySurname(@PathParam("surname") String surname) {
		Session session = SessionUtil.getSession();
		Query query = session.createQuery("from Employee emp where emp.surname =:surname");
		query.setParameter("surname", surname);
		List<Employee> foundedEmployees = query.list();
		session.close();
		return foundedEmployees;
	}

	// Cerco un employee per età e nome
	public List<Employee> findEmployeeByAgeAndName(@PathParam("age") int age, @PathParam("name") String name) {
		Session session = SessionUtil.getSession();
		Query query = session.createQuery("from Employee emp where emp.age =:age and emp.name =:name");
		query.setParameter("age", age);
		query.setParameter("name", name);
		List<Employee> foundedEmployees = query.list();
		session.close();
		return foundedEmployees;
	}

	// Cerco un employee per età e cognome
	public List<Employee> findEmployeeByAgeAndSurname(@PathParam("age") int age, @PathParam("surname") String surname) {
		Session session = SessionUtil.getSession();
		Query query = session.createQuery("from Employee emp where emp.age =:age and emp.surname =:surname");
		query.setParameter("age", age);
		query.setParameter("surname", surname);
		List<Employee> foundedEmployees = query.list();
		session.close();
		return foundedEmployees;
	}

	// Cerco un employee per nome e cognome
	public List<Employee> findEmployeeByNameAndSurname(@PathParam("name") String name,
			@PathParam("surname") String surname) {
		Session session = SessionUtil.getSession();
		Query query = session.createQuery("from Employee emp where emp.name =:name and emp.surname =:surname");
		query.setParameter("name", name);
		query.setParameter("surname", surname);
		List<Employee> foundedEmployees = query.list();
		session.close();
		return foundedEmployees;
	}

	// Cerco un employee con tutti i campi
	public List<Employee> findEmployeeComplete(@PathParam("age") int age, @PathParam("name") String name,
			@PathParam("surname") String surname) {
		Session session = SessionUtil.getSession();
		Query query = session
				.createQuery("from Employee emp where emp.age =:age and emp.name =:name and emp.surname =:surname");
		query.setParameter("age", age);
		query.setParameter("name", name);
		query.setParameter("surname", surname);
		List<Employee> foundedEmployees = query.list();
		session.close();
		return foundedEmployees;
	}

	// Cancello employee
	public int deleteEmployee(int id) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		String hql = "delete from Employee where id = :id";
		Query query = session.createQuery(hql);
		query.setInteger("id", id);
		int rowCount = query.executeUpdate();
		System.out.println("Rows affected: " + rowCount);
		tx.commit();
		session.close();
		return rowCount;
	}

// Aggiorno employee
	public int updateEmployee(int id, Employee emp) {
		if (id <= 0)
			return 0;
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		String hql = "update Employee set name =:name, surname =:surname, age =:age where id =:id";
		Query query = session.createQuery(hql);
		query.setInteger("id", id);
		query.setString("name", emp.getName());
		query.setString("surname", emp.getSurname());
		query.setInteger("age", emp.getAge());
		int rowCount = query.executeUpdate();
		System.out.println("Rows affected: " + rowCount);
		tx.commit();
		session.close();
		return rowCount;
	}
}
