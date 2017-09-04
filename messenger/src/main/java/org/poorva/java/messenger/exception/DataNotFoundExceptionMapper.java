package org.poorva.java.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.poorva.java.messenger.model.ErrorMessage;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

	@Override
	public Response toResponse(DataNotFoundException ex) {
		// TODO Auto-generated method stub
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(),404,"google.com");
		return Response.status(Status.NOT_FOUND)
						.entity(errorMessage)
						.build();
	}

}
