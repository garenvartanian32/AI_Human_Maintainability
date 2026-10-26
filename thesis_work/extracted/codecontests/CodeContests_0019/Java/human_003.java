import java.io.*;
import java.math.BigInteger;
import java.time.YearMonth;
import java.util.*;

public class Main {
    static boolean local = false;
    static FReader sc;
    static PrintWriter pw;

    public static void main(String args[]) throws FileNotFoundException {
        if (args.length != 0) {
            local = true;
            sc = new FReader("/Users/qjex/prj/jolymp/src/input.txt");
            try {
                pw = new PrintWriter(new FileOutputStream("/Users/qjex/prj/jolymp/src/output.txt"), false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            sc = new FReader(System.in);
            pw = new PrintWriter(System.out);
        }
        solve();
        pw.close();
        sc.close();
    }

    static boolean can(String s) {
        if (s.charAt(2) != '-' || s.charAt(5) != '-') return false;
        for (int i = 0; i < s.length(); i++) {
            if (i == 2 || i == 5) continue;;
            if (s.charAt(i) < '0' || s.charAt(i) > '9') return false;
        }
        int d = Integer.valueOf(s.substring(0, 2));
        int m = Integer.valueOf(s.substring(3, 5));
        int y = Integer.valueOf(s.substring(6));
        return (y >= 2013 && y <= 2015 && m >= 1 && m <= 12 && d >= 1 && d <= YearMonth.of(y, m).lengthOfMonth());
    }

    public static void solve() {
        String s = sc.next();
        TreeMap<String, Integer> all = new TreeMap<String, Integer>();
        for (int i = 0; i < s.length() - 9; i++) {
            String cur = s.substring(i, i + 10);
            if (can(cur)) {
                if (all.containsKey(cur)) {
                    all.put(cur, all.get(cur) + 1);
                } else {
                    all.put(cur, 1);
                }
            }
        }
        int mx = 0;
        String ans = "";
        for (Map.Entry<String, Integer> e : all.entrySet()) {
            if (e.getValue() > mx) {
                mx = e.getValue();
                ans = e.getKey();
            }
        }
        pw.print(ans);
    }

}

class FReader {
    BufferedReader br;
    StringTokenizer st;

    FReader(String file) {
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        st = new StringTokenizer("");
    }


    FReader(InputStream is) {
        br = new BufferedReader(new InputStreamReader(is));
        st = new StringTokenizer("");
    }

    String next() {
        try {
            while (!st.hasMoreElements()) {
                st = new StringTokenizer(br.readLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return st.nextToken();
    }

    int nextInt() {
        return Integer.parseInt(next());
    }

    long nextLong() {
        return Long.parseLong(next());
    }

    double nextDouble() {
        return Double.parseDouble(next());
    }

    BigInteger nextBigInteger(){ return new BigInteger(next()); }

    public void close() {
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Pair implements Comparable<Pair> {

    public int first;
    public int second;

    public Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public int compareTo(Pair pairInt) {
        if (first > pairInt.first) return 1;
        if (first < pairInt.first) return -1;
        if (second > pairInt.second) return 1;
        return second == pairInt.second ? 0 : -1;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Pair && compareTo((Pair) obj) == 0;
    }
}