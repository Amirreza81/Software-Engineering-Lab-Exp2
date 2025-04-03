
import java.util.Date;
import java.util.Map;

/**
 * Class representing a Digital Wallet Payment.
 */
class DigitalWalletPayment extends Payment {

    /**
     * Constructs a DigitalWalletPayment with the specified details.
     *
     * @param amount         the payment amount
     * @param currency       the currency of the payment
     * @param customerInfo   a map containing customer details (e.g., name, email)
     * @param paymentDetails a map containing digital wallet details (e.g., wallet id)
     */
    public DigitalWalletPayment(double amount, String currency, Map<String, String> customerInfo, Map<String, String> paymentDetails) {
        super(amount, currency, customerInfo, paymentDetails);
    }

    /**
     * Validates the digital wallet payment.
     * Checks for a positive amount, supported currency, presence of customer email, and the existence of a wallet id.
     *
     * @return true if the payment is valid; false otherwise
     */
    @Override
    public boolean validatePayment() {
        if (amount <= 0) {
            return false;
        }
        if (!("USD".equals(currency) || "EUR".equals(currency) || "GBP".equals(currency))) {
            return false;
        }
        if (!customerInfo.containsKey("email")) {
            return false;
        }
        return paymentDetails.containsKey("wallet_id");
    }

    /**
     * Processes the digital wallet payment by connecting to the Digital Wallet API.
     *
     * @param config configuration map containing the "digital_wallet_endpoint"
     * @return a map with the result including a status and a generated transaction id
     */
    @Override
    public Map<String, String> processPayment(Map<String, String> config) {
        System.out.println("Connecting to Digital Wallet API at " + config.get("digital_wallet_endpoint"));
        String transactionId = "DW" + new Date().getTime();
        System.out.println("Processing digital wallet payment for " + customerInfo.get("name"));
        return Map.of("status", "success", "transaction_id", transactionId);
    }
}
