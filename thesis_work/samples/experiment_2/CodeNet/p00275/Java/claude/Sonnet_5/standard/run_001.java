import java.io.*;
import java.util.*;

public class Sol {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer st = new StreamTokenizer(br);
        st.wordChars('A','Z');
        StringBuilder out = new StringBuilder();
        while (true) {
            st.nextToken();
            int n = (int) st.nval;
            if (n == 0) break;
            st.nextToken();
            String cards = st.sval;
            int[] hands = new int[n];
            int field = 0;
            for (int i = 0; i < cards.length(); i++) {
                char c = cards.charAt(i);
                int player = i % n;
                if (c == 'M') {
                    hands[player]++;
                } else if (c == 'S') {
                    field += hands[player] + 1;
                    hands[player] = 0;
                } else if (c == 'L') {
                    hands[player] += field + 1;
                    field = 0;
                }
            }
            Arrays.sort(hands);
            StringBuilder line = new StringBuilder();
            for (int i = 0; i < n; i++) {
                if (i > 0) line.append(' ');
                line.append(hands[i]);
            }
            line.append(' ').append(field);
            out.append(line).append('\n');
        }
        System.out.print(out);
    }
}