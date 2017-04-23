package homework.interfaces.operators;

import homework.interfaces.IToken;
import homework.interfaces.operators.binaryOperators.Add;
import homework.interfaces.operators.binaryOperators.Subtract;
import homework.interfaces.operators.binaryOperators.Multiply;
import homework.interfaces.operators.binaryOperators.Divide;
import homework.interfaces.operators.binaryOperators.Exponent;
import homework.interfaces.operators.unaryOperators.Log;
import homework.interfaces.operators.unaryOperators.Sqrt;

public final class OperatorsFactory implements IOperatorsFactory {

    private static OperatorsFactory instance = null;
    private final int three = 3;

    private OperatorsFactory() { }

    public static OperatorsFactory getInstance() {
        if (instance == null) {
            instance = new OperatorsFactory();
        }
        return instance;
    }

    @Override
    public IOperator createOperator(final String token) {
        switch (token) {
            case "+":
                return new Add();
            case "-":
                return new Subtract();
            case "*":
                return new Multiply();
            case "/":
                return new Divide();
            case "^":
                return new Exponent();
            case "log":
                return new Log();
            case "sqrt":
                return new Sqrt();
            default:
        }
        return null;
    }

    @Override
    public boolean isOperator(final IToken token) {
        final String[] operators = {"+", "-", "*", "/",
                "^", "*", "log", "sqrt"};
        for (String s : operators) {
            if (s.equals(token.getSymbol())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isUnaryOperator(final IOperator operator) {
        if (operator.getPriority() == this.three) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isBinaryOperator(final IOperator operator) {
        if (!this.isUnaryOperator(operator)) {
            return true;
        }
        return false;
    }

}
