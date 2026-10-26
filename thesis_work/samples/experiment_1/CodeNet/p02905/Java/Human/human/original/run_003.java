//package com.company;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    public static class Task {
        int[] factors;
        List<Integer> primes;
        int M = 1_000_001;
        int mod = 998244353;
        int[] addition; // \sum a_i
        int[] additionSq; // \sum a_i^2
        int[] dp;
        int inv2 = (int) BigInteger.valueOf(2).modInverse(BigInteger.valueOf(mod)).longValue();
        int[] inv;
        int getProduct(int index) {
            return mult(add(mult(addition[index], addition[index]), -additionSq[index]), inv2);
        }

        int add(int a, int b) {
            int c = a + b;
            if (c >= mod) return c - mod;
            if (c < 0) return c + mod;
            return c;
        }
        int mult(int a, int b) {
            return (int) ((long) a * b % mod);
        }

        public void addNumber(int v) {
            TreeMap<Integer, Integer> divisible = new TreeMap<>();
            int addV = v;
            int multV = mult(v, v);
            while (v != 1) {
                int f = factors[v];
                int cnt = 0;
                while (v % f == 0) {
                    v /= f;
                    cnt++;
                }
                if (cnt > 0) divisible.put(f, cnt);
            }
            List<Integer> values = new ArrayList<>();
            List<Integer> keys = new ArrayList<>();
            for (Map.Entry<Integer, Integer> entry: divisible.entrySet()) {
                values.add(entry.getValue());
                keys.add(entry.getKey());
            }
            int[] counts = new int[values.size()];
            long currentNumber = 1;
            if (values.size() > 0) {
                outer:
                while (true) {
                    int idx = 0;
                    counts[idx]++;
                    currentNumber = currentNumber * keys.get(idx);
                    while (counts[idx] == values.get(idx) + 1) {
                        for (int i = 0; i < counts[idx]; i++) {
                            if (currentNumber % keys.get(idx) != 0) {
                                throw new RuntimeException();
                            }
                            currentNumber = currentNumber / keys.get(idx);
                        }
                        counts[idx] = 0;
                        if (idx == counts.length - 1) break outer;
                        counts[++idx]++;
                        currentNumber = currentNumber * keys.get(idx);
                    }
//                    System.err.println(addV + " " + currentNumber);
                    int tmp = (int) currentNumber;
                    addition[tmp] = add(addition[tmp], addV);
                    additionSq[tmp] = add(additionSq[tmp], multV);
                }
            }
            addition[1] = add(addition[1], addV);
            additionSq[1] = add(additionSq[1], multV);
        }
        public void precompute() {
            factors = new int[M];
            primes = new ArrayList<>();
            factors[1] = 1;
            for (int i = 2; i < M; i++) {
                if (factors[i] == 0) {
                    factors[i] = i;
                    primes.add(i);
                }
                for (int p : primes) {
                    if (p * i >= M) break;
                    factors[p * i] = p;
                    if (i % p == 0) break;
                }
            }
            addition = new int[M];
            additionSq = new int[M];
            dp = new int[M];
            inv = new int[M];
            inv[1] = 1;
            for (int i = 2; i < M; ++i) {
                inv[i] = add(mod, -mult(mod / i, inv[mod % i]));
            }
        }



        public void solve(Scanner sc, PrintWriter pw) throws IOException {
            int n = sc.nextInt();
            precompute();
            for (int i = 0; i < n; i++) {
                addNumber(sc.nextInt());
            }
            for (int i = M - 1; i >= 1; i--) {
                dp[i] = getProduct(i);
                for (int j = 2; j * i < M; j++) {
                    dp[i] = add(dp[i], -dp[i * j]);
                }
            }
//            System.err.println(Arrays.toString(Arrays.copyOf(addition, 7)));
//            System.err.println(Arrays.toString(Arrays.copyOf(additionSq, 7)));

//            System.err.println(Arrays.toString(Arrays.copyOf(dp, 7)));
            int ret = 0;
            for (int i = 1; i < M; i++) {
                ret = add(ret, mult(dp[i], inv[i]));
            }
            pw.println(ret);
        }
    }

    static long TIME_START, TIME_END;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new FileInputStream("input"));
        PrintWriter pw = new PrintWriter(new BufferedOutputStream(System.out));
//        PrintWriter pw = new PrintWriter(new FileOutputStream("output"));

        Runtime runtime = Runtime.getRuntime();
        long usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();
        TIME_START = System.currentTimeMillis();
        Task t = new Task();
        t.solve(sc, pw);
        TIME_END = System.currentTimeMillis();
        long usedMemoryAfter = runtime.totalMemory() - runtime.freeMemory();
        pw.close();
        System.err.println("Memory increased: " + (usedMemoryAfter - usedMemoryBefore) / 1000000);
        System.err.println("Time used: " + (TIME_END - TIME_START) + ".");
    }

    static class Scanner {
        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream s) {
            br = new BufferedReader(new InputStreamReader(s));
        }

        public Scanner(FileReader s) throws FileNotFoundException {
            br = new BufferedReader(s);
        }

        public String next() throws IOException {
            while (st == null || !st.hasMoreTokens())
                st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        public long nextLong() throws IOException {
            return Long.parseLong(next());
        }

        public String nextLine() throws IOException {
            return br.readLine();
        }

        public double nextDouble() throws IOException {
            return Double.parseDouble(next());
        }

        public boolean ready() throws IOException {
            return br.ready();
        }
    }
}
