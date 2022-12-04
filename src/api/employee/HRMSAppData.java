package api.employee;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import api.employee.controller.EmployeeController;
import api.employee.dao.EmployeeDaoImpl;
import api.employee.entity.Employee;
import api.employee.entity.UserCommandLineInputData;
import api.employee.exception.EmployeeException;

/**
 * @author manjiri
 *
 */

public class HRMSAppData {

	public static void main(String[] args) {
		System.out.println("********Welcome to Capstone HRMS Core********");

		Scanner scanner = null;
		Employee emp = null;
		EmployeeDaoImpl emploeeDaoImpl = new EmployeeDaoImpl();
		UserCommandLineInputData userDataObj = null;

		// load EmployeeDB with default entries
		loadDefaultEmployeeEntries(emploeeDaoImpl);

		EmployeeController empController = new EmployeeController();
		try {
			scanner = new Scanner(System.in);

			int userInput;
			String userSearchData = null;

			/**
			 * Main-menu for Capstone HRMS
			 */
			do {
				System.out.println(
						"\nWelcome to Capstone HRMS application. Please select appropriate Menu from given choices"
								+ "\n1. Create Employee" + "\n2. Edit Employee " + "\n3. Delete Employee"
								+ "\n4. Display list of all Employees" + "\n5. Search Employee based on mobile number"
								+ "\n6. Search Employee based on Name "
								+ "\n7. Search Employees based on marital status"
								+ "\n8. Search Employees based on gender" + "\n9. Print names of all Departments"
								+ "\n10.Search Employees based on provided Department name"
								+ "\n11.Display employees for every Department"
								+ "\n12.Display Average Salary for each department" + "\n0. --- Quit ---");
				userInput = 0;
				System.out.println("enter your menu choice");
				userInput = scanner.nextInt();
				System.out.println("Your choice is -" + userInput);

				switch (userInput) {
				case 1:// Add
					emp = showMenuToAddEmployee(userInput);
					empController.getDataPerUserAction(userInput, emp, userSearchData, userDataObj);
					break;
				case 2:// Edit
					empController.getDataPerUserAction(4, emp, userSearchData, userDataObj);
					userDataObj = showSubMenuForEditEmployee(userInput);
					empController.getDataPerUserAction(userInput, emp, userSearchData, userDataObj);
					break;
				case 3: // delete
					empController.getDataPerUserAction(4, emp, userSearchData, userDataObj);
					userSearchData = showSubMenuForSearchEmployee(userInput);
					empController.getDataPerUserAction(userInput, emp, userSearchData, userDataObj);
					break;
				case 4:// Display All
					empController.getDataPerUserAction(userInput, emp, userSearchData, userDataObj);
					break;
				case 5:// Search Employee based on mobile number
					userSearchData = showSubMenuForSearchEmployee(userInput);
					empController.getDataPerUserAction(userInput, emp, userSearchData, userDataObj);
					break;
				case 6: // Search Employee based on Name
					userSearchData = showSubMenuForSearchEmployee(userInput);
					empController.getDataPerUserAction(userInput, emp, userSearchData, userDataObj);
					break;
				case 7:// Search Employees based on marital status
					userSearchData = showSubMenuForSearchEmployee(userInput);
					empController.getDataPerUserAction(userInput, emp, userSearchData, userDataObj);
					break;
				case 8:// Search Employees based on gender
					userSearchData = showSubMenuForSearchEmployee(userInput);
					empController.getDataPerUserAction(userInput, emp, userSearchData, userDataObj);
					break;
				case 9: // Print names of all Departments
					empController.getDataPerUserAction(userInput, emp, userSearchData, userDataObj);
					break;
				case 10:// Search Employees based on Department name provided
					userSearchData = showSubMenuForSearchEmployee(userInput);
					empController.getDataPerUserAction(userInput, emp, userSearchData, userDataObj);
					break;
				case 11:// Display Employees for all Departments
					empController.getDataPerUserAction(userInput, emp, userSearchData, userDataObj);
					break;
				case 12:// Display Average Salary for each department
					empController.getDataPerUserAction(userInput, emp, userSearchData, userDataObj);
					break;
				case 0:
					System.out.println("Bye...! Have a good day!!");
					break;
				default:
					System.out.println("This is not a valid Menu Option! Please Select Another");
					break;
				}
			} while (userInput != 0);

		} catch (Exception e) {
			System.out.println("I am facing some unknown issue");
			e.printStackTrace();
		} finally {
			scanner.close();
		}

	}

