package pr3;

public class Calculator {
    public double add(double a, double b) throws InvalidInputException {
        validateInput(a, b);
        return a + b;
    }

    public double subtract(double a, double b) throws InvalidInputException {
        validateInput(a, b);
        return a - b;
    }

    public double multiply(double a, double b) throws InvalidInputException {
        validateInput(a, b);
        return a * b;
    }

    public double divide(double a, double b) throws InvalidInputException {
        validateInput(a, b);
        if (b == 0) {
            throw new ArithmeticException("Ділення на нуль неможливе");
        }

        return a / b;
    }

    public double sqrt(double a) throws InvalidInputException {
        if (a < 0) {
            throw new InvalidInputException("Неможливо вичислити квадратний корінь з негативного числа");
        }

        return Math.sqrt(a);
    }

    private void validateInput(double a, double b) throws InvalidInputException {
        if (Double.isNaN(a) || Double.isNaN(b)) {
            throw new InvalidInputException("Вхідні значення не можуть бути NaN");
        }
        if (Double.isInfinite(a) || Double.isInfinite(b)) {
            throw new InvalidInputException("Вхідні значення не можуть бути бескінечністю");
        }
    }
}
