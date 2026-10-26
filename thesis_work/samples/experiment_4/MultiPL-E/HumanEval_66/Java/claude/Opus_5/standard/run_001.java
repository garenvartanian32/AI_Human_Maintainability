import java.util.*;
import java.lang.reflect.*;
import org.javatuples.*;
import java.security.*;
import java.math.*;
import java.io.*;
import java.util.stream.*;

class Problem {
    public static long digitSum(String s) {
        long sum = 0;
        for (char c : s.toCharArray()) {
            if (c >= 'A' && c <= 'Z') sum += c;
        }
        return sum;
    }
}