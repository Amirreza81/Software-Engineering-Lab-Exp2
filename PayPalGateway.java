import java.util.Date;
import java.util.Map;

/**
 * PayPalGateway is an implementation of the PaymentGateway for PayPal.
 */
public class PayPalGateway extends BaseGateway {

    /**
     * Constructs a PayPalGateway with the specified API endpoint and API key.
     *
     * @param apiEndpoint the PayPal API endpoint URL
     * @param apiKey      the API key for PayPal authentication
     */
    public PayPalGateway (String apiEndpoint, String apiKey) {
        super(apiEndpoint, apiKey);
    }

    /**
     * Processes a payment using PayPal.
     *
     * @param paymentDetails a map containing payment details
     * @return a map containing the processing result
     */
    @Override
    public Map<String, String> processPayment (Map<String, String> paymentDetails) {
        System.out.println("Processing payment through PayPal at " + apiEndpoint);
        String transactionId = "PP" + new Date().getTime();
        return createResponse("success", transactionId);
    }

    /**
     * Refunds a payment using PayPal.
     *
     * @param transactionId the transaction id to refund
     * @return a map containing the refund result
     */
    @Override
    public Map<String, String> refundPayment (String transactionId) {
        System.out.println("Processing refund through PayPal for transaction " + transactionId);
        return createResponse("refunded", transactionId);
    }

    /**
     * Retrieves the status of a transaction from PayPal.
     *
     * @param transactionId the transaction id
     * @return the transaction status as a string
     */
    @Override
    public String getTransactionStatus (String transactionId) {
        System.out.println("Checking transaction status on PayPal for " + transactionId);
        return "completed";
    }
}
