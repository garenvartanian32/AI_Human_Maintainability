import java.util.*;
import java.lang.reflect.*;
import org.javatuples.*;
import java.security.*;
import java.math.*;
import java.io.*;
import java.util.stream.*;
class Problem {
    // Given two array lists operator, and operand. The first array list has basic algebra operations, and 
    // the second array list is an array array list of integers. Use the two given array lists to build the algebric 
    // expression and return the evaluation of this expression.
    // The basic algebra operations:
    // Addition ( + ) 
    // Subtraction ( - ) 
    // Multiplication ( * ) 
    // Floor division ( // ) 
    // Exponentiation ( ** ) 
    // Example:
    // operator['+', '*', '-']
    // array array list = [2, 3, 4, 5]
    // result = 2 + 3 * 4 - 5
    // => result = 9
    // Note:
    // The length of operator array list is equal to the length of operand array list minus one.
    // Operand is an array array list of of non-negative integers.
    // Operator array list has at least one operator, and operand array list has at least two operands.

    // Operators are collapsed in precedence order: exponentiation binds
    // tightest, then multiplication and floor division, then the additive pair.
    private static final List<Set<String>> PRECEDENCE_LEVELS = Arrays.asList(
            new HashSet<>(Arrays.asList("**")),
            new HashSet<>(Arrays.asList("*", "//")),
            new HashSet<>(Arrays.asList("+", "-")));

    public static long doAlgebra(ArrayList<String> op, ArrayList<Long> operand) {
        LinkedList<Long> values = new LinkedList<>(operand);
        LinkedList<String> symbols = new LinkedList<>(op);

        for (Set<String> level : PRECEDENCE_LEVELS) {
            collapse(values, symbols, level);
        }

        return values.getFirst();
    }

    /** Applies every operator belonging to {@code level}, left to right. */
    private static void collapse(
            LinkedList<Long> values, LinkedList<String> symbols, Set<String> level) {
        int position = 0;
        while (position < symbols.size()) {
            String symbol = symbols.get(position);
            if (!level.contains(symbol)) {
                position++;
                continue;
            }

            long result = apply(symbol, values.get(position), values.get(position + 1));
            values.set(position, result);
            values.remove(position + 1);
            symbols.remove(position);
        }
    }

    private static long apply(String symbol, long left, long right) {
        switch (symbol) {
            case "**":
                return power(left, right);
            case "*":
                return left * right;
            case "//":
                return Math.floorDiv(left, right);
            case "+":
                return left + right;
            default:
                return left - right;
        }
    }

    private static long power(long base, long exponent) {
        long result = 1;
        for (long step = 0; step < exponent; step++) {
            result *= base;
        }
        return result;
    }
}