package es.upm.grise.subscriptionService;

import java.util.ArrayList;
import java.util.Collection;

public class User {

	private Delivery deliveryType;
	private String email;
	private Collection <Message> messages = new ArrayList<Message>();
	
	public User(Delivery deliveryType, String email) {
		
		this.deliveryType = deliveryType;
		this.email = email;
		
	}

	String getEmail() {
		
		return email;
		
	}

	public Delivery getDeliveryType() {
		
		return deliveryType;
		
	}
	

	public void saveMessage(Message message) {
		
		messages.add(message);
		
	}

	public boolean messageExists(Message message) {
		
		return messages.contains(message);
		
	}


	public void deleteMessage(Message message) {
		
		messages.remove(message);
		
	}

	public void cancelMessageById(Message message) {
		
		messages.removeIf(m -> m.getId() == message.getId());
		
	}	
}
