package fr.donjon.zpoubelle;

/**
 * Just a way to have our own Exceptions available
 * @author Patrick
 *
 */
public class CustomException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5611076786810114586L;

	public CustomException() {
		// TODO Auto-generated constructor stub
	}

	public CustomException(String arg0) {
		super(arg0);
	}

	public CustomException(String message, Throwable cause) {
		super(message, cause);
	}

	public CustomException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public CustomException(Throwable cause) {
		super(cause);
	}

}