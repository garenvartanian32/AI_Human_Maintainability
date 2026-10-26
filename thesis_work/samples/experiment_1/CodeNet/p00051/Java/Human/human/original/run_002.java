import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int all = Integer.parseInt(br.readLine());
		for (int i = 0; i < all; i++) {
			String var = br.readLine();
			char[] s = var.toCharArray();
			Arrays.sort(s);
			char[] rev = new char[s.length];
			for (int j = 0; j < s.length; j++) {
				rev[j] = s[s.length - j - 1];
			}

			int min = Integer.parseInt(String.valueOf(s));
			int max = Integer.parseInt(String.valueOf(rev));
			System.out.println(max - min);

		}
	}
}