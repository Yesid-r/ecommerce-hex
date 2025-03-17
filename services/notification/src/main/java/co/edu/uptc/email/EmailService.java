package co.edu.uptc.email;


import co.edu.uptc.kafka.order.Product;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static co.edu.uptc.email.EmailTemplate.ORDER_CONFIRMATION;
import static co.edu.uptc.email.EmailTemplate.PAYMENT_CONFIRMATION;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.mail.javamail.MimeMessageHelper.MULTIPART_MODE_RELATED;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {



    @Value("${email.from}")
    private String emailFrom;
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Async
    public void sendPaymentSuccessEmail(String to, String customerName, BigDecimal amount, String orderReference) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, MULTIPART_MODE_RELATED, UTF_8.name());
        mimeMessageHelper.setFrom(emailFrom);
        final String templateName = PAYMENT_CONFIRMATION.getTemplate();
        Map<String, Object> model = new HashMap<>();
        model.put("customerName", customerName);
        model.put("amount", amount);
        model.put("orderReference", orderReference);
        Context context = new Context();
        context.setVariables(model);
        mimeMessageHelper.setSubject(PAYMENT_CONFIRMATION.getSubject());

        try {
            String html = templateEngine.process(templateName, context);
            mimeMessageHelper.setText(html, true);
            mimeMessageHelper.setTo(to);
            mailSender.send(mimeMessage);
            log.info("Email sent successfully to {}", to);
        }catch (MessagingException e) {
            log.warn("WARNING cannot sent email to: " +to + " " +e.getMessage(), e);
        }
    }

    @Async
    public void sendOrderConfirmationEmail(String to, String customerName, BigDecimal amount, String orderReference, List<Product> products) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, MULTIPART_MODE_RELATED, UTF_8.name());
        mimeMessageHelper.setFrom(emailFrom);
        final String templateName = ORDER_CONFIRMATION.getTemplate();
        Map<String, Object> model = new HashMap<>();
        model.put("customerName", customerName);
        model.put("totalAmount", amount);
        model.put("orderReference", orderReference);
        model.put("products", products);
        Context context = new Context();
        context.setVariables(model);
        mimeMessageHelper.setSubject(ORDER_CONFIRMATION.getSubject());

        try {
            String html = templateEngine.process(templateName, context);
            mimeMessageHelper.setText(html, true);
            mimeMessageHelper.setTo(to);
            mailSender.send(mimeMessage);
            log.info("Email sent successfully to {}", to);
        }catch (MessagingException e) {
            log.warn("WARNING cannot sent email to: " +to + " " +e.getMessage(), e);
        }
    }
}
