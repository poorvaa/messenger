package org.poorva.java.messenger.exception;


import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.poorva.java.messenger.model.ErrorMessage;


public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(Throwable ex) {
		// TODO Auto-generated method stub
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(),500,"google.com");
		return Response.status(Status.INTERNAL_SERVER_ERROR)
						.entity(errorMessage)
						.build();
	}
}

