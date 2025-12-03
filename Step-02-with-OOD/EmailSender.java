package services;

public class EmailSender implements MessageSender{
    public void sendMessage(String to, String message){
        System.out.println("Sending email to " + to + ": " + message);
    }
}
