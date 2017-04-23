package homework.interfaces.brackets;

public final class Bracket implements IBracket {

    private String symb = "";

    public Bracket(final String symbol) {
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
