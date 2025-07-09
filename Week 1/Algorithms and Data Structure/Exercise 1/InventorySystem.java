import java.util.HashMap;
import java.util.Scanner;

class Product {
    int productId;
    String productName;
    int quantity;
    double price;

    public Product(int productId, String productName, int quantity, double price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    public String toString() {
        return "ID: " + productId + ", Name: " + productName + ", Qty: " + quantity + ", Price: $" + price;
    }
}

public class InventorySystem {
    static HashMap<Integer, Product> inventory = new HashMap<>();

    public static void addProduct(Product product) {
        inventory.put(product.productId, product);
        System.out.println("Product added.");
    }

    public static void updateProduct(int id, int quantity, double price) {
        if (inventory.containsKey(id)) {
            Product p = inventory.get(id);
            p.quantity = quantity;
            p.price = price;
            System.out.println("Product updated.");
        } else {
            System.out.println("Product not found.");
        }
    }

    public static void deleteProduct(int id) {
        if (inventory.remove(id) != null) {
            System.out.println("Product deleted.");
        } else {
            System.out.println("Product not found.");
        }
    }

    public static void displayInventory() {
        for (Product p : inventory.values()) {
            System.out.println(p);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n1. Add 2. Update 3. Delete 4. View 5. Exit");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter ID, Name, Qty, Price: ");
                    int id = sc.nextInt();
                    String name = sc.next();
                    int qty = sc.nextInt();
                    double price = sc.nextDouble();
                    addProduct(new Product(id, name, qty, price));
                    break;
                case 2:
                    System.out.print("Enter ID to update, Qty, Price: ");
                    id = sc.nextInt();
                    qty = sc.nextInt();
                    price = sc.nextDouble();
                    updateProduct(id, qty, price);
                    break;
                case 3:
                    System.out.print("Enter ID to delete: ");
                    id = sc.nextInt();
                    deleteProduct(id);
                    break;
                case 4:
                    displayInventory();
                    break;
                case 5:
                    running = false;
                    break;
            }
        }
        sc.close();
    }
}