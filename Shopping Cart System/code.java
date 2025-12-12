import java.util.*;

public class code {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Creating a Hashmap to store inventory
        HashMap<Integer, Product> inventory = new HashMap<>();
        inventory.put(1, new Product("Mango", 100, 100));
        inventory.put(2, new Product("Apple", 150, 500));
        inventory.put(3, new Product("Grapes", 80, 300));
        inventory.put(4, new Product("Banana", 40, 100));
        inventory.put(5, new Product("Watermelon", 60, 200));

        // Creating an ArryaList to store cart product
        ArrayList<Product> cart = new ArrayList<>();

        while (true) {
            System.out.println("1. View Inventory or Add items to cart");
            System.out.println("2. View Cart");
            System.out.println("3. Remove items from Cart");
            System.out.println("4. Get Bill");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    displayInventory(inventory);
                    addItemsToCart(inventory, sc, cart);
                    break;
                case 2:
                    viewCart(cart);
                    break;
                case 3:
                    removeFromCart(cart, sc, inventory);
                    break;
                case 4:
                    getBill(cart, sc, inventory);
                    break;
                case 5:
                    System.out.println("Exiting the system. Thank you for shopping!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("ERROR: Enter a valid choice !!");
                    break;
            }
        }
    }

    private static void displayInventory(HashMap<Integer, Product> inventory) {
        System.out.println();
        System.out.println("======================================");
        System.out.println("          Available Inventory         ");
        System.out.println("======================================");
        for (Integer productId : inventory.keySet()) {
            System.out.println("Product ID: " + productId + ", " + inventory.get(productId));
        }
        System.out.println("--------------------------------------");
        System.out.println();
    }

    private static void addItemsToCart(HashMap<Integer, Product> inventory, Scanner sc, ArrayList<Product> cart) {
        while (true) {
            System.out.print("Enter a Product ID to add to cart (or 0 to exit): ");
            int productId = sc.nextInt();
            if (productId == 0) {
                System.out.println("\nReturning to main menu...");
                System.out.println("--------------------------------------");
                return;
            }

            if (!inventory.containsKey(productId)) {
                System.out.println("ERROR: Invalid Product ID. Please try again!");
                System.out.println("--------------------------------------");
                continue;
            }

            Product product = inventory.get(productId);
            System.out.print("Enter quantity: ");
            int quantity = sc.nextInt();

            if (quantity > product.getStock()) {
                System.out.println("ERROR: Insufficient stock. Available stock: " + product.getStock());
                System.out.println("--------------------------------------");
            } else {
                cart.add(new Product(product.getName(), product.getPrice(), quantity));

                // Update stock in the inventory
                inventory.put(productId,
                        new Product(product.getName(), product.getPrice(), product.getStock() - quantity));
                System.out.println("SUCCESS: Item added to cart!");
                System.out.println("--------------------------------------");
            }
        }
    }

    private static void viewCart(ArrayList<Product> cart) {
        if (cart.isEmpty()) {
            System.out.println("======================================");
            System.out.println("              Your Cart               ");
            System.out.println("======================================");
            System.out.println("Your cart is empty!");
            System.out.println("--------------------------------------");
            return;
        }

        System.out.println("======================================");
        System.out.println("              Your Cart               ");
        System.out.println("======================================");
        for (Product item : cart) {
            System.out.println(item);
        }
        System.out.println("--------------------------------------");
    }

    private static void removeFromCart(ArrayList<Product> cart, Scanner sc, HashMap<Integer, Product> inventory) {
        if (cart.isEmpty()) {
            System.out.println("Your cart is Empty!");
            System.out.println();
            return;
        }
        while (true) {
            System.out.print("\nEnter the Product Id to remove from cart (or 0 to exit): ");
            int itemNumber = sc.nextInt();
            if (itemNumber == 0) {
                System.out.println();
                return;
            }

            if (itemNumber < 1 || itemNumber > cart.size()) {
                System.out.println("Invalid Product Id. Try again.");
                System.out.println();
                continue;
            }
            Product removedItem = cart.remove(itemNumber - 1);
            // Update the inventory stock
            for (Integer productId : inventory.keySet()) {
                Product product = inventory.get(productId);
                if (product.getName().equals(removedItem.getName())) {
                    inventory.put(productId, new Product(product.getName(), product.getPrice(),
                            product.getStock() + removedItem.getQuantity()));
                    break;
                }
            }

            System.out.println("Item removed from cart!");
        }
    }

    private static void getBill(ArrayList<Product> cart, Scanner sc, HashMap<Integer, Product> inventory) {
        if (cart.isEmpty()) {
            System.out.println("======================================");
            System.out.println("          Checkout Unavailable        ");
            System.out.println("======================================");
            System.out.println("Your cart is empty. Nothing to checkout.");
            System.out.println("--------------------------------------");
            return;
        }

        // Calculate total bill
        double totalBill = 0.0;
        for (Product item : cart) {
            totalBill += item.getQuantity() * item.getPrice();
        }

        System.out.println("======================================");
        System.out.println("             Checkout Bill            ");
        System.out.println("======================================");
        System.out.println("Total Bill: $" + totalBill);

        // Apply discount code
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter discount code (or press Enter to skip): ");
        String discountCode = scanner.nextLine();

        double discount = 0.0;
        if (discountCode.equalsIgnoreCase("SAVE10")) {
            discount = totalBill * 10 / 100;
            System.out.println("Discount Applied: 10% (-$" + discount + ")");
            System.out.println("Hurrah! You save $" + discount + "!");
        }

        // Calculate final amount after discount
        double finalAmount = totalBill - discount;
        System.out.println("Final Amount to Pay: $" + finalAmount);
        System.out.print("\nEnter amount " + finalAmount + " to pay and exit: ");
        int pay = sc.nextInt();

        if (pay == finalAmount) {
            // Clear the cart
            cart.clear();
            System.out.println("======================================");
            System.out.println("         Checkout Complete!           ");
            System.out.println("======================================");
            System.out.println("Thank you for shopping with us!");
            System.out.println("--------------------------------------");
        }
    }
}

class Product {
    String name;
    double price;
    int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public int getStock() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Price: $" + price + ", Quantity: " + quantity;
    }
}