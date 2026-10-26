import java.io.*;
import java.util.*;

public class Sol {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer first = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(first.nextToken());
        int m = Integer.parseInt(first.nextToken());
        int[] mem = new int[m];
        Set<Integer> active = new HashSet<>();
        int nextId = 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < t; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine());
            String cmd = stk.nextToken();
            if (cmd.equals("alloc")) {
                int n = Integer.parseInt(stk.nextToken());
                int found = -1, run = 0;
                for (int j = 0; j < m; j++) {
                    if (mem[j] == 0) {
                        run++;
                        if (run == n) { found = j - n + 1; break; }
                    } else {
                        run = 0;
                    }
                }
                if (found == -1) {
                    sb.append("NULL").append('\n');
                } else {
                    for (int j = found; j < found + n; j++) mem[j] = nextId;
                    active.add(nextId);
                    sb.append(nextId).append('\n');
                    nextId++;
                }
            } else if (cmd.equals("erase")) {
                int x = Integer.parseInt(stk.nextToken());
                if (active.contains(x)) {
                    for (int j = 0; j < m; j++) if (mem[j] == x) mem[j] = 0;
                    active.remove(x);
                } else {
                    sb.append("ILLEGAL_ERASE_ARGUMENT").append('\n');
                }
            } else if (cmd.equals("defragment")) {
                int[] vals = new int[m];
                int c = 0;
                for (int v : mem) if (v != 0) vals[c++] = v;
                for (int j = 0; j < m; j++) mem[j] = (j < c) ? vals[j] : 0;
            }
        }
        System.out.print(sb);
    }
}