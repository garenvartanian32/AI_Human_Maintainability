import java.util.*;
import java.lang.reflect.*;
import org.javatuples.*;
import java.security.*;
import java.math.*;
import java.io.*;
import java.util.stream.*;
class Problem {
    // Concatenate array list of strings into a single string
    // >>> concatenate((new ArrayList<String>(Arrays.asList())))
    // ("")
    // >>> concatenate((new ArrayList<String>(Arrays.asList((String)"a", (String)"b", (String)"c"))))
    // ("abc")
    public static String concatenate(ArrayList<String> strings) {
        // A builder avoids the quadratic cost of repeated string concatenation,
        // and yields "" naturally for an empty list.
        StringBuilder joined = new StringBuilder();
        for (String piece : strings) {
            joined.append(piece);
        }
        return joined.toString();
    }
}