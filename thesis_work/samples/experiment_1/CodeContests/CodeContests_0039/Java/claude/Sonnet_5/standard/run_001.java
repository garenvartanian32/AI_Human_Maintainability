import java.util.*;

public class Main {
    static int n;
    static List<String> result = new ArrayList<String>();
    static void rec(int[] s, int len, int maxc) {
        if (len == n) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < len; i++) sb.append((char)('a'+s[i]));
            result.add(sb.toString());
            return;
        }
        for (int c = 0; c <= maxc+1; c++) {
            s[len] = c;
            rec(s, len+1, Math.max(maxc, c));
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        rec(new int[n], 0, -1);
        StringBuilder out = new StringBuilder();
        out.append(result.size()).append("\n");
        for (String s : result) out.append(s).append("\n");
        System.out.print(out);
    }
}