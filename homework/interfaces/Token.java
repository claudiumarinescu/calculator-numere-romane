package homework.interfaces;


public final class Token implements IToken {

    private String symb = "";

    public Token(final String symbol) {
        this.symb = symbol;
    }

    @Override
    public String getSymbol() {
        return this.symb;
    }

    @Override
    public void setSymbol(final String symbol) {
        this.symb = symbol;
    }

}
