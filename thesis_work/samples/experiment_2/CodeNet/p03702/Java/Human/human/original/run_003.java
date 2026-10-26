import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.NoSuchElementException;

public class Main {

	int N;
	long A,B;
	int[] h,hh;

	private boolean check(int x){

		long b = 1L * B * x;

		if(b >= (int)1e9+7)return true;

		long cntA = 0;

		hh = new int[N];
		for(int i = 0;i < N;i++){
			hh[i] = h[i] - (int)b;
		}

		for(int i = 0;i < N;i++){
			if(hh[i] > 0){
				cntA += hh[i] / (A-B) + (hh[i]%(A-B) > 0 ? 1 : 0);
			}
		}

		return cntA <= x;
	}

	public void solve() {
		N = nextInt();
		A = nextInt();
		B = nextInt();

		h = new int[N];
		for(int i = 0;i < N;i++){
			h[i] = nextInt();
		}


		int low = 0;
		int high = (int)1e9+7;

		while(high-low>1){

			int mid = high+low >> 1;
			if(check(mid)){
				high = mid;
			}else{
				low = mid;
			}
		}

		out.println(high);
	}

	public static void main(String[] args) {
		out.flush();
		new Main().solve();
		out.close();
	}

	/* Input */
	private static final InputStream in = System.in;
	private static final PrintWriter out = new PrintWriter(System.out);
	private final byte[] buffer = new byte[2048];
	private int p = 0;
	private int buflen = 0;

	private boolean hasNextByte() {
		if (p < buflen)
			return true;
		p = 0;
		try {
			buflen = in.read(buffer);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (buflen <= 0)
			return false;
		return true;
	}

	public boolean hasNext() {
		while (hasNextByte() && !isPrint(buffer[p])) {
			p++;
		}
		return hasNextByte();
	}

	private boolean isPrint(int ch) {
		if (ch >= '!' && ch <= '~')
			return true;
		return false;
	}

	private int nextByte() {
		if (!hasNextByte())
			return -1;
		return buffer[p++];
	}

	public String next() {
		if (!hasNext())
			throw new NoSuchElementException();
		StringBuilder sb = new StringBuilder();
		int b = -1;
		while (isPrint((b = nextByte()))) {
			sb.appendCodePoint(b);
		}
		return sb.toString();
	}

	public int nextInt() {
		return Integer.parseInt(next());
	}

	public long nextLong() {
		return Long.parseLong(next());
	}

	public double nextDouble() {
		return Double.parseDouble(next());
	}
}