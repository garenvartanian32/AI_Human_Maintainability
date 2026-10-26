import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        st.nextToken(); int n = (int) st.nval;
        st.nextToken(); int m = (int) st.nval;
        Set<Integer> on = new HashSet<Integer>();
        for (int i = 0; i < n; i++) {
            st.nextToken(); int x = (int) st.nval;
            for (int j = 0; j < x; j++) { st.nextToken(); on.add((int) st.nval); }
        }
        System.out.println(on.size() == m ? "YES" : "NO");
    }
}