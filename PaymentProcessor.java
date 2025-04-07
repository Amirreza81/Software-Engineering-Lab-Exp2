import java.util.Map;

/**
 * PaymentProcessor handles the payment processing by working with a PaymentGateway.
 * This demonstrates dependency injection where any PaymentGateway implementation can be injected.
 */
public class PaymentProcessor {
    private PaymentGateway paymentGateway;

    /**
     * Constructs a PaymentProcessor with the injected PaymentGateway.
     *
     * @param paymentGateway an implementation of the PaymentGateway interface
     */
    public PaymentProcessor (PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    /**
     * Processes a payment using the configured payment gateway.
     *
     * @param paymentDetails a map containing payment details
     * @return a map containing the payment processing result
     */
    public Map<String, String> processPayment (Map<String, String> paymentDetails) {
        return paymentGateway.processPayment(paymentDetails);
    }

    /**
     * Refunds a payment using the configured payment gateway.
     *
     * @param transactionId the id of the transaction to refund
     * @return a map containing the refund result
     */
    public Map<String, String> refundPayment (String transactionId) {
        return paymentGateway.refundPayment(transactionId);
    }

    /**
     * Gets the transaction status from the configured payment gateway.
     *
     * @param transactionId the id of the transaction
     * @return a string representing the transaction status
     */
    public String getTransactionStatus (String transactionId) {
        return paymentGateway.getTransactionStatus(transactionId);
    }
}
