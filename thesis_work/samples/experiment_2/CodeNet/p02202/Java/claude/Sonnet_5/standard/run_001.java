import java.io.*;
import java.util.*;

public class Sol {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer st = new StreamTokenizer(br);
        st.nextToken(); int n = (int) st.nval;
        long total = 0;
        for (int i = 0; i < n; i++) {
            st.nextToken();
            total += (long) st.nval;
        }
        total -= (long) n * (n + 1) / 2;
        System.out.println(total);
    }
}