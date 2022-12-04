package api.employee.service;

import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import api.employee.entity.Employee;
import api.employee.exception.EmployeeException;

/**
 * 
 * @author manjiri
 *
 */
public interface EmployeeService {

	public int addEmployee(Employee emp) throws EmployeeException;

	public List<Employee> getAllEmployees() throws EmployeeException;

	public List<Employee> searchEmployeeOnMaritalstatus(String status) throws EmployeeException;

	public List<Employee> searchEmployeeOnGender(String gender) throws EmployeeException;

	public List<Employee> searchEmployeeOnContactNumber(String contactNumber) throws EmployeeException;

	public List<Employee> searchEmployeeOnName(String Name) throws EmployeeException;

	public void editEmployee(Employee emp) throws EmployeeException;

	public int deleteEmployee(int empId) throws EmployeeException;

	public void getListOfAllDepartments() throws EmployeeException;

	public Set<Entry<String, List<Employee>>> getEmpListForAllDepartment() throws EmployeeException;

	public List<Employee> getEmpListForInputDepartment(String departmentName) throws EmployeeException;

	public Set<Entry<String, Double>> getAvgSalaryPerDepartment() throws EmployeeException;

}