package api.employee.constants;

public class DBConnectionDetails {

	public static final String DB_URL="jdbc:mysql://localhost:3306/HRMSdb";
	public static final String DB_USERNAME="root";
	public static final String DB_PWD="^HappyMe@Feb28";
	public static final String DB_DRIVER="com.mysql.cj.jdbc.Driver";
	
	//QUERIES
	public static final String SQL_INSERT_EMPLOYEE="insert into employee(first_name, middle_name, last_name, dob, email_id, gender,marital_status,mobile_number,department,status,salary) values(?,?,?,?,?,?,?,?,?,?,?)";
	public static final String SQL_SELECT_EMPLOYEE="select emp_id, first_name, middle_name, last_name, dob, email_id, gender,marital_status,mobile_number,department,salary,status from employee";
	public static final String SQL_SELECT_EMPLOYEE_USING_EMPID="select emp_id, first_name, middle_name, last_name, dob, email_id, gender,marital_status,mobile_number,department,salary,status from employee where emp_id = ?";
	public static final String SQL_DELETE_EMPLOYEE="update employee set status = 'inactive' where emp_id = ?";
	public static final String SQL_EDIT_EMPLOYEE="update employee set  first_name = ?,middle_name =?,last_name = ?,dob = ?,email_id = ?,gender = ?,marital_status = ?,mobile_number = ?,department = ?,status=?,salary =? where emp_id = ?";

	
}
