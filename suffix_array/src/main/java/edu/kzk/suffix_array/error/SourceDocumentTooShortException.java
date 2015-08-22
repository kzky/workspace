package edu.kzk.suffix_array.error;

public class SourceDocumentTooShortException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4234001137938257205L;

	public SourceDocumentTooShortException() {
		super();
	}

	public SourceDocumentTooShortException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SourceDocumentTooShortException(String message, Throwable cause) {
		super(message, cause);
	}

	public SourceDocumentTooShortException(String message) {
		super(message);
	}

	public SourceDocumentTooShortException(Throwable cause) {
		super(cause);
	}
	
}
