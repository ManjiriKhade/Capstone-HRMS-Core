package api.employee.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.protocol.Resultset;

import java.sql.ResultSet;

import api.employee.constants.*;
import api.employee.exception.*;
import api.employee.entity.*;

public class EmployeeDaoImpl implements EmployeeDao {

	@Override
	public int registerEmployee(Employee emp) throws EmployeeException {
		int result = 0;

		try {
			Connection conn = Connect.getConnection();
			// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = conn.prepareStatement(DBConnectionDetails.SQL_INSERT_EMPLOYEE);

			preparedStatement.setString(1, emp.getFirstName());
			preparedStatement.setString(2, emp.getMiddleName());
			preparedStatement.setString(3, emp.getLastName());
			preparedStatement.setDate(4, emp.getDob());
			preparedStatement.setString(5, emp.getEmailId());
			preparedStatement.setString(6, emp.getGender());
			preparedStatement.setString(7, emp.getMaritalStatus());
			preparedStatement.setString(8, emp.getMobileNumber());
			preparedStatement.setString(9, emp.getDepartment());
			preparedStatement.setString(10, emp.getStatus());
			preparedStatement.setInt(11,emp.getSalary() );
			// Step 3: Execute the query or update query
			result = preparedStatement.executeUpdate();

			if (result == 1) {
				System.out.println("Employee with email id - " + emp.getEmailId() + " succesfully inserted.");
			}

		} catch (ClassNotFoundException e) {
			throw new EmployeeException(e, ErrorCode.LOAD_DRIVER_ERROR);
		} catch (SQLException e) {
			throw new EmployeeException(e, ErrorCode.SQL_INSERT_ERROR);
		} catch (Exception ce) {
			throw new EmployeeException(ce, ErrorCode.SQL_UNKNOWN_ERROR);
		}
		return result;
	}



	@Override
	public List<Employee> displayAllEmployees() throws EmployeeException {
		List<Employee> employees = null;
		Connection conn = null;
		ResultSet empResultSet;

		try {
			 conn = Connect.getConnection();
			// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = conn.prepareStatement(DBConnectionDetails.SQL_SELECT_EMPLOYEE);
			// Step 3: Execute the query or update query
			empResultSet = preparedStatement.executeQuery();

			employees = readDBEmployeeRecord(empResultSet);

		} catch (ClassNotFoundException e) {
			throw new EmployeeException(e, ErrorCode.LOAD_DRIVER_ERROR);
		} catch (SQLException e) {
			throw new EmployeeException(e, ErrorCode.SQL_INSERT_ERROR);
		} catch (Exception ce) {
			throw new EmployeeException(ce, ErrorCode.SQL_UNKNOWN_ERROR);
		}
		return employees;	

	}



	/**
	 * @param employees
	 * @param empResultSet
	 * @throws SQLException
	 */
	private List<Employee> readDBEmployeeRecord( ResultSet empResultSet) throws SQLException {
		List<Employee> employees = null;
		if (empResultSet.next() == false) {
			System.out.println("No Record found for given Employee ID - ");
		} else {
			employees = new ArrayList<Employee>();
			do {
				Employee emp = new Employee();
				emp.setEmployeeId(empResultSet.getLong("emp_id"));
				emp.setFirstName(empResultSet.getString("first_name"));
				emp.setMiddleName(empResultSet.getString("middle_name"));
				emp.setLastName(empResultSet.getString("last_name"));
				emp.setDob(empResultSet.getDate("dob"));
				emp.setGender(empResultSet.getString("gender"));
				emp.setMaritalStatus(empResultSet.getString("marital_status"));
				emp.setMobileNumber(empResultSet.getString("mobile_number"));
				emp.setEmailId(empResultSet.getString("email_id"));
				emp.setDepartment(empResultSet.getString("department"));
				emp.setStatus(empResultSet.getString("status"));
				emp.setSalary(empResultSet.getInt("salary"));
				employees.add(emp);
			} while (empResultSet.next());
		}
		return employees;
	}

