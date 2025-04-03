import java.util.Date;
import java.util.Map;



/**
 * Class responsible for processing payments.
 * It coordinates the validation and processing of a Payment instance and logs the transaction.
 */
public class PaymentProcessor {
    private Map<String, String> config;

    /**
     * Constructs a PaymentProcessor with the specified configuration.
     *
     * @param config a map containing API endpoints and other configuration details
     */
    public PaymentProcessor(Map<String, String> config) {
        this.config = config;
    }

    /**
     * Processes the given payment by validating and then processing it.
     * Logs the transaction after processing.
     *
     * @param payment the Payment instance to be processed
     * @return a map containing the result of the payment processing
     */
    public Map<String, String> process(Payment payment) {
        // Validate the payment details
        if (!payment.validatePayment()) {
            return Map.of("status", "failed", "message", "Validation error");
        }
        // Process the payment using the specific payment method's implementation
        Map<String, String> result = payment.processPayment(config);
        // Log the transaction
        logTransaction(payment, result);
        return result;
    }

    /**
     * Logs the details of the processed payment.
     *
     * @param payment the Payment instance that was processed
     * @param result  the result returned by the payment processing
     */
    private void logTransaction(Payment payment, Map<String, String> result) {
        String logEntry = String.format("%s - Payment of %.2f %s for %s: %s",
                payment.timestamp, payment.amount, payment.currency, payment.customerInfo.get("name"), result);
        System.out.println("LOG: " + logEntry);
    }
}