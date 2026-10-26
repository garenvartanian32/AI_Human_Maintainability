import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        while (true) {
            int n = Integer.parseInt(br.readLine().trim());
            if (n == 0) break;
            String cards = br.readLine().trim();
            int[] hands = new int[n];
            int field = 0;
            for (int i = 0; i < cards.length(); i++) {
                int p = i % n;
                char c = cards.charAt(i);
                if (c == 'M') hands[p]++;
                else if (c == 'S') { field += hands[p] + 1; hands[p] = 0; }
                else if (c == 'L') { hands[p] += field + 1; field = 0; }
            }
            Arrays.sort(hands);
            StringBuilder sb = new StringBuilder();
            for (int h : hands) sb.append(h).append(' ');
            sb.append(field);
            out.append(sb).append("\n");
        }
        System.out.print(out);
    }
}