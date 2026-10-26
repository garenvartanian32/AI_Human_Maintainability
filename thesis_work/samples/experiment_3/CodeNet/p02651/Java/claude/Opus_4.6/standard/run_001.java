import java.io.*;
import java.util.*;

public class Sol {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer st = new StreamTokenizer(br);
        StringBuilder input = new StringBuilder();
        int c;
        while ((c = br.read()) != -1) input.append((char) c);
        StringTokenizer tk = new StringTokenizer(input.toString());

        int t = Integer.parseInt(tk.nextToken());
        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < t; tc++) {
            int n = Integer.parseInt(tk.nextToken());
            long[] a = new long[n];
            for (int i = 0; i < n; i++) a[i] = Long.parseLong(tk.nextToken());
            String s = tk.nextToken();

            Map<Integer, Long> basis = new HashMap<>();
            int result = 0;
            for (int i = n - 1; i >= 0; i--) {
                long v = a[i];
                for (int bit = 63; bit >= 0; bit--) {
                    if (((v >> bit) & 1) == 1) {
                        if (basis.containsKey(bit)) {
                            v ^= basis.get(bit);
                        } else {
                            break;
                        }
                    }
                }
                if (v != 0) {
                    int lb = 63 - Long.numberOfLeadingZeros(v);
                    basis.put(lb, v);
                    if (s.charAt(i) == '1') {
                        result = 1;
                        break;
                    }
                }
            }
            sb.append(result).append('\n');
        }
        System.out.print(sb);
    }
}