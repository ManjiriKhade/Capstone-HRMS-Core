package api.employee.exception;
/**
 * 
 * @author manjiri
 *
 */
public class EmployeeException extends Exception {
	private String errorCode;

	public EmployeeException() {
		super();
	}

	public EmployeeException(String message) {
		super(message);
	}

	public EmployeeException(String message, Throwable cause, String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public EmployeeException(Throwable cause, String errorCode) {
		super(cause);
		this.errorCode = errorCode;
	}

	public EmployeeException(String message, String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
}
