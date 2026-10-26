import java.util.*;
import java.lang.reflect.*;
import org.javatuples.*;
import java.security.*;
import java.math.*;
import java.io.*;
import java.util.stream.*;

class Problem {
    public static long multiply(long a, long b) {
        return Math.abs(a % 10) * Math.abs(b % 10);
    }
}