import java.io.*;
import java.util.*;
import java.math.*;
import java.lang.*;

import static java.lang.Math.*;

public class Solution implements Runnable {
	static class InputReader {
		private InputStream stream;
		private byte[] buf = new byte[1024];
		private int curChar;
		private int numChars;
		private SpaceCharFilter filter;
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		public InputReader(InputStream stream) {
			this.stream = stream;
		}

		public int read() {
			if (numChars == -1)
				throw new InputMismatchException();

			if (curChar >= numChars) {
				curChar = 0;
				try {
					numChars = stream.read(buf);
				} catch (IOException e) {
					throw new InputMismatchException();
				}

				if (numChars <= 0)
					return -1;
			}
			return buf[curChar++];
		}

		public String nextLine() {
			String str = "";
			try {
				str = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return str;
		}

		public int nextInt() {
			int c = read();

			while (isSpaceChar(c))
				c = read();

			int sgn = 1;

			if (c == '-') {
				sgn = -1;
				c = read();
			}

			int res = 0;
			do {
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = read();
			} while (!isSpaceChar(c));

			return res * sgn;
		}

		public long nextLong() {
			int c = read();
			while (isSpaceChar(c))
				c = read();
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = read();
			}
			long res = 0;

			do {
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = read();
			} while (!isSpaceChar(c));
			return res * sgn;
		}

		public double nextDouble() {
			int c = read();
			while (isSpaceChar(c))
				c = read();
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = read();
			}
			double res = 0;
			while (!isSpaceChar(c) && c != '.') {
				if (c == 'e' || c == 'E')
					return res * Math.pow(10, nextInt());
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = read();
			}
			if (c == '.') {
				c = read();
				double m = 1;
				while (!isSpaceChar(c)) {
					if (c == 'e' || c == 'E')
						return res * Math.pow(10, nextInt());
					if (c < '0' || c > '9')
						throw new InputMismatchException();
					m /= 10;
					res += (c - '0') * m;
					c = read();
				}
			}
			return res * sgn;
		}

		public String readString() {
			int c = read();
			while (isSpaceChar(c))
				c = read();
			StringBuilder res = new StringBuilder();
			do {
				res.appendCodePoint(c);
				c = read();
			} while (!isSpaceChar(c));

			return res.toString();
		}

		public boolean isSpaceChar(int c) {
			if (filter != null)
				return filter.isSpaceChar(c);
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}

		public String next() {
			return readString();
		}

		public interface SpaceCharFilter {
			public boolean isSpaceChar(int ch);
		}
	}

	public static void main(String args[]) throws Exception {
		new Thread(null, new Solution(), "Main", 1 << 27).start();
	}

	static class Pair {
		int x, y;

		Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + x * 7 + (y * 3 + 5 * (y - x));
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			Pair other = (Pair) obj;
			if (x != other.x && y != other.y) {
				return false;
			}
			return true;
		}
	}

	static void sieveOfEratosthenes(int n) {
		//Prints prime nos till n 
		boolean prime[] = new boolean[n + 1];
		for (int i = 0; i <= n; i++)
			prime[i] = true;

		for (int p = 2; p * p <= n; p++) {
			if (prime[p] == true) {
				for (int i = p * p; i <= n; i += p)
					prime[i] = false;
			}
		}

		for (int i = 2; i <= n; i++) {
			if (prime[i] == true)
				System.out.print(i + " ");
		}
	}

	public void run() {
		InputReader in = new InputReader(System.in);
		PrintWriter w = new PrintWriter(System.out);
		int n=in.nextInt();
		int k=in.nextInt();
		ArrayList<Integer> A=new ArrayList<Integer>();
		ArrayList<Integer> B=new ArrayList<Integer>();
		ArrayList<Integer> AB=new ArrayList<Integer>();
		for(int i=0;i<n;i++)
		{
			int t=in.nextInt();
			int a=in.nextInt();
			int b=in.nextInt();
			if(a==1 && b==1)
				AB.add(t);
			else if(a==1 && b==0)
				A.add(t);
			else if(a==0 && b==1)
				B.add(t);
		}
		Collections.sort(A);
		Collections.sort(B);
		Collections.sort(AB);
		if((A.size()+AB.size())<k)
			w.println(-1);
		else if((B.size()+AB.size())<k)
			w.println(-1);
		else
		{
			long count=0;
			if(A.size()==0 || B.size()==0)
			{
				for(int i=0;i<AB.size() && i<k;i++)
					count+=AB.get(i);
				w.println(count);
			}
			else
			{
				int i=0,j=0,x=0;
				while(i<A.size() && i<B.size() && j<AB.size() && x<k)
				{
					if((A.get(i)+B.get(i))<(AB.get(j)))
					{
						count+=A.get(i)+B.get(i);
						i++;
					}
					else
					{
						count+=AB.get(j);
						j++;
					}
					x++;
				}
				while(x<k && j<AB.size())
				{
					count+=AB.get(j);
					j++;
					x++;
				}
				while(x<k && i<A.size() && i<B.size())
				{
					count+=A.get(i)+B.get(i);
					i++;
					x++;
				}
				w.println(count);
			}
		}
		w.flush();
		w.close();
	}
}
