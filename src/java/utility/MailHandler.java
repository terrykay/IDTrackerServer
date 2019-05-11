package utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailHandler {

    private static MailHandler mailHandler = null;
    private List<String> recipientsEmail;
    private String fromDNR = "donotreply@tezk.co.uk";
    private String from = fromDNR;
    private final String host = "mail.tezk.co.uk";
    private final Properties properties;
    private final Session session;
    private String subject;
    private String emailBody;
    private String errorMessage = "";

    public String getErrorMessage() {
        return errorMessage;
    }

    protected MailHandler() {
        // Get the mail Session object
        properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        session = Session.getDefaultInstance(properties);
        recipientsEmail = new ArrayList();
    }

    public static MailHandler getInstance() {
        if (mailHandler == null) {
            mailHandler = new MailHandler();
        }
        return mailHandler;
    }

    public void setEmailBody(String body) {
        emailBody = body;
    }

    public void setSubject(String aSubject) {
        subject = aSubject;
    }

    public void setFrom(String newFrom) {
        from = newFrom;
    }

    public void setRecipients(List<String> recipients) {
        recipientsEmail = recipients;
    }

    public void setRecipient(String anEmail) {
        recipientsEmail.clear();
        recipientsEmail.add(anEmail);
    }

    public boolean send() {
        errorMessage = "";
        boolean success = true;
        try {
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(from));
            message.setSubject(subject);
            message.setText(emailBody);
            for (String eachRecipient : recipientsEmail) {
                if (isEmailValid(eachRecipient))
                    message.addRecipient(Message.RecipientType.BCC, new InternetAddress(eachRecipient));
                else
                    errorMessage += "Invalid email address : "+eachRecipient+" - " + validationMessage + "\n";
            }
            Transport.send(message);
        } catch (MessagingException e) {
            errorMessage = e.toString();
            System.err.println(e);

            success = false;
        }

        return success;
    }
    
    public String validationMessage;
    public boolean isEmailValid(String eMailAddress) {
        boolean valid = false;
        try {
            InternetAddress address = new InternetAddress(eMailAddress);
            address.validate();
            valid = true;
        } catch (AddressException ex) {
            valid = false;
            validationMessage = ex.getMessage();
        }
        return valid;
    }
}
