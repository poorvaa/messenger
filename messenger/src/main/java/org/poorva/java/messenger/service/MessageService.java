package org.poorva.java.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.poorva.java.messenger.database.DatabaseClass;
import org.poorva.java.messenger.exception.DataNotFoundException;
import org.poorva.java.messenger.model.Message;

public class MessageService {
	
	private Map<Long, Message> messages = DatabaseClass.getMessages();
	
	public MessageService()
	{
		messages.put(1L,new Message(1,"Hello","poorva"));
		messages.put(2L,new Message(2,"World","gupta"));
	}
	
	public List<Message> getAllMessages()
	{
		/*Message m1 = new Message(1L,"Hello","Poorva");
		Message m2 = new Message(2L,"World","Gupta");
		
		List<Message> list = new ArrayList<>();
		list.add(m1);
		list.add(m2);
		return list; */
		
		return new ArrayList<Message>(messages.values());
		
	}
	
	public Message getMessage(long id)
	{
		//return messages.get(id);
		Message message = messages.get(id);
		if(message == null)
		{
			throw new DataNotFoundException("Message with id "+id+" not found");
		}
		
		return message;
			
	}
	
	public Message addMessage(Message message)
	{
		message.setId(messages.size()+1);
		messages.put(message.getId(),message);
		return message;
	}
	
	public Message updateMessage(Message message)
	{
		if(message.getId()<=0)
			return null;
		
		messages.put(message.getId(),message);
		return message;
		
	}
	
	public Message removeMessage(long id)
	{
		return messages.remove(id);
	}
	
	public List<Message> getAllMessagesForYear(int year)
	{
		List<Message> messageForYear = new ArrayList<Message>();
		Calendar cal = Calendar.getInstance();
		for(Message message: messages.values())
		{
			//set the time of calendar to the value
			cal.setTime(message.getCreated());
			if(cal.get(Calendar.YEAR)== year)
			{
				messageForYear.add(message);
			}
		}
		
		return messageForYear;
	}
	//gets all msg from start to given size 
	public List<Message> getAllMessagesPaginated(int start,int size)
	{
		ArrayList<Message> list = new ArrayList<Message>(messages.values());
		if(start+size>list.size())
		{
			return new ArrayList<Message>();
		}
		return list.subList(start,start+size);
	}
	
	

}
