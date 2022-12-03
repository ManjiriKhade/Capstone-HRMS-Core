package api.employee.dao;

import api.employee.exception.EmployeeException;

import java.util.List;

import api.employee.entity.*;

public interface EmployeeDao {

	
	public int registerEmployee(Employee emp) throws EmployeeException;
	
	public List<Employee> displayAllEmployees() throws EmployeeException;
	
	public int deleteEmployee(int empID) throws EmployeeException;

	public void editEmployee(Employee emp) throws EmployeeException;;


}
