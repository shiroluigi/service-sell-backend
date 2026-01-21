package ecommerce.ecom.common;

import org.springframework.beans.factory.annotation.Value;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.mail.SimpleMailMessage;
// import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

@Service
public class EmailService {

    // @Autowired
    // private JavaMailSender javaMailSender;

    // public void sendEmail(String to, String subject, String body){
    // try {
    // SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
    // simpleMailMessage.setTo(to);
    // simpleMailMessage.setSubject(subject);
    // simpleMailMessage.setText(body);

    // javaMailSender.send(simpleMailMessage);
    // } catch (Exception e) {
    // System.out.println("Exception occured! " + e.getMessage());
    // }
    // }

    private final SendGrid sendGrid;
    private final String fromEmail;

    public EmailService(
            @Value("${sendgrid.api.key}") String apiKey,
            @Value("${sendgrid.verified.email}") String fromEmail) {
        this.sendGrid = new SendGrid(apiKey);
        this.fromEmail = fromEmail;
    }

    public void sendEmail(String to, String subject, String body) {
        try {
            Email from = new Email(fromEmail);
            Email receiver = new Email(to);

            Content content = new Content("text/plain", body);
            // For HTML emails: new Content("text/html", body)

            Mail mail = new Mail(from, subject, receiver, content);

            Request request = new Request();
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            sendGrid.api(request);

            System.out.println("Email sent successfully!");
        } catch (Exception e) {
            System.out.println("Exception occurred! " + e.getMessage());
        }
    }
}