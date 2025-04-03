import java.util.Date;
import java.util.Map;


/**
 * Class representing a Bank Transfer Payment.
 */
class BankTransferPayment extends Payment {

    /**
     * Constructs a BankTransferPayment with the specified details.
     *
     * @param amount         the payment amount
     * @param currency       the currency of the payment
     * @param customerInfo   a map containing customer details (e.g., name, email)
     * @param paymentDetails a map containing bank transfer details (e.g., account number)
     */
    public BankTransferPayment(double amount, String currency, Map<String, String> customerInfo, Map<String, String> paymentDetails) {
        super(amount, currency, customerInfo, paymentDetails);
    }

    /**
     * Validates the bank transfer payment.
     * Checks for a positive amount, supported currency, presence of customer email, and the existence of an account number.
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
        return paymentDetails.containsKey("account_number");
    }

    /**
     * Processes the bank transfer payment by connecting to the Bank Transfer API.
     *
     * @param config configuration map containing the "bank_transfer_endpoint"
     * @return a map with the result including a status and a generated transaction id
     */
    @Override
    public Map<String, String> processPayment(Map<String, String> config) {
        System.out.println("Connecting to Bank Transfer API at " + config.get("bank_transfer_endpoint"));
        String transactionId = "BT" + new Date().getTime();
        System.out.println("Processing bank transfer payment for " + customerInfo.get("name"));
        return Map.of("status", "success", "transaction_id", transactionId);
    }
}