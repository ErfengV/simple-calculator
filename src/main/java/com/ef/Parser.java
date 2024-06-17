package com.ef;
import com.ef.token.Token;

import java.util.List;

class Parser {
    private final List<Token> tokens;
    private int pos;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        this.pos = 0;
    }

    private Token consume(Token.Type type) {
        if (pos < tokens.size() && tokens.get(pos).type == type) {
            return tokens.get(pos++);
        }
        throw new RuntimeException("Expected token of type " + type + " but found " + tokens.get(pos).type);
    }

    private Token lookAhead() {
        if (pos < tokens.size()) {
            return tokens.get(pos);
        }
        return null;
    }

    public double parse() {
        return parseExpression();
    }

    private double parseExpression() {
        double result = parseTerm();
        while (lookAhead() != null && (lookAhead().type == Token.Type.PLUS
                || lookAhead().type == Token.Type.MINUS)) {
            if (lookAhead().type == Token.Type.PLUS) {
                consume(Token.Type.PLUS);
                result += parseTerm();
            } else if (lookAhead().type == Token.Type.MINUS) {
                consume(Token.Type.MINUS);
                result -= parseTerm();
            }
        }
        return result;
    }

    private double parseTerm() {
        double result = parseFactor();
        while (lookAhead() != null && (lookAhead().type == Token.Type.MULTIPLY
                || lookAhead().type == Token.Type.DIVIDE
                || lookAhead().type == Token.Type.MODULO)) {
            if (lookAhead().type == Token.Type.MULTIPLY) {
                consume(Token.Type.MULTIPLY);
                result *= parseFactor();
            } else if (lookAhead().type == Token.Type.DIVIDE) {
                consume(Token.Type.DIVIDE);
                result /= parseFactor();
            } else if (lookAhead().type == Token.Type.MODULO) {
                consume(Token.Type.MODULO);
                result %= parseFactor();
            }
        }
        return result;
    }

    private double parseFactor() {
        double result = parseBase();
        while (lookAhead() != null && lookAhead().type == Token.Type.POWER) {
            consume(Token.Type.POWER);
            result = Math.pow(result, parseBase());
        }
        return result;
    }

    private double parseBase() {
        if (lookAhead().type == Token.Type.NUMBER) {
            return Double.parseDouble(consume(Token.Type.NUMBER).text);
        } else if (lookAhead().type == Token.Type.MINUS) {
            consume(Token.Type.MINUS);
            return -parseBase();
        } else if (lookAhead().type == Token.Type.LPAREN) {
            consume(Token.Type.LPAREN);
            double result = parseExpression();
            consume(Token.Type.RPAREN);
            return result;
        } else if (lookAhead().type == Token.Type.IDENTIFIER) {
            String functionName = consume(Token.Type.IDENTIFIER).text;
            consume(Token.Type.LPAREN);
            double argument = parseExpression();
            consume(Token.Type.RPAREN);
            return evaluateFunction(functionName, argument);
        } else {
            throw new RuntimeException("Expected number, '(', '-' or function but found " + lookAhead().type);
        }
    }

    private double evaluateFunction(String functionName, double argument) {
        switch (functionName) {
            case "sin":
                return Math.sin(argument);
            case "cos":
                return Math.cos(argument);
            case "tan":
                return Math.tan(argument);
            case "log":
                return Math.log(argument);
            case "sqrt":
                return Math.sqrt(argument);
            case "exp":
                return Math.exp(argument);
            default:
                throw new RuntimeException("Unknown function: " + functionName);
        }
    }
}
