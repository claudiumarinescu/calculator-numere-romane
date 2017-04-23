package homework.interfaces.operands;

public final class ArabicNumber implements IOperand<Double> {

    private Double symb = (double) 0;

    public ArabicNumber(final Double number) {
        this.symb = number;
    }

    @Override
    public String getSymbol() {
        return this.symb.toString();
    }

    @Override
    public void setSymbol(final String symbol) {
        this.symb = (double) Integer.parseInt(symbol);
    }

    @Override
    public Double getSymbolValue() {
        return this.symb;
    }

    @Override
    public void setSymbolValue(final Double value) {
        this.symb = value;
    }

    public IOperand<Double> convertToRomanNumber() {

        final int[] numbers = {1000, 900, 500, 400, 100, 90,
                50, 40, 10, 9, 5, 4, 1 };
        final String[] letters = {"M",  "CM",  "D",  "CD", "C",  "XC",
                "L",  "XL",  "X",  "IX", "V",  "IV", "I" };

        String roman = "";
        int num = (int) Math.floor(this.symb);
        if (num < 0) {
            roman += "- ";
            num *= -1;
        }

        for (int i = 0; i < numbers.length; i++) {
            while (num >= numbers[i]) {
                roman += letters[i];
                num -= numbers[i];
            }
        }
        return new RomanNumber(roman);
    }

}
