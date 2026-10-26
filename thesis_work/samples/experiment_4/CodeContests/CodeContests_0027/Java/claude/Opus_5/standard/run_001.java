import java.io.*;
import java.util.*;

public class Sol {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer st = new StreamTokenizer(br);
        st.nextToken(); int n = (int) st.nval;
        st.nextToken(); int m = (int) st.nval;
        Set<Integer> covered = new HashSet<>();
        for (int i = 0; i < n; i++) {
            st.nextToken(); int xi = (int) st.nval;
            for (int j = 0; j < xi; j++) {
                st.nextToken();
                covered.add((int) st.nval);
            }
        }
        System.out.println(covered.size() == m ? "YES" : "NO");
    }
}