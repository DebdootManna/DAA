import java.util.*;

public class AdvertisementSlotAllocation {
    
    public static class AdSlot implements Comparable<AdSlot> {
        int id;
        int duration;
        double price;
        double valuePerSecond;
        
        public AdSlot(int id, int duration, double price) {
            this.id = id;
            this.duration = duration;
            this.price = price;
            this.valuePerSecond = price / duration;
        }
        
        @Override
        public int compareTo(AdSlot other) {
            return Double.compare(other.valuePerSecond, this.valuePerSecond);
        }
    }
    
    public static double[] maximizeRevenue(AdSlot[] slots, int totalAvailableTime) {

        Arrays.sort(slots);
        
        double[] allocations = new double[slots.length];
        int remainingTime = totalAvailableTime;
        
        for (int i = 0; i < slots.length; i++) {
            if (remainingTime >= slots[i].duration) {
                allocations[i] = 1.0;
                remainingTime -= slots[i].duration;
            } else {
                allocations[i] = (double) remainingTime / slots[i].duration;
                break;
            }
        }
        
        return allocations;
    }
    
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter the total available ad time (in seconds): ");
            int totalTime = scanner.nextInt();
            
            System.out.print("Enter the number of ad slots: ");
            int n = scanner.nextInt();
            
            AdSlot[] slots = new AdSlot[n];
            
            System.out.println("Enter the duration (seconds) and price ($) for each ad slot:");
            for (int i = 0; i < n; i++) {
                System.out.print("Ad Slot " + (i+1) + " duration: ");
                int duration = scanner.nextInt();
                
                System.out.print("Ad Slot " + (i+1) + " price: $");
                double price = scanner.nextDouble();
                
                slots[i] = new AdSlot(i+1, duration, price);
            }
            
            double[] allocations = maximizeRevenue(slots, totalTime);
            
            // Display the results
            System.out.println("\nOptimal Ad Slot Allocation:");
            System.out.println("---------------------------");
            System.out.printf("%-10s %-10s %-10s %-15s %-10s %-10s\n", 
                    "Slot ID", "Duration", "Price($)", "Value/Second", "Fraction", "Revenue");
            
            double totalRevenue = 0;
            for (int i = 0; i < slots.length; i++) {
                if (allocations[i] > 0) {
                    double revenue = slots[i].price * allocations[i];
                    totalRevenue += revenue;
                    
                    System.out.printf("%-10d %-10d %-10.2f %-15.2f %-10.2f %-10.2f\n", 
                            slots[i].id, slots[i].duration, slots[i].price, 
                            slots[i].valuePerSecond, allocations[i], revenue);
                }
            }
            
            System.out.println("\nTotal Revenue: $" + String.format("%.2f", totalRevenue));
        }
    }
}