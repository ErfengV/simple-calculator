package com.ef.token;

public class Token {
    public enum Type { NUMBER, PLUS, MINUS, MULTIPLY, DIVIDE, MODULO, POWER, LPAREN, RPAREN, IDENTIFIER }
    public Type type;
    public String text;

    public Token(Type type, String text) {
        this.type = type;
        this.text = text;
    }

    @Override
    public String toString() {
        return String.format("Token(%s, \"%s\")", type.name(), text);
    }
}

