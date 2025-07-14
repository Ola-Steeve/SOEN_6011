import java.util.Scanner;

/**
 * Simple console application that computes arccos(x) with
 * full input validation and error handling.
 *
 * Author: Olaoluwa Ekundayo | SOEN 6011 – D1 (Function 1)
 */
public class ArccosConsole {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Inverse Cosine Calculator (arccos) ===");
        System.out.println("Enter a value for x in the range [-1, 1]");
        System.out.println("Type 'quit' to exit.\n");

        while (true) {
            System.out.print("x = ");
            String token = scanner.nextLine().trim();

            // -----exit ------------------------------------------
            if (token.equalsIgnoreCase("quit")) {
                System.out.println("Goodbye!");
                break;
            }

            // ----- numeric check ------------------------------------------
            double x;
            try {
                x = Double.parseDouble(token);
            } catch (NumberFormatException e) {
                System.err.println("Error: please enter a numeric value.");
                continue; // prompt again
            }

            // ----- domain check -------------------------------------------
            if (x < -1.0 || x > 1.0) {
                System.err.println("Error: input out of domain (‑1 ≤ x ≤ 1).");
                continue; // prompt again
            }

            // ----- computation -------------------------------------------
            double radians = Math.acos(x);
            System.out.printf("arccos(%f) = %.6f radians%n%n", x, radians);
        }

        scanner.close();
    }
}
