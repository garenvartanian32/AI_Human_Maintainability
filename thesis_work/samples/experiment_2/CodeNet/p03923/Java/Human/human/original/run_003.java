import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		FS r = new FS();
		long N = r.nextLong();
		long A = r.nextLong();
		solver(N,A);
	}
	
		public static void solver(long N, long A) {
		long minTime = Long.MAX_VALUE;
		for(int i=1; i<100; i++) {
			double base = Math.pow(N, 1/(double)i);
			long speed = 1;
			long tmpTime = 0;
			for(int j=1; j<=i; j++) {
				if(j==i) {
					long time = N/speed;
					if(time*speed<N) time++;
					tmpTime+=time;
					break;
				}
				long rate = (long)base;
				if(speed*rate<Math.pow(base,j)) {
					rate++;
				}
				tmpTime+=rate+A;
				speed*=rate;
			}
			if(tmpTime <= minTime) minTime = tmpTime;
			else break;
		}
		System.out.println(minTime);
	}

	// Read Class
	static class FS {
		private final InputStream in = System.in;
		private final byte[] buffer = new byte[1024];
		private int ptr = 0;
		private int buflen = 0;
		private boolean hasNextByte() {
			if (ptr < buflen) {
				return true;
			} else {
				ptr = 0;
				try {
					buflen = in.read(buffer);
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (buflen <= 0) {
					return false;
				}
			}
			return true;
		}
	
		private int readByte() { return hasNextByte() ? buffer[ptr++] : -1;}
		private boolean isPrintableChar(int c) {return 33 <= c && c <= 126;}
		private void skipUnprintable() {while(hasNextByte() && !isPrintableChar(buffer[ptr])) ptr++;}
	
		public boolean hasNext() { skipUnprintable(); return hasNextByte();}
		public String next() {
			if (!hasNext()) throw new NoSuchElementException();
			StringBuilder sb = new StringBuilder();
			int b = readByte();
			while(isPrintableChar(b)) {
				sb.appendCodePoint(b);
				b = readByte();
			}
			return sb.toString();
		}
		public int nextInt() {
			return (int)nextLong();
		}
		public long nextLong() {
			if (!hasNext()) throw new NoSuchElementException();
			long n = 0;
			boolean minus = false;
			int b = readByte();
			if (b == '-') {
				minus = true;
				b = readByte();
			}
			if (b < '0' || '9' < b) {
				throw new NumberFormatException();
			}
			while(true) {
				if ('0' <= b && b <= '9') {
					n *= 10;
					n += b - '0';
				} else if(b == -1 || !isPrintableChar(b)) {
					return minus ? -n : n;
				} else {
					throw new NumberFormatException();
				}
				b = readByte();
			}
		}
	}
}