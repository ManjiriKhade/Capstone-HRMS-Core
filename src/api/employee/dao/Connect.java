package api.employee.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


import api.employee.constants.DBConnectionDetails;

public class Connect {
	static Connection getConnection() throws SQLException, ClassNotFoundException{
		Class.forName(DBConnectionDetails.DB_DRIVER);
		return DriverManager.getConnection(DBConnectionDetails.DB_URL,DBConnectionDetails.DB_USERNAME,DBConnectionDetails.DB_PWD);
 	}

}
