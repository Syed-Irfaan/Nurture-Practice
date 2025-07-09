import java.util.*;

interface PaymentProcessor {
    void processPayment(double amount);
}


class PayPalGateway {
    public void sendPayment(double amount) {
        System.out.println("Processing payment through PayPal: ₹" + amount);
    }
}


class StripeGateway {
    public void makeStripePayment(double amount) {
        System.out.println("Processing payment through Stripe: ₹" + amount);
    }
}


class PayPalAdapter implements PaymentProcessor {
    private PayPalGateway payPal;

    public PayPalAdapter(PayPalGateway payPal) {
        this.payPal = payPal;
    }

    public void processPayment(double amount) {
        payPal.sendPayment(amount);
    }
}


class StripeAdapter implements PaymentProcessor {
    private StripeGateway stripe;

    public StripeAdapter(StripeGateway stripe) {
        this.stripe = stripe;
    }

    public void processPayment(double amount) {
        stripe.makeStripePayment(amount);
    }
}


public class AdapterPatternExample {
    public static void main(String[] args) {
        PaymentProcessor paypalProcessor = new PayPalAdapter(new PayPalGateway());
        paypalProcessor.processPayment(1000.00);

        PaymentProcessor stripeProcessor = new StripeAdapter(new StripeGateway());
        stripeProcessor.processPayment(2500.00);
    }
}
