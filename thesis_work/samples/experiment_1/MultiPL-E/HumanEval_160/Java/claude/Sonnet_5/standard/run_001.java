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

        long result = operand.get(0);
        for (int i = 0; i < op.size(); i++) {
            long next = operand.get(i+1);
            String o = op.get(i);
            if (o.equals("+")) result = result + next;
            else if (o.equals("-")) result = result - next;
            else if (o.equals("*")) result = result * next;
            else if (o.equals("//")) result = Math.floorDiv(result, next);
            else if (o.equals("**")) result = (long) Math.pow(result, next);
        }
        return result;
    }
}