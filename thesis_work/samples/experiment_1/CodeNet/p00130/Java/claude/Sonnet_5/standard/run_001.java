import java.util.*;
import java.io.*;

public class Main {
    static String solve(String s) {
        Map<Integer, Character> chain = new HashMap<Integer, Character>();
        int pos = 0;
        chain.put(0, s.charAt(0));
        int i = 1;
        int n = s.length();
        while (i < n) {
            if (i+1 < n && s.charAt(i) == '-' && s.charAt(i+1) == '>') {
                pos++;
                chain.put(pos, s.charAt(i+2));
                i += 3;
            } else {
                pos--;
                chain.put(pos, s.charAt(i));
                i += 1;
            }
        }
        int lo = Collections.min(chain.keySet());
        int hi = Collections.max(chain.keySet());
        StringBuilder sb = new StringBuilder();
        for (int p = lo; p <= hi; p++) sb.append(chain.get(p));
        return sb.toString();
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < n; i++) {
            String s = br.readLine().trim();
            out.append(solve(s)).append("\n");
        }
        System.out.print(out);
    }
}