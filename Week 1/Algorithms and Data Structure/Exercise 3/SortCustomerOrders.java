import java.util.Scanner;

class Order {
    int orderId;
    String customerName;
    double totalPrice;

    public Order(int orderId, String customerName, double totalPrice) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.totalPrice = totalPrice;
    }

    public void display() {
        System.out.println("OrderID: " + orderId + ", Customer: " + customerName + ", Total Price: " + totalPrice);
    }
}

public class SortCustomerOrders {

    
    public static void bubbleSort(Order[] orders) {
        int n = orders.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (orders[j].totalPrice > orders[j + 1].totalPrice) {
                    Order temp = orders[j];
                    orders[j] = orders[j + 1];
                    orders[j + 1] = temp;
                }
            }
        }
    }

    
    public static void quickSort(Order[] orders, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(orders, low, high);
            quickSort(orders, low, pivotIndex - 1);
            quickSort(orders, pivotIndex + 1, high);
        }
    }

    private static int partition(Order[] orders, int low, int high) {
        double pivot = orders[high].totalPrice;
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (orders[j].totalPrice < pivot) {
                i++;
                Order temp = orders[i];
                orders[i] = orders[j];
                orders[j] = temp;
            }
        }
        Order temp = orders[i + 1];
        orders[i + 1] = orders[high];
        orders[high] = temp;
        return i + 1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        
        System.out.print("Enter number of orders: ");
        int n = sc.nextInt();
        sc.nextLine();

        Order[] orders = new Order[n];

        
        for (int i = 0; i < n; i++) {
            System.out.println("Enter details for Order " + (i + 1) + ":");
            System.out.print("Order ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Customer Name: ");
            String name = sc.nextLine();

            System.out.print("Total Price: ");
            double price = sc.nextDouble();
            sc.nextLine();

            orders[i] = new Order(id, name, price);
        }

        
        System.out.println("\nSorted using Bubble Sort :");
        bubbleSort(orders);
        for (Order order : orders) {
            order.display();
        }

        
        System.out.println("\nSorted using Quick Sort :");
        quickSort(orders, 0, orders.length - 1);
        for (Order order : orders) {
            order.display();
        }

        
        System.out.println("\nAnalysis:");
        System.out.println("Bubble Sort is simple but slow (O(n²)) and not suitable for large orders.");
        System.out.println("Quick Sort is faster (O(n log n)) and preferred for high-performance systems like e-commerce.");
        
        sc.close();
    }
}
