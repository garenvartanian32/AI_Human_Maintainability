import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Integer.MAX_VALUE;

/**
 * 551B
 * θ(|a|+|b|+|c|) time
 * θ(|a|+|b|+|c|) space
 *
 * @author artyom
 */
public class _551B implements Runnable {
    private static final int SIZE = 'z' + 1;
    private BufferedReader in;
    private StringTokenizer tok;

    private Object solve() throws IOException {
        String a = nextToken(), b = nextToken(), c = nextToken();
        int[] cntA = count(a), cntB = count(b), cntC = count(c);
        int bb = MAX_VALUE;
        for (int i = 'a'; i <= 'z'; i++) {
            if (cntB[i] > 0) {
                bb = Math.min(bb, cntA[i] / cntB[i]);
            }
        }
        int max = 0, maxB = 0;
        for (int k = 0; k <= bb; k++) {
            int cc = MAX_VALUE;
            for (int i = 'a'; i <= 'z'; i++) {
                if (cntC[i] > 0) {
                    cc = Math.min(cc, (cntA[i] - cntB[i] * k) / cntC[i]);
                }
            }
            if (k + cc > max) {
                max = k + cc;
                maxB = k;
            }
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < maxB; i++) {
            ans.append(b);
        }
        for (int j = 'a'; j <= 'z'; j++) {
            cntA[j] -= maxB * cntB[j];
        }
        max -= maxB;
        for (int i = max; i > 0; i--) {
            ans.append(c);
        }
        for (int j = 'a'; j <= 'z'; j++) {
            cntA[j] -= max * cntC[j];
            for (int i = 0; i < cntA[j]; i++) {
                ans.append((char) j);
            }
        }
        return ans;
    }

    private static int[] count(String s) {
        int[] cnt = new int[SIZE];
        for (int i = 0, n = s.length(); i < n; i++) {
            cnt[s.charAt(i)]++;
        }
        return cnt;
    }

    //--------------------------------------------------------------
    public static void main(String[] args) {
        new _551B().run();
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(System.in));
            tok = null;
            System.out.print(solve());
            in.close();
        } catch (IOException e) {
            System.exit(0);
        }
    }

    private String nextToken() throws IOException {
        while (tok == null || !tok.hasMoreTokens()) {
            tok = new StringTokenizer(in.readLine());
        }
        return tok.nextToken();
    }
}