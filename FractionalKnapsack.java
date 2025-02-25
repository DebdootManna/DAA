import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class Item {
    int value;
    int weight;
    double ratio;

    public Item(int value, int weight) {
        this.value = value;
        this.weight = weight;
        this.ratio = (double) value / weight;
    }
}

public class FractionalKnapsack {

    // Solves the fractional knapsack problem using a greedy strategy
    public static double fractionalKnapsack(int capacity, Item[] items) {
        // Sort items by value-to-weight ratio in descending order
        Arrays.sort(items, Comparator.comparingDouble((Item item) -> item.ratio).reversed());
        double totalValue = 0.0;
        int remainingCapacity = capacity;

        // Process each item in sorted order
        for (Item item : items) {
            if (remainingCapacity == 0) {
                break;
            }
            // If the item can be taken fully
            if (item.weight <= remainingCapacity) {
                totalValue += item.value;
                remainingCapacity -= item.weight;
            } else { // Take fraction of the item
                totalValue += item.ratio * remainingCapacity;
                remainingCapacity = 0;
            }
        }
        return totalValue;
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter number of items: ");
            int n = scanner.nextInt();
            Item[] items = new Item[n];

            for (int i = 0; i < n; i++) {
                System.out.print("Enter value for item " + (i + 1) + ": ");
                int value = scanner.nextInt();
                System.out.print("Enter weight for item " + (i + 1) + ": ");
                int weight = scanner.nextInt();
                items[i] = new Item(value, weight);
            }

            System.out.print("Enter knapsack capacity: ");
            int capacity = scanner.nextInt();

            double maxValue = fractionalKnapsack(capacity, items);
            System.out.println("Maximum value achievable (Fractional Knapsack): " + maxValue);
        }
    }
}