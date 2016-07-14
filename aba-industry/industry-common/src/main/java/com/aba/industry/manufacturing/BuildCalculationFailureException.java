package com.aba.industry.manufacturing;

import java.io.IOException;

public class BuildCalculationFailureException extends Exception {
	private static final long serialVersionUID = 3918647148904477319L;
	
	public BuildCalculationFailureException( IOException e ) {
		super(e);
	}
	
	public BuildCalculationFailureException( String message ) {
		super(message);
	}
}
