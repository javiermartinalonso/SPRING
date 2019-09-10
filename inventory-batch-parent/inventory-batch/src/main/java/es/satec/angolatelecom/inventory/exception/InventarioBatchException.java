package es.satec.angolatelecom.inventory.exception;

public class InventarioBatchException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2206593653485341318L;

	public InventarioBatchException() {
		super();
	}

	public InventarioBatchException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InventarioBatchException(String message, Throwable cause) {
		super(message, cause);
	}

	public InventarioBatchException(String message) {
		super(message);
	}

	public InventarioBatchException(Throwable cause) {
		super(cause);
	}
	
}
