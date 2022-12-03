package api.employee.service;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import api.employee.dao.EmployeeDao;
import api.employee.dao.EmployeeDaoImpl;
import api.employee.entity.Employee;
import api.employee.exception.EmployeeException;

public class EmployeeServiceImpl implements EmployeeService {
	EmployeeDao empDao = new EmployeeDaoImpl();

	@Override
	public int addEmployee(Employee emp) throws EmployeeException {
		return empDao.registerEmployee(emp);

	}

	@Override
	public List<Employee> getAllEmployees() throws EmployeeException {
		System.out.println("EmpService - getAllEmployees - ");
		empDao.displayAllEmployees().stream().forEach(System.out::println);
		return empDao.displayAllEmployees();
	}

	@Override
	public List<Employee> searchEmployeeOnMaritalstatus(String maritalStatus) throws EmployeeException {
		List<Employee> empList = null;
		if (maritalStatus != null) {
			System.out.println("\nFetching Employee Data for Marital Status - " + maritalStatus + ".....");
			empList = empDao.displayAllEmployees();
			empList.stream().filter(e -> e.getMaritalStatus().toLowerCase().matches(maritalStatus.toLowerCase()))
					.forEach(System.out::println);
		} else {
			System.out.println("\nPlease provide Marital Status to search! ");
		}
		return empList;
	}

	@Override
	public List<Employee> searchEmployeeOnGender(String gender) throws EmployeeException {
		List<Employee> empList = null;
		if (gender != null) {
			System.out.println("\nFetching Employee Data for gender - " + gender + ".....");
			empList = empDao.displayAllEmployees();
			empList.stream().filter(e -> e.getGender().toLowerCase().matches(gender.toLowerCase()))
					.forEach(System.out::println);
		} else {
			System.out.println("\nNo Employee record exists for given gender! ");
		}
		return empList;
	}

	@Override
	public List<Employee> searchEmployeeOnContactNumber(String contactNumber) throws EmployeeException {
		List<Employee> empList = null;
		if (contactNumber != null) {
			System.out.println("Fetching Employee Data for contact number - " + contactNumber + ".....");
			empList = empDao.displayAllEmployees();
			empList.stream().filter(e -> e.getMobileNumber().matches(contactNumber)).forEach(System.out::println);
		} else {
			System.out.println("\nNo Employee record exists for given contact number! ");
		}
		return empList;
	}

	@Override
	public List<Employee> searchEmployeeOnName(String empName) throws EmployeeException {
		List<Employee> empList = null;
		if (empName != null) {
			System.out.println("Fetching Employee Data for employee name - " + empName + ".....");
			empList = empDao.displayAllEmployees();
			empList.stream().filter(e -> e.getFirstName().matches(empName)).forEach(System.out::println);
			empList.stream().filter(e -> e.getMiddleName().matches(empName)).forEach(System.out::println);
			empList.stream().filter(e -> e.getLastName().matches(empName)).forEach(System.out::println);

		} else {
			System.out.println("No Employee Record exists for a given Name! ");
		}
		return empList;
	}

	@Override
	public int deleteEmployee(int empId) throws EmployeeException {
		return empDao.deleteEmployee(empId);
	}

	@Override
	public void editEmployee(Employee emp) throws EmployeeException {
		empDao.editEmployee(emp);
	}

	public void getListOfAllDepartments() throws EmployeeException {
		List<Employee> empList = null;
		System.out.println("\nFetching Department List ");
		empList = empDao.displayAllEmployees();
		empList.stream().map(Employee::getDepartment).distinct().forEach(System.out::println);
	}

	@Override
	public List<Employee> getEmpListForInputDepartment(String departmentName) throws EmployeeException {
		List<Employee> empList = null;
		boolean departmentFound = false;
		System.out.println("\nFetching Employee List for Department - " + departmentName);
		empList = empDao.displayAllEmployees();

		Map<String, List<Employee>> employeeListByDepartment = empList.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment));

		Set<Entry<String, List<Employee>>> empEntrySet = employeeListByDepartment.entrySet();
		for (Entry<String, List<Employee>> entry : empEntrySet) {
			if (entry.getKey().toLowerCase().contains(departmentName.toLowerCase())) {
				System.out.println("---------Department:: " + entry.getKey());
				empList = entry.getValue();
				empList.stream().forEach(System.out::println);
				System.out.println("----------------------------");
				departmentFound = true;
			} 
		}
		if(!departmentFound) {
			System.out.println("No employee record found with Department name :: " + departmentName);
		}
		return empList;
	}

	@Override
	public Set<Entry<String, List<Employee>>> getEmpListForAllDepartment() throws EmployeeException {
		List<Employee> empList = null;
		System.out.println("\nFetching Employee List for All Department - ");
		empList = empDao.displayAllEmployees();

		Map<String, List<Employee>> employeeListByDepartment = empList.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment));

		Set<Entry<String, List<Employee>>> empEntrySet = employeeListByDepartment.entrySet();

		for (Entry<String, List<Employee>> entry : empEntrySet) {
			System.out.println("---------Department:: " + entry.getKey());
			List<Employee> list = entry.getValue();
			list.stream().forEach(System.out::println);
			System.out.println("----------------------------");
		}
		return empEntrySet;
	}

	@Override
	public Set<Entry<String, Double>> getAvgSalaryPerDepartment() throws EmployeeException {
		List<Employee> empList = null;
		empList = empDao.displayAllEmployees();
		Map<String, Double> avgSalaryOfDepartments=
				empList.stream().collect(Collectors.groupingBy(Employee :: getDepartment,Collectors.averagingInt(Employee::getSalary)));
		 Set<Entry<String, Double>> avgSalEntrySet =  avgSalaryOfDepartments.entrySet();
			System.out.println("Department     |   Average Salary");
		 for (Entry<String, Double> entry : avgSalEntrySet) {
			System.out.println(entry.getKey()+"|"+entry.getValue());
		}
		 
		return avgSalEntrySet;
		

	}

}
