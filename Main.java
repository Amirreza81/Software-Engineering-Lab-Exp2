import java.util.HashMap;
import java.util.Map;

/**
 * Main class to demonstrate the usage of the payment system with dependency injection.
 */
public class Main {
    public static void main (String[] args) {
        // Create a configuration manager to load sensitive configuration details.
        ConfigurationManager configManager = new ConfigurationManager();
        String stripeEndpoint = configManager.getConfigValue("stripe_api_endpoint");
        String stripeKey = configManager.getConfigValue("stripe_api_key");
        String paypalEndpoint = configManager.getConfigValue("paypal_api_endpoint");
        String paypalKey = configManager.getConfigValue("paypal_api_key");

        // Create sample payment details.
        Map<String, String> paymentDetails = new HashMap<>();
        paymentDetails.put("amount", "100");
        paymentDetails.put("currency", "USD");
        paymentDetails.put("customer_email", "john@example.com");

        // ------------------ Test with StripeGateway ------------------
        System.out.println("Testing StripeGateway:");
        PaymentGateway stripeGateway = new StripeGateway(stripeEndpoint, stripeKey);
        PaymentProcessor stripeProcessor = new PaymentProcessor(stripeGateway);

        // Process payment with Stripe.
        Map<String, String> stripeResult = stripeProcessor.processPayment(paymentDetails);
        System.out.println("Stripe Payment Result: " + stripeResult);

        // Refund payment with Stripe.
        String stripeTransactionId = stripeResult.get("transaction_id");
        Map<String, String> stripeRefund = stripeProcessor.refundPayment(stripeTransactionId);
        System.out.println("Stripe Refund Result: " + stripeRefund);

        // Get transaction status with Stripe.
        String stripeStatus = stripeProcessor.getTransactionStatus(stripeTransactionId);
        System.out.println("Stripe Transaction Status: " + stripeStatus);

        System.out.println("\n----------------------------------------\n");

        // ------------------ Test with PayPalGateway ------------------
        System.out.println("Testing PayPalGateway:");
        PaymentGateway paypalGateway = new PayPalGateway(paypalEndpoint, paypalKey);
        PaymentProcessor paypalProcessor = new PaymentProcessor(paypalGateway);

        // Process payment with PayPal.
        Map<String, String> paypalResult = paypalProcessor.processPayment(paymentDetails);
        System.out.println("PayPal Payment Result: " + paypalResult);

        // Refund payment with PayPal.
        String paypalTransactionId = paypalResult.get("transaction_id");
        Map<String, String> paypalRefund = paypalProcessor.refundPayment(paypalTransactionId);
        System.out.println("PayPal Refund Result: " + paypalRefund);

        // Get transaction status with PayPal.
        String paypalStatus = paypalProcessor.getTransactionStatus(paypalTransactionId);
        System.out.println("PayPal Transaction Status: " + paypalStatus);
    }
}
