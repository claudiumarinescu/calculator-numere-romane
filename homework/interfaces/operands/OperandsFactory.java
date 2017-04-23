package homework.interfaces.operands;

public final class OperandsFactory implements IOperandsFactory<Double> {

    private static OperandsFactory instance = null;

    private OperandsFactory() {    }

    public static OperandsFactory getInstance() {
        if (instance == null) {
            instance = new OperandsFactory();
        }
        return instance;
    }

    @Override
    public IOperand<Double> createOperand(final String token) {
        return new RomanNumber(token);
    }

    @Override
    public IOperand<Double> convertToRomanNumber(final Double arabNumber) {
        ArabicNumber a = new ArabicNumber(arabNumber);
        return a.convertToRomanNumber();
    }

    @Override
    public IOperand<Double> convertToArabNumber(final String romanNumber) {
        RomanNumber r = new RomanNumber(romanNumber);
        return r.convertToArabicNumber();
    }

}
