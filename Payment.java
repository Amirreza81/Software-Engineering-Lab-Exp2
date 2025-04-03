import java.util.Date;
import java.util.Map;

/**
 * Abstract class representing a generic Payment.
 * It contains common properties such as amount, currency, timestamp, customer information, and payment details.
 */
public abstract class Payment {
    protected double amount;
    protected String currency;
    protected Date timestamp;
    protected Map<String, String> customerInfo;
    protected Map<String, String> paymentDetails;

    /**
     * Constructs a Payment with the specified amount, currency, customer information, and payment details.
     *
     * @param amount         the payment amount
     * @param currency       the currency of the payment
     * @param customerInfo   a map containing customer details (e.g., name, email)
     * @param paymentDetails a map containing specific payment details (e.g., card number, wallet id)
     */
    public Payment(double amount, String currency, Map<String, String> customerInfo, Map<String, String> paymentDetails) {
        this.amount = amount;
        this.currency = currency;
        this.customerInfo = customerInfo;
        this.paymentDetails = paymentDetails;
        this.timestamp = new Date();
    }

    /**
     * Validates the payment details. Each subclass should implement its own validation logic.
     *
     * @return true if the payment is valid; false otherwise
     */
    public abstract boolean validatePayment();

    /**
     * Processes the payment by connecting to the corresponding third-party API.
     *
     * @param config configuration map with API endpoints
     * @return a map containing the processing result such as status and transaction id
     */
    public abstract Map<String, String> processPayment(Map<String, String> config);
}
