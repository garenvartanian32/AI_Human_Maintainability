import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 */
public class Main {
	public static void main(String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		InputReader in = new InputReader(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		TaskA solver = new TaskA();
		solver.solve(1, in, out);
		out.close();
	}
}

class TaskA {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n = in.nextInt();
		char[] s = in.next().toCharArray();
		for (int i = 0; i < n; i++) {
			for (int len = 1; len <= n; len++) {
				int cnt = 0;
				for (int j = i; j < n; j += len) {
					if (s[j] == '.')
						break;
					++cnt;
					if (cnt == 5) {
						out.println("yes");
						return;
					}
				}
			}
		}
		out.println("no");
	}
}

class InputReader {
	final InputStream is;
	final byte[] buf = new byte[1024];
	int pos;
	int size;

	public InputReader(InputStream is) {
		this.is = is;
	}

	public int nextInt() {
		int c = read();
		while (isWhitespace(c))
			c = read();
		int sign = 1;
		if (c == '-') {
			sign = -1;
			c = read();
		}
		int res = 0;
		do {
			if (c < '0' || c > '9')
				throw new InputMismatchException();
			res = res * 10 + c - '0';
			c = read();
		} while (!isWhitespace(c));
		return res * sign;
	}

	public String next() {
		int c = read();
		while (isWhitespace(c))
			c = read();
		StringBuilder sb = new StringBuilder();
		do {
			sb.append((char) c);
			c = read();
		} while (!isWhitespace(c));
		return sb.toString();
	}

	int read() {
		if (size == -1)
			throw new InputMismatchException();
		if (pos >= size) {
			pos = 0;
			try {
				size = is.read(buf);
			} catch (IOException e) {
				throw new InputMismatchException();
			}
			if (size <= 0)
				return -1;
		}
		return buf[pos++] & 255;
	}

	static boolean isWhitespace(int c) {
		return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
	}

}

