package com.topjavatutorial;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.topjavatutorial.entità.Employee;
import com.topjavatutorial.entità.Mezzo;
import com.topjavatutorial.service.EmployeeService;
import com.topjavatutorial.service.MezzoService;

@Path("/")
public class MyResource {

	// I metodi usati qua chiamano all' interno un metodo creato nella classe
	// Service, che a sua volta chiama metodi della classe DAO
	private EmployeeService employeeService = new EmployeeService();
	private MezzoService mezzoService = new MezzoService();

	// Login
	@POST
	@Produces("application/json")
	@Path("login/{username}" + "login/{password}")
	public List<Employee> login(@PathParam("username") String username, @PathParam("password") String password) {

		List<Employee> employees = new ArrayList<Employee>();
		employees.addAll(employeeService.login(username, password));
		System.out.println(employees.toString());
		return employees;
	}

	// Registro employee
	@POST
	@Path("/registraEmployee")
	@Consumes("application/json")
	public Response registraEmployee(Employee emp) {
		employeeService.registraEmployee(emp);
		return Response.ok().build();
	}

//Ottengo tutti gli employees
	@GET
	@Produces("application/json")
	@Path("/employees")
	public List<Employee> getEmployees() {
		List<Employee> employees = new ArrayList<Employee>();
		employees.addAll(employeeService.getEmployees());
		// inserisco la riga "clear" altrimenti nella pagina web, dopo aver modificato
		// un mezzo,
		// mi mostra alternativamente vecchia e nuova lista di mezzi; sicuramente si può
		// evitare
		// questo artificio con Vue, ma al momento non sono capace
		employees.clear();
		employees.addAll(employeeService.getEmployees());
		return employees;
	}

//Creo un employee
	@POST
	@Path("/create")
	@Consumes("application/json")
	public Response addEmployee(Employee emp) {
		employeeService.addEmployee(emp);
		return Response.ok().build();
	}

	// Ottengo Employee per id, per la pagina modifica
	@GET
	@Path("employees/update/{id}")
	@Consumes("application/json")
	public List<Employee> findEmployeesById(@PathParam("id") int id) {
		List<Employee> employeesById = new ArrayList<Employee>();
		employeesById.addAll(employeeService.findEmployeesById(id));
		// inserisco la riga "clear" altrimenti nella pagina web, dopo aver modificato
		// un mezzo,
		// mi mostra alternativamente vecchia e nuova lista di mezzi; sicuramente si può
		// evitare
		// questo artificio con Vue, ma al momento non sono capace
		employeesById.clear();
		employeesById.addAll(employeeService.findEmployeesById(id));
		return employeesById;
	}

	// Ottengo employee per id, per la pagina dettagli
	@GET
	@Path("employee/{id}")
	@Consumes("application/json")
	public List<Employee> findEmployeesById2(@PathParam("id") int id) {
		List<Employee> employeesById = new ArrayList<Employee>();
		employeesById.addAll(employeeService.findEmployeesById(id));
		// inserisco la riga "clear" altrimenti nella pagina web, dopo aver modificato
		// un mezzo,
		// mi mostra alternativamente vecchia e nuova lista di mezzi; sicuramente si può
		// evitare
		// questo artificio con Vue, ma al momento non sono capace
		employeesById.clear();
		employeesById.addAll(employeeService.findEmployeesById(id));
		return employeesById;
	}

//Cerco un employee per età
	@POST
	@Path("/ricercaEmployee/{age}")
	@Consumes("application/json")
	public List<Employee> findEmployee(@PathParam("age") int age) {
		List<Employee> foundedEmployees = new ArrayList<Employee>();
		foundedEmployees.addAll(employeeService.findEmployee(age));
		return foundedEmployees;
	}

	// Cerco un employee per nome
	@POST
	@Path("/ricercaEmployee/name/{name}")
	@Consumes("application/json")
	public List<Employee> findEmployeeByName(@PathParam("name") String name) {
		List<Employee> foundedEmployees = new ArrayList<Employee>();
		foundedEmployees.addAll(employeeService.findEmployeeByname(name));
		return foundedEmployees;
	}

//Cerco un employee per cognome
	@POST
	@Path("/ricercaEmployee/surname/{surname}")
	@Consumes("application/json")
	public List<Employee> findEmployeeBySurname(@PathParam("surname") String surname) {
		List<Employee> foundedEmployees = new ArrayList<Employee>();
		foundedEmployees.addAll(employeeService.findEmployeeBySurname(surname));
		return foundedEmployees;
	}

