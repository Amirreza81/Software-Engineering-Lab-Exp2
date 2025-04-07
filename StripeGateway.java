import java.util.Date;
import java.util.Map;

/**
 * StripeGateway is an implementation of the PaymentGateway for Stripe.
 */
public class StripeGateway extends BaseGateway {

    /**
     * Constructs a StripeGateway with the specified API endpoint and API key.
     *
     * @param apiEndpoint the Stripe API endpoint URL
     * @param apiKey      the API key for Stripe authentication
     */
    public StripeGateway (String apiEndpoint, String apiKey) {
        super(apiEndpoint, apiKey);
    }

    /**
     * Processes a payment using Stripe.
     *
     * @param paymentDetails a map containing payment details
     * @return a map containing the processing result
     */
    @Override
    public Map<String, String> processPayment (Map<String, String> paymentDetails) {
        System.out.println("Processing payment through Stripe at " + apiEndpoint);
        String transactionId = "ST" + new Date().getTime();
        return createResponse("success", transactionId);
    }

    /**
     * Refunds a payment using Stripe.
     *
     * @param transactionId the transaction id to refund
     * @return a map containing the refund result
     */
    @Override
    public Map<String, String> refundPayment (String transactionId) {
        System.out.println("Processing refund through Stripe for transaction " + transactionId);
        return createResponse("refunded", transactionId);
    }

    /**
     * Retrieves the status of a transaction from Stripe.
     *
     * @param transactionId the transaction id
     * @return the transaction status as a string
     */
    @Override
    public String getTransactionStatus (String transactionId) {
        System.out.println("Checking transaction status on Stripe for " + transactionId);
        return "completed";
    }
}
