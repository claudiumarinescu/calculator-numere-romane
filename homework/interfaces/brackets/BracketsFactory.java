package homework.interfaces.brackets;

import homework.interfaces.IToken;

public final class BracketsFactory implements IBracketsFactory {

    private static BracketsFactory instance = null;

    private BracketsFactory() {    }

    public static BracketsFactory getInstance() {
        if (instance == null) {
            instance = new BracketsFactory();
        }
        return instance;
    }

    @Override
    public IBracket createBracket(final String token) {
        Bracket bracket = new Bracket(token);
        return bracket;
    }

    @Override
    public boolean isBracket(final IToken token) {
        switch (token.getSymbol()) {
            case "(":
                return true;
            case ")":
                return true;
            case "[":
                return true;
            case "]":
                return true;
            case "{":
                return true;
            case "}":
                return true;
            default:
        }
        return false;
    }

    @Override
    public boolean isOpenedBracket(final IBracket bracket) {
        switch (bracket.getSymbol()) {
            case "(":
                return true;
            case "[":
                return true;
            case "{":
                return true;
            default:
        }
        return false;
    }

    @Override
    public boolean isClosedBracket(final IBracket bracket) {
        switch (bracket.getSymbol()) {
            case ")":
                return true;
            case "]":
                return true;
            case "}":
                return true;
            default:
        }
        return false;
    }

    @Override
    public boolean isBracketPair(final IBracket openBracket,
            final IBracket closeBracket) {
        if (this.isOpenedBracket(openBracket)
                && this.isClosedBracket(closeBracket)) {
            return true;
        }
        return false;
    }

}
