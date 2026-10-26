import java.util.*;
import java.lang.reflect.*;
import org.javatuples.*;
import java.security.*;
import java.math.*;
import java.io.*;
import java.util.stream.*;

class Problem {
    public static boolean primeLength(String string) {
        int length = string.length();

        if (length < 2) {
            return false;
        }

        for (int divisor = 2; divisor * divisor <= length; divisor++) {
            if (length % divisor == 0) {
                return false;
            }
        }

        return true;
    }
}