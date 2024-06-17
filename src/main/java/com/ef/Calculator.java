package com.ef;

import com.ef.token.Lexer;
import com.ef.token.Token;

import java.util.List;
import java.util.Scanner;

/**
 * @Author：erfeng
 */
public class Calculator {
    public static void main(String[] args) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter an expression: ");
            String input = scanner.nextLine();
            if("end".equals(input)){
               return;
            }
            Lexer lexer = new Lexer(input);
            List<Token> tokens = lexer.tokenize();

            Parser parser = new Parser(tokens);
            double result = parser.parse();

            System.out.println("Result: " + result);
        }
    }
}

