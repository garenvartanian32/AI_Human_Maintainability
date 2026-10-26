import java.util.*;
import java.lang.reflect.*;
import org.javatuples.*;
import java.security.*;
import java.math.*;
import java.io.*;
import java.util.stream.*;

class Problem {
    public static boolean correctBracketing(String brackets) {
        int openBrackets = 0;

        for (char bracket : brackets.toCharArray()) {
            if (bracket == '(') {
                openBrackets++;
            } else {
                openBrackets--;
            }

            if (openBrackets < 0) {
                return false;
            }
        }

        return openBrackets == 0;
    }
}