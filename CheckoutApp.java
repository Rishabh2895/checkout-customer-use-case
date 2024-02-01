import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

// Main application to simulate a checkout process
public class CheckoutApp {
    public static void main(String[] args) {
        // Create instances for customers
        Customer steve = new Customer("steve@ttu.edu", 13579, "2345", "steve");
        Customer alex = new Customer("alex@ttu.edu", 13579, "4567", "alex");
        Customer james = new Customer("james@ttu.edu", 13579, "6789", "james");
        Customer john = new Customer("john@ttu.edu", 13579, "5678", "john");
        Customer sam = new Customer("sam@ttu.edu", 13579, "7890", "sam");
        // Add more customers as needed

        // Create a list to store customer instances
        List<Customer> customers = new ArrayList<>();
        customers.add(steve);
        customers.add(alex);
        customers.add(james);
        customers.add(john);
        customers.add(sam);
        // Add more customers as needed

        // Create PurchaseOrderManager instance with a secure payment gateway
        SecurePaymentGateway securePaymentGateway = new SecurePaymentGateway(); // Replace with a real payment gateway implementation
        PurchaseOrderManager orderManager = new PurchaseOrderManager(securePaymentGateway);

        Scanner scanner = new Scanner(System.in);

        // Prompt user for email and password
        System.out.print("Enter your email: ");
        String inputEmail = scanner.nextLine();
        System.out.print("Enter your password: ");
        String inputPassword = scanner.nextLine();

        // Find the matching customer based on input email and password
        Customer matchingCustomer = null;
        for (Customer customer : customers) {
            if (customer.getEmail().equals(inputEmail) && customer.getPassword().equals(inputPassword)) {
                matchingCustomer = customer;
                break;
            }
        }

        if (matchingCustomer != null) {
            System.out.print("Enter your card number: ");
            int inputCardNo = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter your account ID: ");
            String inputAccountID = scanner.nextLine();

            if (matchingCustomer.matchCredentials(inputEmail, inputPassword, inputCardNo, inputAccountID)) {
                System.out.print("Enter order total price: ");
                double totalPrice = Double.parseDouble(scanner.nextLine());
                System.out.print("Enter order quantity: ");
                int quantity = Integer.parseInt(scanner.nextLine());
                System.out.print("Enter order item: ");
                String item = scanner.nextLine();

                // Simulate a customer placing an order
                DeliveryOrder order = orderManager.createOrder(matchingCustomer, totalPrice, quantity, item);

                if (order != null) {
                    System.out.println("Order created with authorization number: " + order.getAuthorizationNo());
                    order.setOrderStatus("Processing"); // Update order status to reflect processing
                    System.out.println("Order status: " + order.getOrderStatus());
                }
            } else {
                System.out.println("Invalid credentials. Authorization failed: 0000");
            }
        } else {
            System.out.println("Invalid email or password. Please try again!");
        }

        scanner.close();
    }
}