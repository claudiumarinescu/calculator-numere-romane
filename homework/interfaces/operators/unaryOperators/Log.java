package homework.interfaces.operators.unaryOperators;

import homework.interfaces.operands.ArabicNumber;
import homework.interfaces.operands.IOperand;

public final class Log implements IUnaryOperator<Double> {

    private final int priority = 3;
    private String symb = "log";

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
    public IOperand<Double> calculate(final Double operand) {
        Double result = Math.log(operand);
        return new ArabicNumber(result);
    }

}
