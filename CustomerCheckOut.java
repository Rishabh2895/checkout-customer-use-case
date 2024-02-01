// Customer class represents a customer with basic information and credentials
class Customer {
    // Private fields for customer details
    private String email;
    private int cardNo;
    private String password;
    private String accountID;

    // Constructor to initialize customer details
    public Customer(String email, int cardNo, String password, String accountID) {
        this.email = email;
        this.cardNo = cardNo;
        this.password = password;
        this.accountID = accountID;
    }

    // Getters for customer details
    public int getCardNo() {
        return cardNo;
    }

    public String getEmail() {
        return email;
    }

    public String getAccountID() {
        return accountID;
    }

    public String getPassword() {
        return password;
    }

    // Method to check if input credentials match customer credentials
    public boolean matchCredentials(String email, String password, int cardNo, String accountID) {
        return this.email.equals(email) && this.password.equals(password)
                && this.cardNo == cardNo && this.accountID.equals(accountID);
    }
}

// DeliveryOrder class represents an order placed by a customer
class DeliveryOrder {
    // Private fields for order details
    private String authorizationNo;
    private double totalPrice;
    private int quantity;
    private String item;
    private String accountID;
    private String orderStatus;

    // Constructor to initialize order details
    public DeliveryOrder(String authorizationNumber, double totalPrice, int quantity, String item, String accountID) {
        this.authorizationNo = authorizationNumber;
        this.totalPrice = totalPrice;
        this.quantity = quantity;
        this.item = item;
        this.accountID = accountID;
        this.orderStatus = "Pending";
    }

    // Getters for order details
    public String getAuthorizationNo() {
        return authorizationNo;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getItem() {
        return item;
    }

    public String getAccountID() {
        return accountID;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    // Setter to update order status
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}

// PurchaseOrderManager manages the creation and processing of orders
class PurchaseOrderManager {
    // Private field for the payment gateway
    private SecurePaymentGateway paymentGateway;

    // Constructor to initialize PurchaseOrderManager with a payment gateway
    public PurchaseOrderManager(SecurePaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    // Method to create an order for a customer
    public DeliveryOrder createOrder(Customer customer, double totalPrice, int quantity, String item) {
        if (validateCustomerCredentials(customer)) {
            if (authorizePayment(customer.getCardNo(), totalPrice)) {
                String authorizationNumber = paymentGateway.processPayment(customer.getCardNo(), totalPrice);
                DeliveryOrder order = new DeliveryOrder(authorizationNumber, totalPrice, quantity, item, customer.getAccountID());
                sendOrderConfirmationEmail(customer, item);
                return order;
            } else {
                System.out.println("Payment authorization failed. Order cannot be created.");
                return null;
            }
        } else {
            System.out.println("Invalid customer credentials. Order cannot be created.");
            return null;
        }
    }

    // Private method to validate customer credentials
    private boolean validateCustomerCredentials(Customer customer) {
        // Implement logic to verify customer credentials against a database or authentication server.
        return true; // Simulate successful authentication
    }

    // Private method to authorize payment
    private boolean authorizePayment(int cardNo, double totalPrice) {
        // Implement logic to check credit card authorization using a secure payment gateway.
        return true; // Simulate successful authorization
    }

    // Private method to send an order confirmation email
    private void sendOrderConfirmationEmail(Customer customer, String item) {
        // Implement logic to send an order confirmation email to the customer.
        System.out.println("Order confirmation email sent to " + customer.getEmail() + " for item: " + item);
    }
}

// SecurePaymentGateway handles payment processing securely
class SecurePaymentGateway {
    // Implement methods for processing payments securely using industry-standard encryption and authorization protocols.
    public String processPayment(int cardNo, double totalPrice) {
        // Simulate payment processing and return an authorization number.
        return "7777";
    }
}