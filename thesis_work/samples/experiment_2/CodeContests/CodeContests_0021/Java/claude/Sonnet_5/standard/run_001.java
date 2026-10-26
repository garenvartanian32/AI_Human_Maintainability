import java.io.*;
import java.util.*;

public class Sol {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer st = new StreamTokenizer(br);
        st.nextToken(); int a = (int) st.nval;
        st.nextToken(); int b = (int) st.nval;
        long total = a;
        long leftover = a;
        while (leftover >= b) {
            long add = leftover / b;
            total += add;
            leftover = leftover % b + add;
        }
        System.out.println(total);
    }
}