package org.poorva.java.messenger.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorMessage {
	
	private String errorMessage;
	private int errorCode;
	private String Documentation; // gto give link to documentation of our API.
	
	
	public ErrorMessage()
	{
		
	}
	
	
	public ErrorMessage(String errorMessage, int errorCode, String documentation) {
		super();
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
		Documentation = documentation;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getDocumentation() {
		return Documentation;
	}
	public void setDocumentation(String documentation) {
		Documentation = documentation;
	}
	

}
