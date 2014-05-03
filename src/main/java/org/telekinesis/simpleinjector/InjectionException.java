package org.telekinesis.simpleinjector;

public class InjectionException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4140917539302967162L;

	public InjectionException(String message, Throwable cause){
		super(message, cause);
	}
	
	public InjectionException(String message){
		super(message);
	}
	
}
