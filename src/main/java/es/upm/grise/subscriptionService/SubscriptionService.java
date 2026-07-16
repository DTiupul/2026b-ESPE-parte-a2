package es.upm.grise.subscriptionService;

import java.util.ArrayList;
import java.util.Collection;

public class SubscriptionService {

	private Collection <User> subscribers;
	private EmailService emailService;
	
	public SubscriptionService(EmailService emailService) {
        this.emailService = emailService;
        this.subscribers = new ArrayList<>();
    }

    public void addSubscriber(User user)
            throws NullUserException, ExistingUserException, UserDoesNotHaveEmailException {

        if (user == null) {
            throw new NullUserException();
        }

        if (subscribers.contains(user)) {
            throw new ExistingUserException();
        }

        if (user.getDeliveryType() != Delivery.LOCAL && user.getEmail() == null) {
            throw new UserDoesNotHaveEmailException();
        }

        subscribers.add(user);
    }

    public void removeSubscriber(User user)
            throws NullUserException, NonExistingUserException {

        if (user == null) {
            throw new NullUserException();
        }

        if (!subscribers.contains(user)) {
            throw new NonExistingUserException();
        }

        subscribers.remove(user);
    }

    public int sendMessage(Message message) {

        int discarded = 0;

        for (User user : subscribers) {

            switch (user.getDeliveryType()) {

                case EMAIL:
                    emailService.sendMessage(user, message);
                    break;

                case LOCAL:
                    user.saveMessage(message);
                    break;

                case DO_NOT_DELIVER:
                    user.saveMessage(
                        new Message(message.getId(), "Ha perdido ud. un mensaje"));
                    discarded++;
                    break;
            }
        }

        return discarded;
    }
	
}
