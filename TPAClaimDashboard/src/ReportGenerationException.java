
/*
 * @(#)ReportGenerationException	1.0  10/09/05
 *
 * Copyright 2004-2005 MindTree Consulting Pvt. Ltd. All Rights Reserved.
 * 
 * This software is the proprietary information of MindTree Consulting Pvt. Ltd.  
 * Use is subject to license terms.
 * 
 */

/**
 * The class <code>ReportGenerationException</code> is a form of 
 * <code>Throwable</code> that indicates conditions that a reasonable 
 * application might want to catch.
 *
 * @author  Sajish Sadasivan
 * @version 1.00, 10/09/05
 * @since   JDK1.3
 */ 

public class ReportGenerationException extends Exception{
	
	/**
	 * Constructs an <code>ReportGenerationException</code> with no specified detail message. 
	 */
	ReportGenerationException(){
		super();
	}

	/**
	 * Constructs an <code>ReportGenerationException</code> with the specified detail message. 
	 *
	 * @param   s   the detail message.
	 */
	ReportGenerationException(String message){
		super(message);
	}
}