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
    public static long doAlgebra(ArrayList<String> op, ArrayList<Long> operand) {
        // Precedence: ** highest, then * and //, then + and -
        List<Long> values = new ArrayList<>(operand);
        List<String> ops = new ArrayList<>(op);

        // Pass 1: exponentiation (right-to-left not specified; assume left-to-right like others)
        for (int i = 0; i < ops.size(); ) {
            if (ops.get(i).equals("**")) {
                long base = values.get(i), exp = values.get(i + 1);
                long res = 1;
                for (long e = 0; e < exp; e++) res *= base;
                values.set(i, res);
                values.remove(i + 1);
                ops.remove(i);
            } else {
                i++;
            }
        }
        // Pass 2: * and //
        for (int i = 0; i < ops.size(); ) {
            String o = ops.get(i);
            if (o.equals("*") || o.equals("//")) {
                long a = values.get(i), b = values.get(i + 1);
                long res = o.equals("*") ? a * b : Math.floorDiv(a, b);
                values.set(i, res);
                values.remove(i + 1);
                ops.remove(i);
            } else {
                i++;
            }
        }
        // Pass 3: + and -
        long result = values.get(0);
        for (int i = 0; i < ops.size(); i++) {
            String o = ops.get(i);
            long b = values.get(i + 1);
            if (o.equals("+")) result += b;
            else if (o.equals("-")) result -= b;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(doAlgebra(new ArrayList<>(Arrays.asList("+","*","-")), new ArrayList<>(Arrays.asList(2L,3L,4L,5L))));
    }
}