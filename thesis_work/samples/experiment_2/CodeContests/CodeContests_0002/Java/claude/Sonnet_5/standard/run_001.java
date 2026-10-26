import java.io.*;
import java.util.*;

public class Sol {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer st = new StreamTokenizer(br);
        st.nextToken();
        int q = (int) st.nval;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            st.nextToken(); long l = (long) st.nval;
            st.nextToken(); long r = (long) st.nval;
            st.nextToken(); long d = (long) st.nval;
            long ans;
            if (d < l || d > r) {
                ans = d;
            } else {
                ans = ((r / d) + 1) * d;
            }
            sb.append(ans).append('\n');
        }
        System.out.print(sb);
    }
}