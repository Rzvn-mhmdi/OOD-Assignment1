package services;

class EmailSender implements MessageSender{
    public void sendEmail(String to, String message){
        System.out.println("Sending email to " + to + ": " + message);
    }

    public void sendSms(String to, String message){
        System.out.println("LOG: EmailSender cannot send SMS. Ignoring request for " + to);
    }
}
