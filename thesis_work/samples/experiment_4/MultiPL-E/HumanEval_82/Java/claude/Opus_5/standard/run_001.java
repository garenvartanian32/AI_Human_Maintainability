import java.util.*;
import java.lang.reflect.*;
import org.javatuples.*;
import java.security.*;
import java.math.*;
import java.io.*;
import java.util.stream.*;

class Problem {
    public static boolean primeLength(String string) {
        int n = string.length();
        if (n < 2) return false;

        for (int divisor = 2; divisor * divisor <= n; divisor++) {
            if (n % divisor == 0) return false;
        }
        return true;
    }
}