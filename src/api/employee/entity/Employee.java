package api.employee.entity;

import java.sql.Date;

public class Employee {

	private long employeeId;
	
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
	
	public Employee() {
		
	}	

	public Employee(String firstName, String middleName, String lastName, String gender, Date dob, String emailId,
			String maritalStatus, String mobileNumber, String status, int salary, String department) {
		super();
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.gender = gender;
		this.dob = dob;
		this.emailId = emailId;
		this.maritalStatus = maritalStatus;
		this.mobileNumber = mobileNumber;
		this.status = status;
		this.salary = salary;
		this.department = department;
	}



	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
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

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", firstName=" + firstName + ", middleName=" + middleName
				+ ", lastName=" + lastName + ", gender=" + gender + ", dob=" + dob + ", emailId=" + emailId
				+ ", maritalStatus=" + maritalStatus + ", mobileNumber=" + mobileNumber + ", status=" + status
				+ ", salary=" + salary + ", department=" + department + "]";
	}



	
	
}
