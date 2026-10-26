import java.util.*;
import java.lang.reflect.*;
import org.javatuples.*;
import java.security.*;
import java.math.*;
import java.io.*;
import java.util.stream.*;

class Problem {
    public static long isBored(String S) {
        long count = 0;
        for (String sentence : S.split("[.?!]")) {
            String trimmed = sentence.trim();
            if (trimmed.equals("I") || trimmed.startsWith("I ")) count++;
        }
        return count;
    }
}