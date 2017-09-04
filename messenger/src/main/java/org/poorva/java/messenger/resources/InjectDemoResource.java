package org.poorva.java.messenger.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;


@Path("/injectdemo")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.TEXT_PLAIN)
public class InjectDemoResource {
	
	@GET
	@Path("/annotations")
	public String getParamUsingAnnotations(@MatrixParam("param") String matrixParam,
										   @HeaderParam("CustomHeader") String headerParam)
	{
		return "Matrix Param :" +matrixParam + "Header Param: "+headerParam;
	}
	
	@GET
	@Path("/context")
	public String getParamUsingContext(@Context UriInfo uriInfo, @Context HttpHeaders headers)
	{
		String path = uriInfo.getAbsolutePath().toString();
		String header = headers.getCookies().toString();
		return "Path:" + path+ " Cookies:" + header;
	}

}
