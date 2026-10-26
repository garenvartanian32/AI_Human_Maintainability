import java.util.*;
import java.lang.reflect.*;
import org.javatuples.*;
import java.security.*;
import java.math.*;
import java.io.*;
import java.util.stream.*;

class Problem {
    public static boolean correctBracketing(String brackets) {
        int balance = 0;
        for (char c : brackets.toCharArray()) {
            balance += c == '(' ? 1 : -1;
            if (balance < 0) return false;
        }
        return balance == 0;
    }
}