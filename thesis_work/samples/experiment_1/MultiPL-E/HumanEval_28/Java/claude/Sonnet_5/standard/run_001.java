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

        StringBuilder sb = new StringBuilder();
        for (String s : strings) {
            sb.append(s);
        }
        return sb.toString();
    }
}