import java.util.HashMap;
import java.util.Map;

/**
 * BaseGateway provides common functionality for payment gateways.
 */
public abstract class BaseGateway implements PaymentGateway {
    protected String apiEndpoint;
    protected String apiKey;

    /**
     * Constructs a BaseGateway with the specified API endpoint and API key.
     *
     * @param apiEndpoint the endpoint URL for the payment gateway API
     * @param apiKey      the API key for authentication
     */
    public BaseGateway (String apiEndpoint, String apiKey) {
        this.apiEndpoint = apiEndpoint;
        this.apiKey = apiKey;
    }

    /**
     * Creates a common response map for gateway operations.
     *
     * @param status        the operation status (e.g., success, refunded)
     * @param transactionId the generated or provided transaction identifier
     * @return a map containing the response details
     */
    protected Map<String, String> createResponse (String status, String transactionId) {
        Map<String, String> response = new HashMap<>();
        response.put("status", status);
        response.put("transaction_id", transactionId);
        return response;
    }
}
