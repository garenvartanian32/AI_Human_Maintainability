import java.io.*;
import java.util.*;

public class D {
	public static class segtree {
		long[] t;
		long[] lazy;
		int n;
		/* USAGE
		 * Template is segtree, lazy prop w/ addition. 
		 * For assignment updates (a[k] = u), create new boolean array. Recurrence becomes assigning children to yourself.
		 * Let lazy be the boolean 'marked' array. 
		 * Delete lazy & push if TLE, saves about ~100ms.
		 */
		void update(int l, int r, long a) {update(1, 0, n-1, l, r, a);}
		long query(int l, int r) {return query(1,0,n-1,l,r);}
		public segtree(long[] a) {
			n=a.length;
			t = new long[n*4];
			lazy = new long[n*4];
			build(a, 1, 0, a.length-1);
		}
		
		void build(long[] a, int v, int tl, int tr) {
			if (tl == tr) {
				t[v] = a[tl];
				return;
			}
			int tm = (tl+tr)/2;
			build(a, v*2, tl, tm);
			build(a, v*2+1, tm+1, tr);
			t[v] = Long.min(t[v*2], t[v*2+1]); // Addition, change to to fit whatever ops.
		}
		
		void push(int v) {
			if (lazy[v] == 0) return;
			t[v*2] += lazy[v];
			lazy[v*2] += lazy[v];
			t[v*2+1] += lazy[v];
			lazy[v*2+1] += lazy[v];
			lazy[v] = 0;
		}
		
		void update(int v, int tl, int tr, int l, int r, long new_val) {
			if (l > r) return;
			if (l == tl && r == tr) {
				t[v] += new_val;
				lazy[v] += new_val;
			}
			else {
				push(v);
				int tm = (tl+tr)/2;
				update(v*2, tl, tm, l, Integer.min(r, tm), new_val);
				update(v*2+1, tm+1,tr, Integer.max(l, tm+1),r, new_val);
				t[v] = Long.min(t[v*2] , t[v*2+1]);
			}
		}
		
		long query(int v, int tl, int tr, int l, int r) {
			if (l > r) return Long.MAX_VALUE;
			if (l == tl && tr == r) return t[v];
			push(v);
			int tm = (tl+tr)/2;
			return Long.min(query(v*2, tl, tm, l, Integer.min(r, tm)) , query(v*2+1, tm+1, tr, Integer.max(l, tm+1), r));
		}
	}

	public static Reader sc = new Reader();
	//public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {
		//BufferedReader br = new BufferedReader(new FileReader("input.in"));

		int n = sc.nextInt();
		long[] a=  new long[n];
		for (int i = 0; i < n; i++) a[i] = sc.nextLong();
		segtree s = new segtree(a);
		long[] res = new long[n];
		for (int i = 1; i <= n; i++) {
			int l = -1;
			int r = n-1;
			while (l+1<r) {
				int mid = (l+r)/2;
				if (s.query(mid, r) == 0) {
					l = mid;
				}
				else r = mid;
			}
			//System.out.println(l + " " + r);
			int next = 0;
			if (s.query(r, r) == 0) {
				res[r] = i;
				next = r+1;
				if (next < n) {
					s.update(next, n-1,-i);
				}
				s.update(r, r, Long.MAX_VALUE);
			}
			else if (l >= 0){
				res[l] = i;
				next = l+1;
				if (next < n) {
					s.update(next, n-1,-i);
				}
				s.update(l, l, Long.MAX_VALUE);
			}
		}
		for (long x: res) {
			out.print(x + " ");
		}

		out.println();
		out.close();
	}

	static long ceil(long a, long b) {
		return (a + b - 1) / b;
	}

	static long powMod(long base, long exp, long mod) {
		if (base == 0 || base == 1)
			return base;
		if (exp == 0)
			return 1;
		if (exp == 1)
			return base % mod;
		long R = powMod(base, exp / 2, mod) % mod;
		R *= R;
		R %= mod;
		if ((exp & 1) == 1) {
			return base * R % mod;
		} else
			return R % mod;
	}

	static long pow(long base, long exp) {
		if (base == 0 || base == 1)
			return base;
		if (exp == 0)
			return 1;
		if (exp == 1)
			return base;
		long R = pow(base, exp / 2);
		if ((exp & 1) == 1) {
			return R * R * base;
		} else
			return R * R;
	}

	static class Reader {
		final private int BUFFER_SIZE = 1 << 16;
		private DataInputStream din;
		private byte[] buffer;
		private int bufferPointer, bytesRead;

		public Reader() {
			din = new DataInputStream(System.in);
			buffer = new byte[BUFFER_SIZE];
			bufferPointer = bytesRead = 0;
		}

		public Reader(String file_name) throws IOException {
			din = new DataInputStream(new FileInputStream(file_name));
			buffer = new byte[BUFFER_SIZE];
			bufferPointer = bytesRead = 0;
		}

		public String readLine() throws IOException {
			byte[] buf = new byte[64]; // line length 
			int cnt = 0, c;
			while ((c = read()) != -1) {
				if (c == '\n') {
					break;
				}
				buf[cnt++] = (byte) c;
			}
			return new String(buf, 0, Integer.max(cnt - 1, 0));
		}

		public int nextInt() throws IOException {
			int ret = 0;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');

			if (neg)
				return -ret;
			return ret;
		}

		public long nextLong() throws IOException {
			long ret = 0;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');
			if (neg)
				return -ret;
			return ret;
		}

		public double nextDouble() throws IOException {
			double ret = 0, div = 1;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();

			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');

			if (c == '.') {
				while ((c = read()) >= '0' && c <= '9') {
					ret += (c - '0') / (div *= 10);
				}
			}

			if (neg)
				return -ret;
			return ret;
		}

		private void fillBuffer() throws IOException {
			bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
			if (bytesRead == -1)
				buffer[0] = -1;
		}

		private byte read() throws IOException {
			if (bufferPointer == bytesRead)
				fillBuffer();
			return buffer[bufferPointer++];
		}

		public void close() throws IOException {
			if (din == null)
				return;
			din.close();
		}
	}

	public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
}
