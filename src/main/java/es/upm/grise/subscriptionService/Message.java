package es.upm.grise.subscriptionService;

public class Message {

	private long id;
	@SuppressWarnings("unused")
	private String message;
	
	public Message(long id, String message) {
		
		this.id = id;
		this.message = message;
		
	}

	public long getId() {

		return id;

	}
	
}
