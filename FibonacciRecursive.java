import java.util.Scanner;

public class FibonacciRecursive {
    public static void main(String[] args) {
        System.out.print("Enter an integer between 0 and 100 (inclusive): ");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.close();

        while (n < 0 || n >= 100) {
            System.out.print("Invalid input. Enter an integer between 0 and 100 (inclusive): ");
            n = scanner.nextInt();
        }

        printFibonacciSequence(n);
    }

    private static void printFibonacciSequence(int n) {
        if (n >= 0) {
            System.out.print(getFibonacciNumber(n) + " ");
        }

        if (n > 0) {
            printFibonacciSequence(n - 1);
        }
    }

    private static int getFibonacciNumber(int n) {
        return switch (n) {
            case 0 -> 0;
            case 1 -> 1;
            default -> getFibonacciNumber(n - 1) + getFibonacciNumber(n - 2);
        };
    }
}