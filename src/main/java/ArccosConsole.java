import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLaf;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/** Arccos Calculator GUI application. */
public class ArccosConsole extends JFrame {
  private static final double PI = 3.141592653589793;
  private final JTextField inputField;
  private final JTextArea resultArea;

  /** Constructs the Arccos Calculator GUI. */
  public ArccosConsole() {
    super("Arccos Calculator");
    FlatLaf.setup(new FlatDarkLaf()); // GUI Theme

    setLayout(new BorderLayout(10, 10));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(600, 400);
    setLocationRelativeTo(null);

    // Initialize components
    inputField = new JTextField(15);
    resultArea = new JTextArea(10, 40);
    initializeGui();
  }

  /** Initializes the GUI components and layout. */
  private void initializeGui() {
    // Input Panel
    JPanel inputPanel = new JPanel(new GridBagLayout());
    inputPanel.setBackground(new Color(45, 45, 45));
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 5, 5, 5);

    JLabel inputLabel = new JLabel("Enter a value (between -1 and 1):");
    inputLabel.setForeground(Color.WHITE);
    // Accessibility for label
    inputLabel.getAccessibleContext().setAccessibleName("Input Label");
    inputLabel
        .getAccessibleContext()
        .setAccessibleDescription("Label prompting user to enter a number between -1 and 1");

    gbc.gridx = 0;
    gbc.gridy = 0;
    inputPanel.add(inputLabel, gbc);

    gbc.gridx = 1;
    inputPanel.add(inputField, gbc);
    // Accessibility for input field
    inputField.getAccessibleContext().setAccessibleName("Input Field");
    inputField
        .getAccessibleContext()
        .setAccessibleDescription("Text field for entering a numeric value between -1 and 1");

    // Button Panel
    JPanel buttonPanel = new JPanel();
    buttonPanel.setBackground(new Color(45, 45, 45));

    // Buttons are now local to this method (fixes SingularField PMD warning)
    final JButton calculateButton = createButton("Calculate", new Color(100, 149, 237));
    final JButton clearButton = createButton("Clear History", new Color(70, 130, 180));
    final JButton quitButton = createButton("Quit", new Color(220, 20, 60));

    // Accessibility and mnemonics for buttons
    calculateButton.getAccessibleContext().setAccessibleName("Calculate Button");
    calculateButton
        .getAccessibleContext()
        .setAccessibleDescription("Button to calculate the arccosine of the input value");
    calculateButton.setMnemonic('C'); // Alt+C

    clearButton.getAccessibleContext().setAccessibleName("Clear History Button");
    clearButton
        .getAccessibleContext()
        .setAccessibleDescription("Button to clear the calculation history");
    clearButton.setMnemonic('H'); // Alt+H

    quitButton.getAccessibleContext().setAccessibleName("Quit Button");
    quitButton.getAccessibleContext().setAccessibleDescription("Button to quit the application");
    quitButton.setMnemonic('Q'); // Alt+Q

    buttonPanel.add(calculateButton);
    buttonPanel.add(clearButton);
    buttonPanel.add(quitButton);

    // Result Area
    resultArea.setEditable(false);
    resultArea.setBackground(new Color(30, 30, 30));
    resultArea.setForeground(Color.GREEN);
    // Accessibility for result area
    resultArea.getAccessibleContext().setAccessibleName("Results Area");
    resultArea
        .getAccessibleContext()
        .setAccessibleDescription("Text area displaying calculation results and history");
    JScrollPane scrollPane = new JScrollPane(resultArea);

    // Add components to frame
    add(inputPanel, BorderLayout.NORTH);
    add(buttonPanel, BorderLayout.CENTER);
    add(scrollPane, BorderLayout.SOUTH);

    // Set up button actions
    setupButtonActions(calculateButton, clearButton, quitButton);
  }

  /**
   * Creates a styled button with consistent properties.
   *
   * @param text the button text
   * @param bgColor the background color
   * @return the configured JButton
   */
  private JButton createButton(String text, Color bgColor) {
    JButton button = new JButton(text);
    button.setBackground(bgColor);
    button.setForeground(Color.WHITE);
    return button;
  }

  /** Sets up action listeners for all buttons. */
  private void setupButtonActions(
      JButton calculateButton, JButton clearButton, JButton quitButton) {
    calculateButton.addActionListener(
        new ActionListener() {
          @Override // Added @Override annotation
          public void actionPerformed(ActionEvent e) {
            handleCalculate();
          }
        });

    clearButton.addActionListener(
        new ActionListener() {
          @Override // Added @Override annotation
          public void actionPerformed(ActionEvent e) {
            resultArea.setText("");
          }
        });

    quitButton.addActionListener(
        new ActionListener() {
          @Override // Added @Override annotation
          public void actionPerformed(ActionEvent e) {
            System.exit(0);
          }
        });
  }

  /** Handles the calculate button action. */
  private void handleCalculate() {
    String input = inputField.getText();
    try {
      double x = Double.parseDouble(input);
      if (x < -1 || x > 1) {
        throw new IllegalArgumentException("Input must be between -1 and 1.");
      }
      double result = calculateArccos(x);
      resultArea.append("arccos(" + x + ") = " + result + " radians\n");
    } catch (NumberFormatException ex) {
      JOptionPane.showMessageDialog(
          null, "Please enter a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
    } catch (IllegalArgumentException ex) {
      JOptionPane.showMessageDialog(
          null, ex.getMessage(), "Range Error", JOptionPane.ERROR_MESSAGE);
    }
  }

  /**
   * Calculates arccosine using the identity arccos(x) = π/2 - arcsin(x).
   *
   * @param x the input value between -1 and 1
   * @return the arccosine of x in radians
   */
  public double calculateArccos(double x) {
    return PI / 2 - calculateArcsin(x);
  }

  /**
   * Calculates arcsine using Maclaurin series expansion.
   *
   * @param x the input value between -1 and 1
   * @return the arcsine of x in radians
   */
  public double calculateArcsin(double x) {
    double sum = 0.0;
    final int terms = 25;

    for (int n = 0; n < terms; n++) {
      double numerator = factorialDouble(2 * n) * power(x, 2 * n + 1);
      double denominator = power(4, n) * power(factorialDouble(n), 2) * (2 * n + 1);
      sum += numerator / denominator;
    }

    return sum;
  }

  /**
   * Calculates power of a number.
   *
   * @param base the base number
   * @param exponent the exponent
   * @return base raised to the power of exponent
   */
  private double power(double base, int exponent) {
    double result = 1.0;
    for (int i = 0; i < exponent; i++) {
      result *= base;
    }
    return result;
  }

  /**
   * Calculates factorial of a number.
   *
   * @param n the input number
   * @return factorial of n as double
   */
  private double factorialDouble(int n) {
    double result = 1.0;
    for (int i = 2; i <= n; i++) {
      result *= i;
    }
    return result;
  }

  /**
   * Main method to launch the application.
   *
   * @param args command line arguments
   */
  public static void main(String[] args) {
    SwingUtilities.invokeLater(
        new Runnable() {
          @Override
          public void run() {
            new ArccosConsole().setVisible(true);
          }
        });
  }
}
