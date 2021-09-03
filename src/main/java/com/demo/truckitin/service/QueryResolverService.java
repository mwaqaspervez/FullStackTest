package com.demo.truckitin.service;

import com.demo.truckitin.model.QueryResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Stack;

/**
 * Services response to handle the conversion of infix equation notation
 * to postfix notation.
 */
@Service
public class QueryResolverService {
    private static final List<Character> operatorList = List.of('+', '-', '*', '/');

    /**
     * Resolves and returns the requested infix notation to postfix notation.
     * Throws an error if query is invalid.
     *
     * @param equation The infix equation that needs to be resolved.
     * @return {@link QueryResponse} Object containing the postfix notation
     * and calculated result of the notation.
     */
    public QueryResponse resolve(String equation) {
        // initializing empty String for result
        StringBuilder result = new StringBuilder();

        // initializing empty stack
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < equation.length(); ++i) {
            char current = equation.charAt(i);

            if (Character.isSpaceChar(current)) {
                continue;
            }

            // If the scanned character is an
            // operand, add it to output.
            if (Character.isDigit(current))
                result.append(current);

                // If the scanned character is an '(',
                // push it to the stack.
            else if (current == '(') {
                stack.push(current);
            }

            //  If the scanned character is an ')',
            // pop and output from the stack
            // until an '(' is encountered.
            else if (current == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append(stack.pop());
                }

                stack.pop();
            } else if(operatorList.contains(current)) {
                // an operator is encountered
                while (!stack.isEmpty() && precedence(current) <= precedence(stack.peek())) {
                    result.append(stack.pop());
                }
                stack.push(current);
            } else {
                throw new UnsupportedOperationException(current + " operation is not supported");
            }
        }

        // pop all the operators from the stack
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }

        String infix = result.toString();
        return new QueryResponse(infix, calculate(infix));
    }


    public static double calculate(String expression) {
        Stack<Double> operands = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {

            char current = expression.charAt(i);

            if (operatorList.contains(current)) {
                double right = operands.pop();
                double left = operands.pop();
                double value = switch (current) {
                    case '+' -> left + right;
                    case '-' -> left - right;
                    case '*' -> left * right;
                    case '/' -> left / right;
                    default -> throw new ArithmeticException("Operand not supported" + current);
                };
                operands.push(value);
            } else {
                operands.push(Double.valueOf(String.valueOf(current)));
            }
        }
        return operands.pop();
    }

    private static int precedence(char ch) {
        return switch (ch) {
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            default -> -1;
        };
    }

}
