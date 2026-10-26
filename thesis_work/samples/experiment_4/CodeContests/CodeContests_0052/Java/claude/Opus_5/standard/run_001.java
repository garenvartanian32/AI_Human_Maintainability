import java.io.*;
import java.util.*;

public class Sol {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer st = new StreamTokenizer(br);
        st.wordChars('a','z');
        StringBuilder sb = new StringBuilder();
        while (true) {
            st.nextToken();
            int n = (int) st.nval;
            if (n == 0) break;
            int state = 0;
            int count = 0;
            for (int i = 0; i < n; i++) {
                st.nextToken();
                String mv = st.sval;
                char c1 = mv.charAt(1);
                boolean raise = (c1 == 'u');
                if (state == 0) {
                    state = 1;
                } else if (state == 1) {
                    if (raise) { state = 2; count++; }
                    else { state = 0; }
                } else if (state == 2) {
                    state = 3;
                } else {
                    if (!raise) { state = 0; count++; }
                    else { state = 2; }
                }
            }
            sb.append(count).append('\n');
        }
        System.out.print(sb);
    }
}