	/**
	 * displays sub-menu for editing employee record.
	 * 
	 * @param userInput
	 * @return
	 */
	private static UserCommandLineInputData showSubMenuForEditEmployee(int userInput) {
		Scanner subEditMenuScanner, subEditDataScanner = null;
		String userdata = null;
		long employeeIDToEdit = 0;

		subEditMenuScanner = new Scanner(System.in);
		subEditDataScanner = new Scanner(System.in);
		UserCommandLineInputData usrInputData = new UserCommandLineInputData();

		System.out.println("Enter the Employee ID to edit");
		employeeIDToEdit = subEditMenuScanner.nextLong();
		usrInputData.setEmployeeId(employeeIDToEdit);

		do {
			System.out.println("select the field/s which you want to edit " + "-\n1.Employee First Name"
					+ "\n2.Employee Middle Name. \n3. Employee Last Name \n4.Gender"
					+ "\n5.DOB \n6.Email Id \n7.Marital Status\n8.Mobile Number"
					+ " \n9.Department\n10. Salary\n0.Quit");

			userInput = 0;
			System.out.println("enter your menu choice");
			userInput = subEditMenuScanner.nextInt();

			switch (userInput) {
			case 1:// First Name
				System.out.println("Enter First name you want to change to   -");
				userdata = subEditDataScanner.nextLine();
				usrInputData.setFirstName(userdata);
				break;
			case 2:// Edit
				System.out.println("Enter Middle name you want to change to   -");
				userdata = subEditDataScanner.nextLine();
				usrInputData.setMiddleName(userdata);
				break;
			case 3: // delete
				System.out.println("Enter Last name you want to change to   -");
				userdata = subEditDataScanner.nextLine();
				usrInputData.setLastName(userdata);
				break;
			case 4:// Display All
				System.out.println("Enter Gender you want to change to -");
				userdata = subEditDataScanner.nextLine();
				usrInputData.setGender(userdata);
				break;

			case 5:
				System.out.println("Enter DOB you want to change to -(yyyy-mm-dd)");
				userdata = subEditDataScanner.nextLine();
				usrInputData.setDob(Date.valueOf(userdata));
				break;

			case 6:
				System.out.println("Enter Email ID you want to change to -");
				userdata = subEditDataScanner.nextLine();
				usrInputData.setEmailId(userdata);
				break;
			case 7:
				System.out.println("Enter Marital status you want to change to -");
				userdata = subEditDataScanner.nextLine();
				usrInputData.setMaritalStatus(userdata);
				break;
			case 8:
				System.out.println("Enter Mobile Number  you want to change to - ");
				userdata = subEditDataScanner.nextLine();
				usrInputData.setMobileNumber(userdata);
				break;
			case 9:
				System.out.println("Enter Department you want to change to- ");
				userdata = subEditDataScanner.nextLine();
				usrInputData.setDepartment(userdata);
				break;
			case 10:
				System.out.println("Enter Salary you want to change to -");
				userdata = subEditDataScanner.nextLine();
				usrInputData.setSalary(Integer.parseInt(userdata));
				break;
			case 0:
				System.out.println("Thanks for the input. Employee information is getting updated...");
				break;
			default:
				System.out.println("This is not a valid Menu Option! Please Select Another");
				break;
			}
		} while (userInput != 0);
		return usrInputData;

	}

