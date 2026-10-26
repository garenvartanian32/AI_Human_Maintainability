import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	
	static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

    }
	
	public static void main(String[] args) {
		
        Scanner sc = new Scanner(System.in);
		int [] a = new int[105];
		int p=1,j=0,n=0;
		a[j++] = 1;
		String s;
		s = sc.nextLine();
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '-') {
				p--;
				a[j++] = -1;
			}
			if (s.charAt(i) == '+') {
				p++;
				a[j++] = 1;
			}
			if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
				n = n*10+(s.charAt(i)-'0');
			}
		}
		//System.out.println(n);
		for (int i = 0; i < j; i++) {
			while (p < n && a[i] > 0 && a[i] < n) {
				p++;
				a[i]++;
			}
			while (p > n && a[i] < 0 && a[i] > -n) {
				p--;
				a[i]--;
			}
		}
		//System.out.println(p);
		//System.out.println(a[0]);
		if (p != n) {
			System.out.println("Impossible");
			return;
		}
		System.out.println("Possible");
		for (int i = 0; i < j; i++) {
			if (i != 0) {
				if (a[i] > 0) {
					System.out.print("+ " + Math.abs(a[i]) + " ");
				} else {
					System.out.print("- " +Math.abs(a[i]) + " ");
				}
			} else {
				System.out.print(a[i] + " ");
			}
		}
		System.out.println("= " + n);
	}
}
