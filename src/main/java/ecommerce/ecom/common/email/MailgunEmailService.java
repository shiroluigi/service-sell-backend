package ecommerce.ecom.common.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;

import com.mailgun.api.v3.MailgunMessagesApi;
import com.mailgun.client.MailgunClient;
import com.mailgun.model.message.Message;
import com.mailgun.model.message.MessageResponse;

import ecommerce.ecom.common.EmailService;

@Service
@ConditionalOnProperty(
    value = "email.provider",
    havingValue = "mailgun"
)
public class MailgunEmailService implements EmailService {

    @Value("${mailgun.api.key}")
    private String apiKey;

    @Value("${mailgun.domain}")
    private String domain;

    @Value("${mailgun.from}")
    private String from;

    private MailgunMessagesApi messagesApi;

    @PostConstruct
    public void init() {
        messagesApi = MailgunClient
                .config(apiKey)
                .createApi(MailgunMessagesApi.class);
    }

    @Override
    public void sendEmail(String to, String subject, String text) {
        try {
            Message message = Message.builder()
                    .from(from)
                    .to(to)
                    .subject(subject)
                    .text(text)
                    .build();

            MessageResponse response = messagesApi.sendMessage(domain, message);

            System.out.println("Mailgun Response: " + response.getMessage());

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Mailgun ERROR → " + e.getMessage());
        }
    }
}