	// Cerco un employee per età e nome
	@POST
	@Path("ricercaEmployee/{age}" + "ricercaEmployee/{name}")
	@Consumes("application/json")
	public List<Employee> findEmployeesByAgeAndName(@PathParam("age") int age, @PathParam("name") String name) {
		List<Employee> foundedEmployees = new ArrayList<Employee>();
		foundedEmployees.addAll(employeeService.findEmployeeByAgeAndName(age, name));
		return foundedEmployees;
	}

	// Cerco un employee per età e cognome
	@POST
	@Path("ricercaEmployee/age+surname/{age}" + "ricercaEmployee/age+surname/{surname}")
	@Consumes("application/json")
	public List<Employee> findEmployeesByAgeAndSurname(@PathParam("age") int age,
			@PathParam("surname") String surname) {

		List<Employee> foundedEmployees = new ArrayList<Employee>();
		foundedEmployees.addAll(employeeService.findEmployeeByAgeAndSurname(age, surname));
		return foundedEmployees;
	}

	// Cerco un employee per nome e cognome
	@POST
	@Path("ricercaEmployee/name+surname/{name}" + "ricercaEmployee/name+surname/{surname}")
	@Consumes("application/json")
	public List<Employee> findEmployeesByNameAndSurname(@PathParam("name") String name,
			@PathParam("surname") String surname) {

		List<Employee> foundedEmployees = new ArrayList<Employee>();
		foundedEmployees.addAll(employeeService.findEmployeeByNameAndSurname(name, surname));
		return foundedEmployees;
	}

	// Cerco un employee con tutti i campi
	@POST
	@Path("ricercaEmployee/ricercaCompleta/{age}" + "ricercaEmployee/ricercaCompleta/{name}"
			+ "ricercaEmployee/ricercaCompleta/{surname}")
	@Consumes("application/json")
	public List<Employee> findEmployeesComplete(@PathParam("age") int age, @PathParam("name") String name,
			@PathParam("surname") String surname) {

		List<Employee> foundedEmployees = new ArrayList<Employee>();
		foundedEmployees.addAll(employeeService.findEmployeeComplete(age, name, surname));
		System.out.println(foundedEmployees.toString());
		return foundedEmployees;
	}

//Aggiorno employee 
	@PUT
	@Path("/employees/update/{id}")
	@Consumes("application/json")
	public Response updateEmployee(@PathParam("id") int id, Employee emp) {
		System.out.println("Qua");
		System.out.println(emp.getAge());
		employeeService.updateEmployee(id, emp);
		return Response.ok().build();
	}

//Cancello employee
	@DELETE
	@Path("/delete/{id}")
	@Consumes("application/json")
	public Response deleteEmployee(@PathParam("id") int id) {
		employeeService.deleteEmployee(id);
		return Response.ok().build();
	}

//Ottengo tutti i mezzi
	@GET
	@Path("/mezzi")
	@Produces("application/json")
	public List<Mezzo> getMezzi() {
		List<Mezzo> mezzi = new ArrayList<Mezzo>();
		mezzi.addAll(mezzoService.getMezzi());
		// inserisco la riga "clear" altrimenti nella pagina web, dopo aver modificato
		// un mezzo,
		// mi mostra alternativamente vecchia e nuova lista di mezzi; sicuramente si può
		// evitare
		// questo artificio con Vue, ma al momento non sono capace
		mezzi.clear();
		mezzi.addAll(mezzoService.getMezzi());
		return mezzi;
	}

//Creo un mezzo
	@POST
	@Path("/createMezzo")
	@Consumes("application/json")
	public Response createMezzo(Mezzo mez) {
		mezzoService.createMezzo(mez);
		return Response.ok().build();
	}

	// ATTENZIONE! XXXXXXXX DA qui a "Fino a qua" ho sviluppato i metodi ricerca
	// direttamente nel Front-end XXXXXXXX
	// Cerco mezzi per idMezzo
	@GET
	@Path("mezzi/update/{idMezzo}")
	@Consumes("application/json")
	public List<Mezzo> findMezziByIdMezzo(@PathParam("idMezzo") long idMezzo) {
		List<Mezzo> mezziByIdMezzo = new ArrayList<Mezzo>();
		mezziByIdMezzo.addAll(mezzoService.findMezziByIdMezzo(idMezzo));
		// inserisco la riga "clear" altrimenti nella pagina web, dopo aver modificato
		// un mezzo,
		// mi mostra alternativamente vecchia e nuova lista di mezzi; sicuramente si può
		// evitare
		// questo artificio con Vue, ma al momento non sono capace
		mezziByIdMezzo.clear();
		mezziByIdMezzo.addAll(mezzoService.findMezziByIdMezzo(idMezzo));
		return mezziByIdMezzo;
	}

