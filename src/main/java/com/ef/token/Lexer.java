package com.ef.token;

import java.util.ArrayList;
import java.util.List;

public class Lexer {
    private final String input;
    private int pos;
    private final int length;

    public Lexer(String input) {
        this.input = input;
        this.pos = 0;
        this.length = input.length();
    }

    public List<Token> tokenize() {
        List<Token> tokens = new ArrayList<>();
        while (pos < length) {
            char current = input.charAt(pos);
            switch (current) {
                case ' ':
                case '\t':
                case '\r':
                case '\n':
                    pos++;
                    break;
                case '+':
                    tokens.add(new Token(Token.Type.PLUS, "+"));
                    pos++;
                    break;
                case '-':
                    tokens.add(new Token(Token.Type.MINUS, "-"));
                    pos++;
                    break;
                case '*':
                    tokens.add(new Token(Token.Type.MULTIPLY, "*"));
                    pos++;
                    break;
                case '/':
                    tokens.add(new Token(Token.Type.DIVIDE, "/"));
                    pos++;
                    break;
                case '%':
                    tokens.add(new Token(Token.Type.MODULO, "%"));
                    pos++;
                    break;
                case '^':
                    tokens.add(new Token(Token.Type.POWER, "^"));
                    pos++;
                    break;
                case '(':
                    tokens.add(new Token(Token.Type.LPAREN, "("));
                    pos++;
                    break;
                case ')':
                    tokens.add(new Token(Token.Type.RPAREN, ")"));
                    pos++;
                    break;
                default:
                    if (Character.isDigit(current) || current == '.') {
                        StringBuilder number = new StringBuilder();
                        boolean hasDot = false;
                        while (pos < length && (Character.isDigit(input.charAt(pos))
                                || (!hasDot && input.charAt(pos) == '.'))) {
                            if (input.charAt(pos) == '.') hasDot = true;
                            number.append(input.charAt(pos));
                            pos++;
                        }
                        tokens.add(new Token(Token.Type.NUMBER, number.toString()));
                    } else if (Character.isLetter(current)) {
                        StringBuilder identifier = new StringBuilder();
                        while (pos < length && Character.isLetter(input.charAt(pos))) {
                            identifier.append(input.charAt(pos));
                            pos++;
                        }
                        tokens.add(new Token(Token.Type.IDENTIFIER, identifier.toString()));
                    } else {
                        throw new RuntimeException("Unexpected character: " + current);
                    }
                    break;
            }
        }
        return tokens;
    }
}
