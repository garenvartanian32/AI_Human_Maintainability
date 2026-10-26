import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

public class Main {

	static PrintWriter out;
	static InputReader ir;
	static int[] d={1,-1};

	static void solve() {
		outer:
		for(;;){
			int n=ir.nextInt();
			if(n==0)
				break;
			String[] as=new String[n];
			int ma=Integer.MIN_VALUE,mi=Integer.MAX_VALUE;
			for(int i=0;i<n;i++)
				as[i]=ir.next();
			for(int i=0;i<n;i+=2){
				if(as[i].charAt(0)=='x'){
					for(int j=0;j<2;j++){
						int nx=i+d[j];
						if(nx>=0&&nx<n){
							if(as[nx].charAt(0)!='x')
								mi=Math.min(mi,Integer.parseInt(as[nx]));
							else{
								out.println("none");
								continue outer;
							}
						}
					}
				}
				else{
					for(int j=0;j<2;j++){
						int nx=i+d[j];
						if(nx>=0&&nx<n){
							if(as[nx].charAt(0)!='x'&&Integer.parseInt(as[i])>=Integer.parseInt(as[nx])){
								out.println("none");
								continue outer;
							}
						}
					}
				}
			}
			for(int i=1;i<n;i+=2){
				if(as[i].charAt(0)=='x'){
					for(int j=0;j<2;j++){
						int nx=i+d[j];
						if(nx>=0&&nx<n){
							if(as[nx].charAt(0)!='x')
								ma=Math.max(ma,Integer.parseInt(as[nx]));
							else{
								out.println("none");
								continue outer;
							}
						}
					}
				}
			}
			mi--;
			ma++;
			if(mi<ma)
				out.println("none");
			else if(mi==ma)
				out.println(mi);
			else
				out.println("ambiguous");
		}
	}

	public static void main(String[] args) throws Exception {
		ir = new InputReader(System.in);
		out = new PrintWriter(System.out);
		solve();
		out.flush();
	}

	static class InputReader {

		private InputStream in;
		private byte[] buffer = new byte[1024];
		private int curbuf;
		private int lenbuf;

		public InputReader(InputStream in) {
			this.in = in;
			this.curbuf = this.lenbuf = 0;
		}

		public boolean hasNextByte() {
			if (curbuf >= lenbuf) {
				curbuf = 0;
				try {
					lenbuf = in.read(buffer);
				} catch (IOException e) {
					throw new InputMismatchException();
				}
				if (lenbuf <= 0)
					return false;
			}
			return true;
		}

		private int readByte() {
			if (hasNextByte())
				return buffer[curbuf++];
			else
				return -1;
		}

		private boolean isSpaceChar(int c) {
			return !(c >= 33 && c <= 126);
		}

		private void skip() {
			while (hasNextByte() && isSpaceChar(buffer[curbuf]))
				curbuf++;
		}

		public boolean hasNext() {
			skip();
			return hasNextByte();
		}

		public String next() {
			if (!hasNext())
				throw new NoSuchElementException();
			StringBuilder sb = new StringBuilder();
			int b = readByte();
			while (!isSpaceChar(b)) {
				sb.appendCodePoint(b);
				b = readByte();
			}
			return sb.toString();
		}

		public int nextInt() {
			if (!hasNext())
				throw new NoSuchElementException();
			int c = readByte();
			while (isSpaceChar(c))
				c = readByte();
			boolean minus = false;
			if (c == '-') {
				minus = true;
				c = readByte();
			}
			int res = 0;
			do {
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res = res * 10 + c - '0';
				c = readByte();
			} while (!isSpaceChar(c));
			return (minus) ? -res : res;
		}

		public long nextLong() {
			if (!hasNext())
				throw new NoSuchElementException();
			int c = readByte();
			while (isSpaceChar(c))
				c = readByte();
			boolean minus = false;
			if (c == '-') {
				minus = true;
				c = readByte();
			}
			long res = 0;
			do {
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res = res * 10 + c - '0';
				c = readByte();
			} while (!isSpaceChar(c));
			return (minus) ? -res : res;
		}

		public double nextDouble() {
			return Double.parseDouble(next());
		}

		public int[] nextIntArray(int n) {
			int[] a = new int[n];
			for (int i = 0; i < n; i++)
				a[i] = nextInt();
			return a;
		}

		public long[] nextLongArray(int n) {
			long[] a = new long[n];
			for (int i = 0; i < n; i++)
				a[i] = nextLong();
			return a;
		}

		public char[][] nextCharMap(int n, int m) {
			char[][] map = new char[n][m];
			for (int i = 0; i < n; i++)
				map[i] = next().toCharArray();
			return map;
		}
	}
}