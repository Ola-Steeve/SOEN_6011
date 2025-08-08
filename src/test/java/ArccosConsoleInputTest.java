import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import static org.junit.jupiter.api.Assertions.*;

class ArccosConsoleInputTest {
  private ArccosConsole calculator;
  private Field inputField;
  private Field resultArea;
  private Method handleCalculate;

  @BeforeEach
  void setUp() throws Exception {
    calculator = new ArccosConsole();

    // Get private fields via reflection
    inputField = ArccosConsole.class.getDeclaredField("inputField");
    inputField.setAccessible(true);

    resultArea = ArccosConsole.class.getDeclaredField("resultArea");
    resultArea.setAccessible(true);

    // Get private method via reflection
    handleCalculate = ArccosConsole.class.getDeclaredMethod("handleCalculate");
    handleCalculate.setAccessible(true);
  }

  @Test
  void handleCalculate_withValidInput_appendsResult() throws Exception {
    // Set input text
    ((JTextField)inputField.get(calculator)).setText("0.5");

    // Invoke private method
    handleCalculate.invoke(calculator);

    // Verify result
    String output = ((JTextArea)resultArea.get(calculator)).getText();
    assertTrue(output.contains("arccos(0.5)"));
  }

  @Test
  void handleCalculate_withInvalidNumber_showsError() throws Exception {
    ((JTextField)inputField.get(calculator)).setText("abc");
    handleCalculate.invoke(calculator);

    // Verify no output was added
    String output = ((JTextArea)resultArea.get(calculator)).getText();
    assertTrue(output.isEmpty());
  }

  @Test
  void handleCalculate_withOutOfRangeNumber_showsError() throws Exception {
    // Test upper bound
    ((JTextField)inputField.get(calculator)).setText("1.1");
    handleCalculate.invoke(calculator);
    assertTrue(((JTextArea)resultArea.get(calculator)).getText().isEmpty());

    // Test lower bound
    ((JTextField)inputField.get(calculator)).setText("-1.1");
    handleCalculate.invoke(calculator);
    assertTrue(((JTextArea)resultArea.get(calculator)).getText().isEmpty());
  }
}