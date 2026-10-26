import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.*;
import java.io.*;
public class ProblemE {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FastScanner input = new FastScanner();
		int length_of_street = input.nextInt();
		int blocked_positions = input.nextInt();
		int maximum_lamp = input.nextInt();
		boolean[] blocked = new boolean[length_of_street];
		for (int i = 0; i < blocked_positions; i++) {
			blocked[input.nextInt()] = true;
		}
		int[] lamp_costs = new int[maximum_lamp + 1];
		for (int i = 1; i <= maximum_lamp; i++) {
			lamp_costs[i] = input.nextInt();
		}
		int[] skipList = new int[length_of_street];
		for (int i = 0; i < length_of_street; i++) {
			if (i == 0 && blocked[i]) {
				System.out.println(-1);
				return;
			} else {
				skipList[i] = i;
				if (blocked[i]) {
					skipList[i] = skipList[i - 1];
				}
			}
		}
		long best = Long.MAX_VALUE;
		lbl: for (int length = 1; length <= maximum_lamp; length++) {
			int start = 0;
			int count = 0;
			while (start < length_of_street) {
				count++;
				int tmpStart = start;
				start += length;
				if (start >= skipList.length) {
					break;
				}
				start = skipList[start];
				if (start == tmpStart) {
					continue lbl;
				}
			}
			best = Math.min(best, ((long) count) * lamp_costs[length]);
		}
		if (best == Long.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(best);
		}
	}
	public static class FastScanner {
        BufferedReader br;
        StringTokenizer st;
        public FastScanner(Reader in) {
            br = new BufferedReader(in);
        }
        public FastScanner() {
            this(new InputStreamReader(System.in));
        }
        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
        String readNextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
        int[] readIntArray(int n) {
            int[] a = new int[n];
            for (int idx = 0; idx < n; idx++) {
                a[idx] = nextInt();
            }
            return a;
        }
        long[] readLongArray(int n) {
            long[] a = new long[n];
            for (int idx = 0; idx < n; idx++) {
                a[idx] = nextLong();
            }
            return a;
        }
    }
}