	// Cerco mezzo per marca, modello, alimentazione
	@POST
	@Path("ricercaMezzi/{marca}" + "ricercaMezzi/{modello}" + "ricercaMezzi/{carburante}")
	@Consumes("application/json")
	public List<Mezzo> findMezzi(@PathParam("marca") String marca, @PathParam("modello") String modello,
			@PathParam("carburante") String carburante) {
		List<Mezzo> mezziByMarcaModelloCarburante = new ArrayList<Mezzo>();
		mezziByMarcaModelloCarburante.addAll(mezzoService.findMezzi(marca, modello, carburante));
		return mezziByMarcaModelloCarburante;
	}

//Cerco mezzi della stessa marca
	@POST
	@Path("ricercaMezzi/{marca}")
	@Consumes("application/json")
	public List<Mezzo> findMezziBymarca(@PathParam("marca") String marca) {
		List<Mezzo> mezziByMarca = new ArrayList<Mezzo>();
		mezziByMarca.addAll(mezzoService.findMezziByMarca(marca));
		return mezziByMarca;
	}

// Cerco mezzi dello stesso modello
	@POST
	@Path("ricercaMezzi/mezzi/{modello}")
	@Consumes("application/json")
	public List<Mezzo> findMezziByModello(@PathParam("modello") String modello) {
		List<Mezzo> mezziByModello = new ArrayList<Mezzo>();
		mezziByModello.addAll(mezzoService.findMezziByModello(modello));
		return mezziByModello;
	}

// Cerco mezzi con stessa alimentazione
	@POST
	@Path("ricercaMezzi/mezzi/alimentazione/{carburante}")
	@Consumes("application/json")
	public List<Mezzo> findMezziByCarburante(@PathParam("carburante") String carburante) {
		List<Mezzo> mezziByCarburante = new ArrayList<Mezzo>();
		mezziByCarburante.addAll(mezzoService.findMezziByCarburante(carburante));
		return mezziByCarburante;
	}

	// XXXXXXXX Fino a qua XXXXXXXX

	// Ottengo tutti i mezzi nella pagina di ricerca
	@GET
	@Path("/ricercaMezzi")
	@Produces("application/json")
	public List<Mezzo> getMezziInRicerca() {
		List<Mezzo> mezzi = new ArrayList<Mezzo>();
		mezzi.addAll(mezzoService.getMezzi());
		// inserisco la riga "clear" altrimenti nella pagina web, dopo aver modificato
		// un mezzo,
		// mi mostra alternativamente vecchia e nuova lista di mezzi; sicuramente si può
		// evitare
		// questo artificio con Vue, ma al momento non sono capace
		mezzi.clear();
		mezzi.addAll(mezzoService.getMezzi());
		return mezzi;
	}

	// Ottengo mezzo per id, per la pagina dettagli
	@GET
	@Path("mezzo/{idMezzo}")
	@Consumes("application/json")
	public List<Mezzo> findMezziByIdMezzo2(@PathParam("idMezzo") long idMezzo) {
		List<Mezzo> mezziByIdMezzo = new ArrayList<Mezzo>();
		mezziByIdMezzo.addAll(mezzoService.findMezziByIdMezzo(idMezzo));
		// inserisco la riga "clear" altrimenti nella pagina web, dopo aver modificato
		// un mezzo,
		// mi mostra alternativamente vecchia e nuova lista di mezzi; sicuramente si può
		// evitare
		// questo artificio con Vue, ma al momento non sono capace
		mezziByIdMezzo.clear();
		mezziByIdMezzo.addAll(mezzoService.findMezziByIdMezzo(idMezzo));
		return mezziByIdMezzo;
	}

	// Aggiorno mezzo
	@PUT
	@Path("mezzi/update/{idMezzo}")
	@Consumes("application/json")
	public Response updateMezzo(@PathParam("idMezzo") int idMezzo, Mezzo mez) {

		mezzoService.updateMezzo(idMezzo, mez);
		return Response.ok().build();
	}

//Cancello mezzo
	@DELETE
	@Path("/mezzi/delete/{idMezzo}")
	@Consumes("application/json")
	public Response deleteMezzo(@PathParam("idMezzo") int idMezzo) {
		mezzoService.deleteMezzo(idMezzo);
		return Response.ok().build();
	}
}