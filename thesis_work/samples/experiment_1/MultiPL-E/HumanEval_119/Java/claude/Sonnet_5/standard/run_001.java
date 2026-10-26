import java.util.*;
import java.lang.reflect.*;
import org.javatuples.*;
import java.security.*;
import java.math.*;
import java.io.*;
import java.util.stream.*;
class Problem {
    // You are given an array array list of two strings, both strings consist of open
    // parentheses '(' or close parentheses ')' only.
    // Your job is to check if it is possible to concatenate the two strings in
    // some order, that the resulting string will be good.
    // A string S is considered to be good if and only if all parentheses in S
    // are balanced. For example: the string '(())()' is good, while the string
    // '())' is not.
    // Return 'Yes' if there's a way to make a good string, and return 'No' otherwise.
    // Examples:
    // >>> matchParens((new ArrayList<String>(Arrays.asList((String)"()(", (String)")"))))
    // ("Yes")
    // >>> matchParens((new ArrayList<String>(Arrays.asList((String)")", (String)")"))))
    // ("No")
    public static String matchParens(ArrayList<String> lst) {

        String a = lst.get(0), b = lst.get(1);
        if (checkBal119(a + b) || checkBal119(b + a)) return "Yes";
        return "No";
    }
    public static boolean checkBal119(String s) {
        int depth = 0;
        for (char c : s.toCharArray()) {
            depth += (c == '(') ? 1 : -1;
            if (depth < 0) return false;
        }
        return depth == 0;
    }
}