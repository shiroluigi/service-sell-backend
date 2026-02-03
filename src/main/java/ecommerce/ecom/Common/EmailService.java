package ecommerce.ecom.Common;

@FunctionalInterface
public interface EmailService {
    public void sendEmail(String to,String from,String body);
}