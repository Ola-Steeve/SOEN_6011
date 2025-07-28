import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ArccosConsole extends JFrame {
    private JTextField inputField;
    private JTextArea resultArea;
    private JButton calculateButton, clearButton, quitButton;

    public ArccosConsole() {
        super("Arccos Calculator");
        FlatDarkLaf.install(); // Set dark theme

        // Set main window layout and default close operation
        setLayout(new BorderLayout(10, 10));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        // Input panel with label and field
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBackground(new Color(45, 45, 45));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel inputLabel = new JLabel("Enter a value (between -1 and 1):");
        inputLabel.setForeground(Color.WHITE);
        inputField = new JTextField(15);

        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(inputLabel, gbc);

        gbc.gridx = 1;
        inputPanel.add(inputField, gbc);

        // Button panel with Calculate, Clear, and Quit buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(45, 45, 45));

        calculateButton = new JButton("Calculate");
        clearButton = new JButton("Clear History");
        quitButton = new JButton("Quit");

        calculateButton.setBackground(new Color(100, 149, 237)); // Cornflower Blue
        clearButton.setBackground(new Color(70, 130, 180)); // Steel Blue
        quitButton.setBackground(new Color(220, 20, 60)); // Crimson

        calculateButton.setForeground(Color.WHITE);
        clearButton.setForeground(Color.WHITE);
        quitButton.setForeground(Color.WHITE);

        buttonPanel.add(calculateButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(quitButton);

        // Result area with scroll
        resultArea = new JTextArea(10, 40);
        resultArea.setEditable(false);
        resultArea.setBackground(new Color(30, 30, 30));
        resultArea.setForeground(Color.GREEN);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        // Button actions
        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = inputField.getText();
                try {
                    double x = Double.parseDouble(input);
                    if (x < -1 || x > 1) {
                        throw new IllegalArgumentException("Input must be between -1 and 1.");
                    }
                    double result = calculateArccos(x);
                    resultArea.append("arccos(" + x + ") = " + result + " radians\n");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Range Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resultArea.setText("");
            }
        });

        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    // Accurate arccos(x) using arccos(x) = π/2 – arcsin(x)
    public double calculateArccos(double x) {
        return Math.PI / 2 - calculateArcsin(x);
    }

    // arcsin(x) using Maclaurin series
    public double calculateArcsin(double x) {
        double sum = 0.0;
        int terms = 25;

        for (int n = 0; n < terms; n++) {
            double numerator = factorialDouble(2 * n) * Math.pow(x, 2 * n + 1);
            double denominator = Math.pow(4, n) * Math.pow(factorialDouble(n), 2) * (2 * n + 1);
            sum += numerator / denominator;
        }

        return sum;
    }

    private double factorialDouble(int n) {
        double result = 1.0;
        for (int i = 2; i <= n; i++) result *= i;
        return result;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ArccosConsole().setVisible(true);
        });
    }
}
