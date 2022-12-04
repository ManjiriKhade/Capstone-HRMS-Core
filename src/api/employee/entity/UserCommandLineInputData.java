package api.employee.entity;

import java.sql.Date;
/**
 * 
 * @author manjiri
 *
 */
public class UserCommandLineInputData {

	private int userSelectedMenuOptionNumber;

	private long employeeId;

	private String generalInputUserString;

	private String firstName;

	private String middleName;

	private String lastName;

	private String gender;

	private Date dob;

	private String emailId;

	private String maritalStatus;

	private String mobileNumber;

	private String status;

	private int salary;

	private String department;

	public UserCommandLineInputData() {

	}

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public int getUserSelectedMenuOptionNumber() {
		return userSelectedMenuOptionNumber;
	}

	public void setUserSelectedMenuOptionNumber(int userSelectedMenuOptionNumber) {
		this.userSelectedMenuOptionNumber = userSelectedMenuOptionNumber;
	}

	public String getGeneralInputUserString() {
		return generalInputUserString;
	}

	public void setGeneralInputUserString(String generalInputUserString) {
		this.generalInputUserString = generalInputUserString;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

}
