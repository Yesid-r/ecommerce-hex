package co.edu.uptc.email;

import lombok.Getter;

public enum EmailTemplate {

    PAYMENT_CONFIRMATION("payment-confirmation.html", "Payment successfully processed"),
    ORDER_CONFIRMATION("order-confirmation.html", "Order confirmation");

    @Getter
    private String template;
    @Getter
    private String subject;
    EmailTemplate(String template, String subject) {
        this.template = template;
        this.subject = subject;
    }

}
