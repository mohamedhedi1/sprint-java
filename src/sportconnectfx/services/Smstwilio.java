


package sportconnectfx.services;
import com.twilio.*;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class Smstwilio {
    private final String accountSID;
    private final String authToken;
    private final String recipientPhoneNumber;

    public Smstwilio(String accountSID, String authToken, String twilioPhoneNumber) {
        this.accountSID = accountSID;
        this.authToken = authToken;
        this.recipientPhoneNumber = twilioPhoneNumber;
    }

public void sendSMS(String recipientPhoneNumber, String message) throws IllegalArgumentException {
    Twilio.init(this.accountSID, this.authToken);
    try {
        PhoneNumber to = new PhoneNumber(recipientPhoneNumber);
        PhoneNumber from = new PhoneNumber(this.recipientPhoneNumber);
        Message smsMessage = Message.creator(to, from, message).create();
        System.out.println("SMS SID: " + smsMessage.getSid());
    } catch (NumberFormatException e) {
        throw new IllegalArgumentException("Invalid phone number format: " + recipientPhoneNumber, e);
    } catch (com.twilio.exception.ApiException e) {
        throw new IllegalArgumentException("Failed to send SMS: " + e.getMessage(), e);
    }
}

    
}
