import javax.swing.*;  // Standard GUI components
import java.awt.*;     // AWT for basic UI
import java.awt.event.*; // Event handling

public class ArccosConsole extends JFrame {
    private JTextField inputField;
    private JTextArea resultArea;
    private JButton calculateButton;
    private JButton clearButton;
    private static final double PI = 3.141592653589793;

    public ArccosConsole() {
        setTitle("arccos(x) Calculator");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Input panel
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(new JLabel("Enter x (-1 ≤ x ≤ 1):"));
        inputField = new JTextField(15);
        inputPanel.add(inputField);

        // Button panel
        JPanel buttonPanel = new JPanel();
        calculateButton = new JButton("Calculate arccos");
        clearButton = new JButton("Clear History");
        buttonPanel.add(calculateButton);
        buttonPanel.add(clearButton);

        // Result area
        resultArea = new JTextArea(10, 40);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        // Add components to frame
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        // Event handlers
        calculateButton.addActionListener(e -> calculateArccos());
        clearButton.addActionListener(e -> resultArea.setText(""));
        inputField.addActionListener(e -> calculateArccos());
    }

    private void calculateArccos() {
        try {
            double x = parseDouble(inputField.getText());

            if (x < -1 || x > 1) {
                throw new IllegalArgumentException("x must be between -1 and 1");
            }

            double result = computeArccos(x);
            resultArea.append("arccos(" + x + ") = " + String.format("%.6f", result) + " radians\n");
            inputField.setText("");
        } catch (NumberFormatException e) {
            resultArea.append("Error: Invalid number format\n");
        } catch (IllegalArgumentException e) {
            resultArea.append("Error: " + e.getMessage() + "\n");
        }
    }

    private double parseDouble(String input) {
        boolean negative = false;
        String processed = input.trim();

        if (processed.startsWith("-")) {
            negative = true;
            processed = processed.substring(1);
        }

        String[] parts = processed.split("\\.");
        if (parts.length > 2) throw new NumberFormatException();

        // Parse integer part
        double value = 0;
        for (char c : parts[0].toCharArray()) {
            if (c < '0' || c > '9') throw new NumberFormatException();
            value = value * 10 + (c - '0');
        }

        // Parse fractional part
        if (parts.length == 2) {
            double fraction = 0;
            double divisor = 10;
            for (char c : parts[1].toCharArray()) {
                if (c < '0' || c > '9') throw new NumberFormatException();
                fraction += (c - '0') / divisor;
                divisor *= 10;
            }
            value += fraction;
        }

        return negative ? -value : value;
    }

    private double computeArccos(double x) {
        // Use bisection method
        double low = 0;
        double high = PI;
        double mid;
        double epsilon = 1e-10;

        while (high - low > epsilon) {
            mid = (low + high) / 2;
            double cosMid = computeCos(mid);

            if (cosMid > x) {
                low = mid;
            } else {
                high = mid;
            }
        }
        return (low + high) / 2;
    }

    private double computeCos(double angle) {
        // Reduce angle to [0, 2π]
        angle = angle % (2 * PI);
        if (angle < 0) angle += 2 * PI;

        // Taylor series for cosine
        double result = 1;
        double term = 1;
        double xSquared = angle * angle;
        int sign = -1;

        for (int n = 1; n <= 15; n++) {
            term = term * xSquared / ((2 * n) * (2 * n - 1));
            result += sign * term;
            sign *= -1;
        }
        return result;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ArccosConsole calculator = new ArccosConsole();
            calculator.setVisible(true);
        });
    }
}