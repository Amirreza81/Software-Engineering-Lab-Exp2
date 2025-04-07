import java.util.Map;

/**
 * PaymentGateway interface separates external payment gateway integration
 * from the core payment logic.
 */
public interface PaymentGateway {
    /**
     * Processes a payment through the gateway.
     *
     * @param paymentDetails a map containing payment details
     * @return a map containing the processing result (e.g., status, transaction id)
     */
    Map<String, String> processPayment (Map<String, String> paymentDetails);

    /**
     * Refunds a payment using the provided transaction id.
     *
     * @param transactionId the identifier of the transaction to refund
     * @return a map containing the refund result
     */
    Map<String, String> refundPayment (String transactionId);

    /**
     * Retrieves the status of a transaction.
     *
     * @param transactionId the identifier of the transaction
     * @return a string representing the transaction status
     */
    String getTransactionStatus (String transactionId);
}
