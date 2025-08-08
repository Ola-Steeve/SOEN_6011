import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.lang.reflect.Method;
import static org.junit.jupiter.api.Assertions.*;
import static java.lang.Math.*;

class ArccosConsoleMathTest {
  private static final double DELTA = 1e-10;
  private ArccosConsole calculator;
  private Method powerMethod;
  private Method factorialMethod;

  @BeforeEach
  void setUp() throws Exception {
    calculator = new ArccosConsole();

    // Get private methods via reflection
    powerMethod = ArccosConsole.class.getDeclaredMethod("power", double.class, int.class);
    powerMethod.setAccessible(true);

    factorialMethod = ArccosConsole.class.getDeclaredMethod("factorialDouble", int.class);
    factorialMethod.setAccessible(true);
  }

  // Helper methods to invoke private methods
  private double invokePower(double base, int exponent) throws Exception {
    return (double) powerMethod.invoke(calculator, base, exponent);
  }

  private double invokeFactorial(int n) throws Exception {
    return (double) factorialMethod.invoke(calculator, n);
  }

  @Test
  void calculateArcsin_withZero_returnsZero() {
    assertEquals(0.0, calculator.calculateArcsin(0), DELTA);
  }

  @Test
  void calculateArcsin_withOne_returnsPiOverTwo() {
    assertEquals(PI/2, calculator.calculateArcsin(1), DELTA);
  }

  @Test
  void calculateArccos_withHalf_returnsCorrectValue() {
    double expected = 1.0471975511965979; // π/3
    assertEquals(expected, calculator.calculateArccos(0.5), DELTA);
  }

  @Test
  void power_withPositiveExponent_returnsCorrectValue() throws Exception {
    assertEquals(8.0, invokePower(2, 3), DELTA);
    assertEquals(1.0, invokePower(5, 0), DELTA);
  }

  @Test
  void factorialDouble_withPositiveNumber_returnsCorrectValue() throws Exception {
    assertEquals(120.0, invokeFactorial(5), DELTA);
    assertEquals(1.0, invokeFactorial(0), DELTA);
  }
}