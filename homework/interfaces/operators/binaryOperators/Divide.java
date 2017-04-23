package homework.interfaces.operators.binaryOperators;

import homework.interfaces.operands.ArabicNumber;
import homework.interfaces.operands.IOperand;

public final class Divide implements IBinaryOperator<Double> {

    private final int priority = 1;
    private String symb = "/";

    @Override
    public int getPriority() {
        return this.priority;
    }

    @Override
    public String getSymbol() {
        return this.symb;
    }

    @Override
    public void setSymbol(final String symbol) {
        this.symb = symbol;
    }

    @Override
    public IOperand<Double> calculate(final Double leftOperand,
            final Double rightOperand) {
        Double result = leftOperand / rightOperand;
        return new ArabicNumber(result);
    }

}
