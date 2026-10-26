import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class D implements Runnable {

    BufferedReader in;
    PrintWriter out;
    StringTokenizer tok = new StringTokenizer("");

    void init() throws FileNotFoundException {
        try {
            in = new BufferedReader(new FileReader("input.txt"));
            out = new PrintWriter("output.txt");
        } catch (Exception e) {
            in = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
    }

    String readString() {
        while (!tok.hasMoreTokens()) {
            try {
                tok = new StringTokenizer(in.readLine(), " :");
            } catch (Exception e) {
                return null;
            }
        }
        return tok.nextToken();
    }

    int readInt() {
        return Integer.parseInt(readString());
    }

    int[] readIntArray(int size) {
        int[] res = new int[size];
        for (int i = 0; i < size; i++) {
            res[i] = readInt();
        }
        return res;
    }

    long readLong() {
        return Long.parseLong(readString());
    }

    double readDouble() {
        return Double.parseDouble(readString());
    }

    <T> List<T>[] createGraphList(int size) {
        List<T>[] list = new List[size];
        for (int i = 0; i < size; i++) {
            list[i] = new ArrayList<>();
        }
        return list;
    }

    public static void main(String[] args) {
        new Thread(null, new D(), "", 1l * 200 * 1024 * 1024).start();
    }

    long timeBegin, timeEnd;

    void time() {
        timeEnd = System.currentTimeMillis();
        System.err.println("Time = " + (timeEnd - timeBegin));
    }

    long memoryTotal, memoryFree;

    void memory() {
        memoryFree = Runtime.getRuntime().freeMemory();
        System.err.println("Memory = " + ((memoryTotal - memoryFree) >> 10)
                + " KB");
    }

    public void run() {
        try {
            timeBegin = System.currentTimeMillis();
            memoryTotal = Runtime.getRuntime().freeMemory();
            init();
            solve();
            out.close();
            if (System.getProperty("ONLINE_JUDGE") == null) {
                time();
                memory();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    List<Integer>[] graph;
    boolean[] vis;
    ArrayList<Integer> comp = new ArrayList<>();

    void dfs(int x) {
        vis[x] = true;
        comp.add(x);
        for (int y : graph[x]) {
            if (!vis[y]) {
                dfs(y);
            }
        }
    }

    void solve() throws IOException {
        int n = readInt();
        int m = readInt();
        int[] a = readIntArray(n);
        vis = new boolean[n];
        graph = createGraphList(n);

        for (int i = 0; i < m; i++) {
            int x = readInt() - 1;
            int y = readInt() - 1;
            graph[x].add(y);
            graph[y].add(x);
        }

        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                comp.clear();
                dfs(i);
                Collections.sort(comp);
                List<Integer> values = new ArrayList<>();
                for (int ind : comp) {
                    values.add(a[ind]);
                }
                Collections.sort(values);

                for (int j = 0; j < comp.size(); j++) {
                    a[comp.get(j)] = values.get(comp.size() - 1 - j);
                }
            }
        }

        for (int x : a) out.print(x + " ");

    }

}