package api.employee.service;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import api.employee.dao.EmployeeDao;
import api.employee.dao.EmployeeDaoImpl;
import api.employee.entity.Employee;
import api.employee.exception.EmployeeException;

/**
 * 
 * @author manjiri
 *
 */
public class EmployeeServiceImpl implements EmployeeService {
	EmployeeDao empDao = new EmployeeDaoImpl();

	/**
	 * add new record in Employee table
	 */
	@Override
	public int addEmployee(Employee emp) throws EmployeeException {
		return empDao.registerEmployee(emp);

	}

	/**
	 * reads all employee records from db
	 */
	@Override
	public List<Employee> getAllEmployees() throws EmployeeException {
		empDao.displayAllEmployees().stream().forEach(System.out::println);
		return empDao.displayAllEmployees();
	}

	/**
	 * returns employee records for specified marital status
	 */
	@Override
	public List<Employee> searchEmployeeOnMaritalstatus(String maritalStatus) throws EmployeeException {
		List<Employee> empList = null,empListForSpecifiedMaritalStatus = null;
		if (maritalStatus != null && !maritalStatus.isEmpty()) {
			empList = empDao.displayAllEmployees();
			empListForSpecifiedMaritalStatus = empList.stream().filter(e -> e.getMaritalStatus().toLowerCase().matches(maritalStatus.toLowerCase())).collect(Collectors.toList());
			if(empListForSpecifiedMaritalStatus.size() >= 1) {
				empListForSpecifiedMaritalStatus.stream().forEach(System.out::println);
			}else {
				System.out.println("\nNo employee record found for specified marital status! "+maritalStatus);
			}
					
		} else {
			System.out.println("\nNo Input provided! Please provide Marital Status to search! ");
		}
		return empList;
	}

	/**
	 * returns employee records for specified gender
	 */
	@Override
	public List<Employee> searchEmployeeOnGender(String gender) throws EmployeeException {
		List<Employee> empList = null;
		List<Employee> empListForSpecifiedGender = null;
		if (gender != null && !gender.isEmpty()) {
			empList = empDao.displayAllEmployees();
			empListForSpecifiedGender = empList.stream()
					.filter(e -> e.getGender().toLowerCase().matches(gender.toLowerCase()))
					.collect(Collectors.toList());
			if (empListForSpecifiedGender.size() >= 1) {
				empListForSpecifiedGender.stream().forEach(System.out::println);
			} else {
				System.out.println("\nNo employee record found for specified gender! -"+gender);
			}
		} else {
			System.out.println("\nNo Input provided! Please provide gender to search! ");
		}
		return empList;
	}

	/**
	 * returns employee records for specified contact_number
	 */
	@Override
	public List<Employee> searchEmployeeOnContactNumber(String contactNumber) throws EmployeeException {
		List<Employee> empList = null, matchFoundList = null;
		if (contactNumber != null && !contactNumber.isEmpty()) {
			empList = empDao.displayAllEmployees();
			matchFoundList = empList.stream().filter(e -> e.getMobileNumber().matches(contactNumber))
					.collect(Collectors.toList());// forEach(System.out::println);
			if (matchFoundList.size() >= 1) {
				matchFoundList.stream().forEach(System.out::println);
			} else {
				System.out.println("\nNo record found for mobile number - " + contactNumber);
			}
		} else {
			System.out.println("\nNo Input provided! Please provide contact number to search! ");
		}
		return matchFoundList;
	}

	/**
	 * returns employee records for specified name (firstName||middleName||lastName)
	 */
	@Override
	public List<Employee> searchEmployeeOnName(String empName) throws EmployeeException {
		List<Employee> empList = null, searchListOnFirstName = null, searchListOnMiddleName = null,searchListOnLastName = null ;
		if (empName != null && !empName.isEmpty()) {
			empList = empDao.displayAllEmployees();
			searchListOnFirstName = empList.stream().filter(e -> e.getFirstName().matches(empName)).collect(Collectors.toList());//.forEach(System.out::println);
			searchListOnMiddleName = empList.stream().filter(e -> e.getMiddleName().matches(empName)).collect(Collectors.toList());//.forEach(System.out::println);
			searchListOnLastName = empList.stream().filter(e -> e.getLastName().matches(empName)).collect(Collectors.toList());//.forEach(System.out::println);
			
			if(searchListOnFirstName.size()<1 && searchListOnMiddleName.size()<1 && searchListOnLastName.size()<1 ) {
				System.out.println("No Employee Record exists for a specified Name! "+empName);
			}else {
				searchListOnFirstName.stream().forEach(System.out::println);
				searchListOnMiddleName.stream().forEach(System.out::println);
				searchListOnLastName.stream().forEach(System.out::println);
			}
		} else {
			System.out.println("No Input provided! Please provide Employee Name to search! ");
		}
		return empList;
	}

	/**
	 * deletes employee records for specified employeeId (it's a soft delete, by
	 * changing employee status to 'InActive')
	 */
	@Override
	public int deleteEmployee(int empId) throws EmployeeException {
		return empDao.deleteEmployee(empId);
	}

	/**
	 * edits employee record for specified details
	 */
	@Override
	public void editEmployee(Employee emp) throws EmployeeException {
		empDao.editEmployee(emp);
	}

	/**
	 * returns all department list
	 */
	public void getListOfAllDepartments() throws EmployeeException {
		List<Employee> empList = null;
		System.out.println("\nFetching Department List ");
		empList = empDao.displayAllEmployees();
		empList.stream().map(Employee::getDepartment).distinct().forEach(System.out::println);
	}

	/**
	 * returns employeeList working in specified department.
	 */
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
		if (!departmentFound) {
			System.out.println("No employee record found with Department name :: " + departmentName);
		}
		return empList;
	}

	/**
	 * returns department wise list of employees
	 */
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

	/**
	 * returns average salary for each department
	 */
	@Override
	public Set<Entry<String, Double>> getAvgSalaryPerDepartment() throws EmployeeException {
		List<Employee> empList = null;
		empList = empDao.displayAllEmployees();
		Map<String, Double> avgSalaryOfDepartments = empList.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingInt(Employee::getSalary)));
		Set<Entry<String, Double>> avgSalEntrySet = avgSalaryOfDepartments.entrySet();
		System.out.println("Department     |   Average Salary");
		for (Entry<String, Double> entry : avgSalEntrySet) {
			System.out.println(entry.getKey() + "|" + entry.getValue());
		}

		return avgSalEntrySet;

	}

}
