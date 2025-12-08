package services;

class SmsSender implements MessageSender{

    public void sendSms(String to, String message){
        System.out.println("Sending SMS Notification to " + to + ": " + message);
    }

    public void sendEmail(String to, String message) {
        System.out.println("LOG: SmsSender cannot send Email. Ignoring request for " + to);
    }
}