	@Override
	public int deleteEmployee(int empID) throws EmployeeException {
		int result;

		try {
			Connection conn = Connect.getConnection();
			// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = conn.prepareStatement(DBConnectionDetails.SQL_DELETE_EMPLOYEE);

			preparedStatement.setInt(1, empID);
			result = preparedStatement.executeUpdate();

			if (result == 1) {
				System.out.println("Employee with employee id - " + empID + " succesfully deleted.");
			} else {
				System.out.println("Employee with employee id - " + empID + " could not be deleted.");

			}

		} catch (ClassNotFoundException e) {
			throw new EmployeeException(e, ErrorCode.LOAD_DRIVER_ERROR);
		} catch (SQLException e) {
			throw new EmployeeException(e, ErrorCode.SQL_INSERT_ERROR);
		} catch (Exception ce) {
			throw new EmployeeException(ce, ErrorCode.SQL_UNKNOWN_ERROR);
		}
		return result;
	}



	@Override
	public void editEmployee(Employee emp) throws EmployeeException {
		List<Employee> employees = new ArrayList<Employee>();
		Connection conn = null;
		ResultSet empResultSet;
		int recordEdited = 0;

		try {
			conn = Connect.getConnection();
			// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = conn
					.prepareStatement(DBConnectionDetails.SQL_SELECT_EMPLOYEE_USING_EMPID);
			preparedStatement.setLong(1, emp.getEmployeeId());
			// Step 3: Execute the query or update query
			empResultSet = preparedStatement.executeQuery();
			employees = readDBEmployeeRecord(empResultSet);
			if (employees != null) {
				recordEdited = editEmployeeUsingUserInput(employees.get(0), emp);

				if (recordEdited == 1) {
					System.out.println("Employee with employee id - " + emp.getEmployeeId() + " succesfully updated.");
				}
			}
			

		} catch (ClassNotFoundException e) {
			throw new EmployeeException(e, ErrorCode.LOAD_DRIVER_ERROR);
		} catch (SQLException e) {
			throw new EmployeeException(e, ErrorCode.SQL_INSERT_ERROR);
		} catch (Exception ce) {
			throw new EmployeeException(ce, ErrorCode.SQL_UNKNOWN_ERROR);
		}

	}


	private int editEmployeeUsingUserInput(Employee dbEmployee, Employee userInputEmployee) throws EmployeeException {
		Connection conn = null;
		int result = 0;


		try {
			conn = Connect.getConnection();
			// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = conn
					.prepareStatement(DBConnectionDetails.SQL_EDIT_EMPLOYEE);
	//		preparedStatement.setLong(1, emp.getEmployeeId());
			// Step 3: Execute the query or update query
			preparedStatement.setString(1, userInputEmployee.getFirstName()!= null ? userInputEmployee.getFirstName():dbEmployee.getFirstName());
			preparedStatement.setString(2, userInputEmployee.getMiddleName()!= null ?userInputEmployee.getMiddleName():dbEmployee.getMiddleName());
			preparedStatement.setString(3, userInputEmployee.getLastName()!= null?userInputEmployee.getLastName():dbEmployee.getLastName());
			preparedStatement.setDate(4, userInputEmployee.getDob()!=null?userInputEmployee.getDob():dbEmployee.getDob());
			preparedStatement.setString(5, userInputEmployee.getEmailId()!=null?userInputEmployee.getEmailId():dbEmployee.getEmailId());
			preparedStatement.setString(6, userInputEmployee.getGender()!=null?userInputEmployee.getGender():dbEmployee.getGender());
			preparedStatement.setString(7, userInputEmployee.getMaritalStatus()!=null?userInputEmployee.getMaritalStatus():dbEmployee.getMaritalStatus());
			preparedStatement.setString(8, userInputEmployee.getMobileNumber()!=null?userInputEmployee.getMobileNumber():dbEmployee.getMobileNumber());
			preparedStatement.setString(9, userInputEmployee.getDepartment()!=null?userInputEmployee.getDepartment():dbEmployee.getDepartment());
			preparedStatement.setString(10, dbEmployee.getStatus());
			preparedStatement.setInt(11,userInputEmployee.getSalary()!=0 ?userInputEmployee.getSalary() :dbEmployee.getSalary());
			preparedStatement.setLong(12, dbEmployee.getEmployeeId());
			// Step 3: Execute the query or update query
			result = preparedStatement.executeUpdate();

		

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new EmployeeException(e, ErrorCode.LOAD_DRIVER_ERROR);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new EmployeeException(e, ErrorCode.SQL_INSERT_ERROR);
		} catch (Exception ce) {
			ce.printStackTrace();
			throw new EmployeeException(ce, ErrorCode.SQL_UNKNOWN_ERROR);
		}
		return result;

	}
	
}
