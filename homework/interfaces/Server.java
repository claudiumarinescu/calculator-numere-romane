package homework.interfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import homework.interfaces.brackets.BracketsFactory;
import homework.interfaces.brackets.Bracket;
import homework.interfaces.operators.OperatorsFactory;
import homework.interfaces.operators.IOperator;
import homework.interfaces.operators.unaryOperators.Log;
import homework.interfaces.operators.unaryOperators.Sqrt;
import homework.interfaces.operators.binaryOperators.Add;
import homework.interfaces.operators.binaryOperators.Subtract;
import homework.interfaces.operators.binaryOperators.Multiply;
import homework.interfaces.operators.binaryOperators.Divide;
import homework.interfaces.operators.binaryOperators.Exponent;
import homework.interfaces.operands.ArabicNumber;
import homework.interfaces.operands.RomanNumber;
import homework.interfaces.operands.IOperand;


public final class Server implements IServer {

    private static Server instance = null;
    private final String illegal = "IMPOSSIBRU";
    private Stack<IToken> operatorsStack = null;
    private List<String> results = null;

    private Server() {
        operatorsStack = new Stack<IToken>();
        results = new ArrayList<String>();
    }

    public static Server getInstance() {
        if (instance == null) {
            instance = new Server();
        }
        return instance;
    }

    @Override
    public boolean canPublish(final String[] tokens) {
        boolean found = false;
        for (String s : tokens) {
            Token t = new Token(s);
            if (OperatorsFactory.getInstance().isOperator(t)) {
                found = false;
                for (IToken op : operatorsStack) {
                    if (s.equals(op.getSymbol())) {
                        found = true;
                    }
                }
                if (!found) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void publish(final String command) {
        String[] tokens = command.split(" ");
        if (!this.canPublish(tokens)) {
            this.results.add(this.illegal);
            return;
        }

        List<IToken> polishTokens = this.toPolish(tokens);

        ArabicNumber result = this.calculatePolish(polishTokens);

        if (result == null) {
            this.results.add(this.illegal);
        } else {
            String p = result.convertToRomanNumber().getSymbol();
            this.results.add(p);
        }
    }

    @Override
    public void subscribe(final String operator) {
        IOperator op = OperatorsFactory.getInstance().createOperator(operator);
        operatorsStack.push(op);
    }

    @Override
    public List<String> getResults() {
        return this.results;
    }

    public List<IToken> toPolish(final String[] tokens) {
        List<IToken> result = new ArrayList<IToken>();
        Stack<IToken> stack = new Stack<IToken>();

        for (String s : tokens) {
            IToken temp = new Token(s);
            // Daca elementul este o paranteza
            if (BracketsFactory.getInstance().isBracket(temp)) {
                Bracket br = new Bracket(temp.getSymbol());
                if (BracketsFactory.getInstance().isOpenedBracket(br)) {
                    stack.push(br);
                    continue;
                } else {
                    IToken t = stack.pop();
                    while (!BracketsFactory.getInstance().isBracket(t)
                        || !BracketsFactory.getInstance().isOpenedBracket(
                            (Bracket) t)) {
                        result.add(t);
                        t = stack.pop();
                    }
                    continue;
                }
            }
            // Daca elementul este operator
            if (OperatorsFactory.getInstance().isOperator(temp)) {
                IOperator op = OperatorsFactory.getInstance().
                                createOperator(temp.getSymbol());
                if (stack.isEmpty()) {
                    stack.push(op);
                    continue;
                }
                IToken vertice = stack.peek();
                while (vertice != null
                    && OperatorsFactory.getInstance().isOperator(vertice)
                        && op.getPriority()
                        <= ((IOperator) vertice).getPriority()) {
                    result.add(stack.pop());
                    if (stack.isEmpty()) {
                        break;
                    }
                    vertice = stack.peek();
                }
                stack.push(op);
                continue;
            }
            // Daca elementul este un numar
            RomanNumber rom = new RomanNumber(temp.getSymbol());
            result.add(((ArabicNumber) rom.convertToArabicNumber()));
        }

        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }

        return result;
    }

    public ArabicNumber calculatePolish(final List<IToken> tokens) {

        Stack<IToken> stack = new Stack<IToken>();

        for (IToken t : tokens) {
            if (!OperatorsFactory.getInstance().isOperator(t)) {
                stack.push(t);
                continue;
            }
            // Is UNARY operator
            if (OperatorsFactory.getInstance().isUnaryOperator((IOperator) t)) {
                IOperand temp = (IOperand) stack.pop();
                if (t.getSymbol().equals("log")) {
                    Log op = (Log) ((IOperator) t);
                    ArabicNumber d = (ArabicNumber) op.calculate(
                            ((ArabicNumber) temp).getSymbolValue());
                    stack.push(d);
                    continue;
                }
                if (t.getSymbol().equals("sqrt")) {
                    Sqrt op = (Sqrt) ((IOperator) t);
                    ArabicNumber d = (ArabicNumber) op.calculate(
                            ((ArabicNumber) temp).getSymbolValue());
                    stack.push(d);
                    continue;
                }
            }

            //Is BINARY operator
            IOperand temp2 = (IOperand) stack.pop();
            IOperand temp1 = (IOperand) stack.pop();

            if (t.getSymbol().equals("+")) {
                Add op = (Add) ((IOperator) t);
                ArabicNumber d = (ArabicNumber) op.calculate(
                        ((ArabicNumber) temp1).getSymbolValue(),
                        ((ArabicNumber) temp2).getSymbolValue());
                stack.push(d);
                continue;
            }
            if (t.getSymbol().equals("-")) {
                Subtract op = (Subtract) ((IOperator) t);
                ArabicNumber d = (ArabicNumber) op.calculate(
                        ((ArabicNumber) temp1).getSymbolValue(),
                        ((ArabicNumber) temp2).getSymbolValue());
                stack.push(d);
                continue;
            }
            if (t.getSymbol().equals("*")) {
                Multiply op = (Multiply) ((IOperator) t);
                ArabicNumber d = (ArabicNumber) op.calculate(
                        ((ArabicNumber) temp1).getSymbolValue(),
                        ((ArabicNumber) temp2).getSymbolValue());
                stack.push(d);
                continue;
            }
            if (t.getSymbol().equals("/")) {
                Divide op = (Divide) ((IOperator) t);
                if (((ArabicNumber) temp2).getSymbolValue() == 0) {
                    return null;
                }
                ArabicNumber d = (ArabicNumber) op.calculate(
                        ((ArabicNumber) temp1).getSymbolValue(),
                        ((ArabicNumber) temp2).getSymbolValue());
                stack.push(d);
                continue;
            }
            if (t.getSymbol().equals("^")) {
                Exponent op = (Exponent) ((IOperator) t);
                ArabicNumber d = (ArabicNumber) op.calculate(
                        ((ArabicNumber) temp1).getSymbolValue(),
                        ((ArabicNumber) temp2).getSymbolValue());
                stack.push(d);
                continue;
            }
        }
        return ((ArabicNumber) stack.pop());
    }
}
