import java.util.Date;
import java.util.Map;



/**
 * PaymentProcessor class that processes payments using a PaymentGateway.
 * This class demonstrates polymorphism by allowing any PaymentGateway implementation
 * to be used for processing, refunding, and checking transaction status.
 */
public class PaymentProcessor {
    private PaymentGateway gateway;

    /**
     * Constructs a PaymentProcessor with the specified PaymentGateway.
     *
     * @param gateway an implementation of the PaymentGateway interface
     */
    public PaymentProcessor(PaymentGateway gateway) {
        this.gateway = gateway;
    }

    /**
     * Processes a payment using the configured payment gateway.
     *
     * @param paymentDetails a map containing payment details
     * @return a map containing the payment processing result
     */
    public Map<String, String> processPayment(Map<String, String> paymentDetails) {
        return gateway.processPayment(paymentDetails);
    }

    /**
     * Refunds a payment using the configured payment gateway.
     *
     * @param transactionId the id of the transaction to refund
     * @return a map containing the refund result
     */
    public Map<String, String> refundPayment(String transactionId) {
        return gateway.refundPayment(transactionId);
    }

    /**
     * Gets the transaction status from the configured payment gateway.
     *
     * @param transactionId the id of the transaction
     * @return a string representing the transaction status
     */
    public String getTransactionStatus(String transactionId) {
        return gateway.getTransactionStatus(transactionId);
    }
}