	/**
	 * displays sub-menu for user selected Main-Menu Actions and reads user sub-menu
	 * data
	 * 
	 * @param userInput - selected main-menu number
	 * @return - user input data
	 */
	private static String showSubMenuForSearchEmployee(int userInput) {
		Scanner submenuScanner = null;
		String userdata = null;
		submenuScanner = new Scanner(System.in);
		switch (userInput) {
		case 2:
			System.out.println("Enter the Employee ID to edit");
			userdata = submenuScanner.nextLine();
			break;
		case 3:
			System.out.println("Enter the Employee ID to delete");
			userdata = submenuScanner.nextLine();
			break;
		case 5:
			System.out.println("Enter the Mobile number to search");
			userdata = submenuScanner.nextLine();
			System.out.println("your entered mobile number is - " + userdata);

			break;
		case 6:
			System.out.println("Enter the name you want to search");
			userdata = submenuScanner.nextLine();
			break;
		case 7:
			System.out.println("Enter the marital status you want to search(married/unmarried)");
			userdata = submenuScanner.nextLine();
			break;
		case 8:
			System.out.println("Enter the gender you want to search(male/female)");
			userdata = submenuScanner.nextLine();
			break;
		case 10:
			System.out.println("Enter the department name you want to search");
			userdata = submenuScanner.nextLine();
			break;

		}
		return userdata;
	}

	/**
	 * loads Employee table with default entries
	 * 
	 * @param emploeeDaoImpl
	 * 
	 */
	private static void loadDefaultEmployeeEntries(EmployeeDaoImpl emploeeDaoImpl) {
		// set employee table with some default Employee entries

		List<Employee> employees = new ArrayList<Employee>();

		employees.add(new Employee("Manjiri", "Yogesh", "Khade", "Female", Date.valueOf("1988-03-08"),
				"khade.manjiri@gmail.com", "married", "9623025121", "Active", 90000, "Product Development"));
		employees.add(new Employee("Yogesh", "Ramchandra", "Khade", "male", Date.valueOf("1983-05-14"),
				"khade.yogesh@gmail.com", "married", "6923205112", "Active", 100000, "HR"));
		employees.add(new Employee("Tanmay", "Yogesh", "Khade", "male", Date.valueOf("2021-01-11"),
				"khade.tanmay@gmail.com", "unmarried", "1234567891", "Active", 85000, "Sales and Marketing"));
		employees.add(new Employee("Manasi", "Parimal", "Kulkarni", "Female", Date.valueOf("2014-01-11"),
				"kulkarni.manasi@gmail.com", "married", "8978654324", "Active", 50000, "Infrastructure"));
		employees.add(new Employee("Jayant", "Ramesh", "Kulkarni", "male", Date.valueOf("2000-10-21"),
				"kulkarni.jayant@gmail.com", "married", "8067321876", "Active", 40000, "Account and Finance"));

		for (Employee employee : employees) {
			try {
				emploeeDaoImpl.registerEmployee(employee);
			} catch (EmployeeException e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * displays sub menu to fetch employee entry
	 * 
	 * @param userInput
	 * @return Employee
	 */
	public static Employee showMenuToAddEmployee(int userInput) {
		Scanner addEmpMenuScanner = null;
		addEmpMenuScanner = new Scanner(System.in);

		Employee employee = new Employee();

		System.out.println("Enter the first name");
		employee.setFirstName(addEmpMenuScanner.nextLine());
		System.out.println("Enter the middle name");
		employee.setMiddleName(addEmpMenuScanner.nextLine());
		System.out.println("Enter the last name");
		employee.setLastName(addEmpMenuScanner.nextLine());
		System.out.println("Enter the email ID");
		employee.setEmailId(addEmpMenuScanner.nextLine());
		System.out.println("Enter the Date Of Birth(YYYY-MM-DD)");
		employee.setDob(Date.valueOf(addEmpMenuScanner.nextLine()));
		System.out.println("Enter the Mobile number(10 Digits)");
		employee.setMobileNumber(addEmpMenuScanner.nextLine());
		System.out.println("Enter the Gender(male/female)");
		employee.setGender(addEmpMenuScanner.nextLine());
		System.out.println("Enter the marital status (married/unmarried) ");
		employee.setMaritalStatus(addEmpMenuScanner.nextLine());
		System.out.println("Enter the Department ");
		employee.setDepartment(addEmpMenuScanner.nextLine());
		System.out.println("Enter the Salary ");
		employee.setSalary(Integer.parseInt(addEmpMenuScanner.nextLine()));
		employee.setStatus("Active");

		return employee;
	}

}
