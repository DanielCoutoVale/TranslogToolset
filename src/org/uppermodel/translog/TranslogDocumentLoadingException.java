package org.uppermodel.translog;

public class TranslogDocumentLoadingException extends Exception {

	/**
	 * Generated serial version UID
	 */
	private static final long serialVersionUID = -6139735124899975817L;

	/**
	 * Constructor
	 * 
	 * @param e the exception triggering this one.
	 */
	public TranslogDocumentLoadingException(Exception e) {
		super(e);
	}

}
