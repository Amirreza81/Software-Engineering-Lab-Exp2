import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * Abstract base class for base gateway functionality.
 */
public abstract class BaseGateway implements PaymentGateway {
    protected String apiEndpoint;

    /**
     * Constructs a BaseGateway with the specified API endpoint.
     *
     * @param apiEndpoint the endpoint URL for the payment gateway API
     */
    public BaseGateway(String apiEndpoint) {
        this.apiEndpoint = apiEndpoint;
    }

    /**
     * Creates a common response map for gateway operations.
     *
     * @param status        the operation status (e.g., success, refunded)
     * @param transactionId the generated or provided transaction identifier
     * @return a map containing the response details
     */
    protected Map<String, String> createResponse(String status, String transactionId) {
        Map<String, String> response = new HashMap<>();
        response.put("status", status);
        response.put("transaction_id", transactionId);
        return response;
    }
}
