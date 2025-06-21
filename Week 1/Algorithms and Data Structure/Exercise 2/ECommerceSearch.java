import java.util.*;

class Product {
    int productId;
    String productName;
    String category;

    public Product(int productId, String productName, String category) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
    }
}

public class ECommerceSearch {

   
    public static Product linearSearch(Product[] products, String targetName) {
        for (Product product : products) {
            if (product.productName.equalsIgnoreCase(targetName)) {
                return product;
            }
        }
        return null;
    }

    
    public static Product binarySearch(Product[] products, String targetName) {
        int left = 0;
        int right = products.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            int compare = products[mid].productName.compareToIgnoreCase(targetName);

            if (compare == 0) {
                return products[mid];
            } else if (compare < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return null;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        
        System.out.print("Enter number of products: ");
        int n = sc.nextInt();
        sc.nextLine(); // Consume newline

        Product[] products = new Product[n];

        
        for (int i = 0; i < n; i++) {
            System.out.println("Enter details for Product " + (i + 1) + ":");
            System.out.print("Product ID: ");
            int id = sc.nextInt();
            sc.nextLine(); // Consume newline

            System.out.print("Product Name: ");
            String name = sc.nextLine();

            System.out.print("Category: ");
            String category = sc.nextLine();

            products[i] = new Product(id, name, category);
        }

        
        System.out.print("\nEnter product name to search: ");
        String target = sc.nextLine();

        
        Product foundLinear = linearSearch(products, target);
        if (foundLinear != null) {
            System.out.println("\n[Linear Search] Found: " + foundLinear.productName + " - " + foundLinear.category);
        } else {
            System.out.println("\n[Linear Search] Product not found.");
        }

        
        Arrays.sort(products, Comparator.comparing(p -> p.productName.toLowerCase()));

        
        Product foundBinary = binarySearch(products, target);
        if (foundBinary != null) {
            System.out.println("[Binary Search] Found: " + foundBinary.productName + " - " + foundBinary.category);
        } else {
            System.out.println("[Binary Search] Product not found.");
        }

        sc.close();
    }
}
