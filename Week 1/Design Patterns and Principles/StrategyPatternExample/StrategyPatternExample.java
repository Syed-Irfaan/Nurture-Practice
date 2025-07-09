import java.util.Scanner;

interface PaymentStrategy {
    void pay(double amount);
}

class CreditCardPayment implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("Paid ₹" + amount + " using Credit Card.");
    }
}

class PayPalPayment implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("Paid ₹" + amount + " using PayPal.");
    }
}

class PaymentContext {
    private PaymentStrategy strategy;

    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void payAmount(double amount) {
        if (strategy != null) {
            strategy.pay(amount);
        } else {
            System.out.println("Payment strategy not selected.");
        }
    }
}

public class StrategyPatternExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PaymentContext context = new PaymentContext();

        System.out.println("Enter payment amount:");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        System.out.println("Select payment method (credit/paypal):");
        String method = scanner.nextLine().toLowerCase();

        switch (method) {
            case "credit":
                context.setPaymentStrategy(new CreditCardPayment());
                break;
            case "paypal":
                context.setPaymentStrategy(new PayPalPayment());
                break;
            default:
                System.out.println("Invalid payment method selected.");
                scanner.close();
                return;
        }

        context.payAmount(amount);
        scanner.close();
    }
}
