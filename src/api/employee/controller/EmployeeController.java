package api.employee.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import api.employee.entity.Employee;
import api.employee.entity.UserCommandLineInputData;
import api.employee.exception.EmployeeException;
import api.employee.service.EmployeeService;
import api.employee.service.EmployeeServiceImpl;

public class EmployeeController {

	UserCommandLineInputData userDataObj = null;
	EmployeeService empService = new EmployeeServiceImpl();

	public List<Employee> getDataPerUserAction(int userInput, Employee emp, String userSearchData,UserCommandLineInputData userDataObj)
			throws EmployeeException {
		List<Employee> empList = new ArrayList<Employee>();
		Set<Entry<String,List<Employee>>> departmentWiseEmpList;
		Set<Entry<String,Double>> departmentWiseAvgSalaryList;
		switch (userInput) {
		case 1:
			empService.addEmployee(emp);
			break;
		case 2:	
			Employee empDataToEdit = null;
			empDataToEdit = copyUserData(userDataObj);
			empService.editEmployee(empDataToEdit);
			break;
		case 3:
			int empIdtoDelete = Integer.parseInt(userSearchData);
			empService.deleteEmployee(empIdtoDelete);
			break;
		case 4:
			empList = empService.getAllEmployees();
			break;
		case 5:
			empList = empService.searchEmployeeOnContactNumber(userSearchData);
			break;
		case 6:
			empList = empService.searchEmployeeOnName(userSearchData);
			break;
		case 7:
			empList = empService.searchEmployeeOnMaritalstatus(userSearchData);
			break;
		case 8:
			empList = empService.searchEmployeeOnGender(userSearchData);
			break;
		case 9:
			empService.getListOfAllDepartments();
			break;
		case 10:
			empList = empService.getEmpListForInputDepartment(userSearchData);
			break;
		case 11:
			departmentWiseEmpList = empService.getEmpListForAllDepartment();
			break;
		case 12:
			departmentWiseAvgSalaryList = empService.getAvgSalaryPerDepartment();
			break;
		}
		return empList;

	}

	private Employee copyUserData(UserCommandLineInputData userInputObj) {
		Employee empDataToEdit = new Employee();
		empDataToEdit.setEmployeeId(userInputObj.getEmployeeId());
		empDataToEdit.setFirstName(userInputObj.getFirstName());
		empDataToEdit.setMiddleName(userInputObj.getMiddleName());
		empDataToEdit.setLastName(userInputObj.getLastName());
		empDataToEdit.setGender(userInputObj.getGender());
		//empDataToEdit.setDOB(userInputObj.getFirstName());
		empDataToEdit.setEmailId(userInputObj.getEmailId());
		empDataToEdit.setMaritalStatus(userInputObj.getMaritalStatus());
		empDataToEdit.setDepartment(userInputObj.getDepartment());
		empDataToEdit.setSalary(userInputObj.getSalary());
		empDataToEdit.setMobileNumber(userInputObj.getMobileNumber());
        System.out.println("empDataToEdit - "+empDataToEdit);
		return empDataToEdit;
	}

}
