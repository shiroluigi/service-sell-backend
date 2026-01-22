package ecommerce.ecom.common.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import ecommerce.ecom.common.EmailService;

@Service
@ConditionalOnProperty(
    value = "email.provider",
    havingValue = "sendgrid"
)
public class SendGridService implements EmailService {
    
    private final SendGrid sendGrid;
    private final String fromEmail;

    public SendGridService(
            @Value("${sendgrid.api.key}") String apiKey,
            @Value("${sendgrid.verified.email}") String fromEmail) {
        this.sendGrid = new SendGrid(apiKey);
        this.fromEmail = fromEmail;
    }

    @Override
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
