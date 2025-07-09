
public class NotificationService {
    private Messenger messenger;

    public NotificationService(Messenger messenger) {
        this.messenger = messenger;
    }

    public void notifyUser() {
        messenger.sendMessage("irfaan@example.com", "Welcome to our service!");
    }
}
