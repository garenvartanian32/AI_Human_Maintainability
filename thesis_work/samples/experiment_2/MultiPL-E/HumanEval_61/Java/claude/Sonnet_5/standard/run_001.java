import java.util.*;
import java.lang.reflect.*;
import org.javatuples.*;
import java.security.*;
import java.math.*;
import java.io.*;
import java.util.stream.*;
class Problem {
    // brackets is a string of "(" and ")".
    // return true if every opening bracket has a corresponding closing bracket.
    // >>> correctBracketing(("("))
    // (false)
    // >>> correctBracketing(("()"))
    // (true)
    // >>> correctBracketing(("(()())"))
    // (true)
    // >>> correctBracketing((")(()"))
    // (false)
    public static boolean correctBracketing(String brackets) {
        int bal = 0;
        for (char c : brackets.toCharArray()) {
            bal += (c == '(') ? 1 : -1;
            if (bal < 0) return false;
        }
        return bal == 0;
    }

    public static void main(String[] args) {
        System.out.println(correctBracketing("("));
        System.out.println(correctBracketing("()"));
        System.out.println(correctBracketing("(()())"));
        System.out.println(correctBracketing(")(()"));
    }
}