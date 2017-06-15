package ua.nure.gnuchykh.exception;

/**
 * An exception that provides information on a database access error.
 * @author qny4ix
 */
public class DBException extends Exception {

	private static final long serialVersionUID = -3550446897536410392L;

	public DBException() {
		super();
	}

	public DBException(String message, Throwable cause) {
		super(message, cause);
	}

	public DBException(String message) {
            super(message);
    }

}
