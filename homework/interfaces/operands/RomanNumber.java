package homework.interfaces.operands;

public final class RomanNumber implements IOperand<Double> {

    private final int uzzz = 1000;
    private final int nzz = 900;
    private final int czz = 500;
    private final int pzz = 400;
    private final int uzz = 100;
    private final int nz = 90;
    private final int cz = 50;
    private final int pz = 40;
    private final int uz = 10;
    private final int n = 9;
    private final int c = 5;
    private final int p = 4;
    private String symb = "";

    public RomanNumber(final String token) {
        this.symb = token;
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
    public Double getSymbolValue() {
        return (double) Integer.parseInt(this.symb);
    }

    @Override
    public void setSymbolValue(final Double value) {
        this.symb = value.toString();
    }

    public IOperand<Double> convertToArabicNumber() {
        return new ArabicNumber((double) this.toArabic(this.symb));
    }

    public int toArabic(final String number) {
        if (number == "") {
            return 0;
        }
        if (number.startsWith("M")) {
            return this.uzzz + toArabic(number.substring(1));
        }
        if (number.startsWith("CM")) {
            return this.nzz + toArabic(number.substring(2));
        }
        if (number.startsWith("D")) {
            return this.czz + toArabic(number.substring(1));
        }
        if (number.startsWith("CD")) {
            return this.pzz + toArabic(number.substring(2));
        }
        if (number.startsWith("C")) {
            return this.uzz + toArabic(number.substring(1));
        }
        if (number.startsWith("XC")) {
            return this.nz + toArabic(number.substring(2));
        }
        if (number.startsWith("L")) {
            return this.cz + toArabic(number.substring(1));
        }
        if (number.startsWith("XL")) {
            return this.pz + toArabic(number.substring(2));
        }
        if (number.startsWith("X")) {
            return this.uz + toArabic(number.substring(1));
        }
        if (number.startsWith("IX")) {
            return this.n + toArabic(number.substring(2));
        }
        if (number.startsWith("V")) {
            return this.c + toArabic(number.substring(1));
        }
        if (number.startsWith("IV")) {
            return this.p + toArabic(number.substring(2));
        }
        if (number.startsWith("I")) {
            return 1 + toArabic(number.substring(1));
        }
        return 0;
    }

}
