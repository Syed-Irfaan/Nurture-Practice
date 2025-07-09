import java.util.*;

public class FinancialForecasting {

    public static double forecastRecursive(double value, double rate, int years) {
        if (years == 0) {
            return value;
        } else {
            return forecastRecursive(value, rate, years - 1) * (1 + rate);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter current value: ");
        double value = sc.nextDouble();

        System.out.print("Enter annual growth rate (e.g., 0.05 for 5%): ");
        double rate = sc.nextDouble();

        System.out.print("Enter number of years to forecast: ");
        int years = sc.nextInt();

        double futureValue = forecastRecursive(value, rate, years);
        System.out.printf("\nPredicted Future Value after %d years: %.2f\n", years, futureValue);

        

        sc.close();
    }
}
