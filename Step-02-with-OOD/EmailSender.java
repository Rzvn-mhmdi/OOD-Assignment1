package services;

public class EmailSender implements MessageSender{
    @Override
    public void sendNotification(String to, String message){
        System.out.println("Sending email to " + to + ": " + message);
    }
}
