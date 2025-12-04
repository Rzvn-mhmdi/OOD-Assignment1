package services;

class EmailSender implements MessageSender{
    public void sendNotification(String to, String message){
        System.out.println("Sending email to " + to + ": " + message);
    }
}
