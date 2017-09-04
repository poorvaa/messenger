package org.poorva.java.messenger.resources;



import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.poorva.java.messenger.model.Message;
import org.poorva.java.messenger.resources.beans.MessageFilterBean;
import org.poorva.java.messenger.service.MessageService;

@Path("/messages")
public class MessageResource {
	
	MessageService messageService = new MessageService();
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessages(@BeanParam MessageFilterBean filterbean)
	{
		if(filterbean.getYr()>0)
		{
			return messageService.getAllMessagesForYear(filterbean.getYr());
		}
		
		if(filterbean.getStart()>=0 && filterbean.getSize()>0)
		{
			return messageService.getAllMessagesPaginated(filterbean.getStart(), filterbean.getSize());
		}
					
			return messageService.getAllMessages();
		
		
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addMessage(Message message, @Context UriInfo uriInfo)
	{
		Message newMessage = messageService.addMessage(message);
		String newId = String.valueOf(newMessage.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
		return Response.created(uri)
				.entity(newMessage)
				.build();
				
		
		
	}
	
	/*public Message addMessage(Message message)
	{
		return messageService.addMessage(message);
	}*/
	
	@PUT
	@Path("/{messageId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message updateMessage(@PathParam("messageId") long id, Message message)
	{
		message.setId(id);
		
		return messageService.updateMessage(message);
	}
	
	
	@DELETE
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteMessage(@PathParam("messageId") long id)
	{
		 messageService.removeMessage(id);
	}
	
	
	
	@GET
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Message getMessage(@PathParam("messageId") long id, @Context UriInfo uriInfo)
	{
		//return messageService.getMessage(id);
		
		Message message = messageService.getMessage(id);
		message.addLink(getUriForSelf(uriInfo, message), "self");
		message.addLink(getUriForProfile(uriInfo,message),"profile");
		message.addLink(getUriForComments(uriInfo,message),"comments");
		return message;
	}


	private String getUriForComments(UriInfo uriInfo, Message message) {
		
		String uri = uriInfo.getBaseUriBuilder()
				.path(MessageResource.class)
				.path(MessageResource.class,"getCommentResource")
				.path(CommentResource.class)
				.resolveTemplate("messageId",message.getId())
				.build()
				.toString();
			return uri;

	}


	private String getUriForProfile(UriInfo uriInfo, Message message) {
		// TODO Auto-generated method stub
		String uri = uriInfo.getBaseUriBuilder()
							.path(ProfileResource.class)
							.path(message.getAuthor())
							.build()
							.toString();
		return uri;
	}


	private String getUriForSelf(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder()
							.path(MessageResource.class)
							.path(Long.toString(message.getId()))
							.build()
							.toString();
		return uri;
	}
	
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource()
	{
		return new CommentResource();
	}

}
