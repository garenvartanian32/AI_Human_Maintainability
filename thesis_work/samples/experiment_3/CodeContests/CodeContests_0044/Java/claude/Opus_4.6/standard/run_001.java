import java.io.*;
import java.util.*;

public class Sol {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer st = new StreamTokenizer(br);
        st.nextToken(); int a = (int) st.nval;
        st.nextToken(); int b = (int) st.nval;
        System.out.println((a % 2 == 1 && b % 2 == 1) ? "Odd" : "Even");
    }
}