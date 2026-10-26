import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		String str[] = br.readLine().split(" ");

		long a[] = new long[n];
		long b[] = new long[n];

		for (int i = 0; i < n; i++) {
			a[i] = Long.parseLong(str[i]);
		}

		str = br.readLine().split(" ");
		for (int i = 0; i < n; i++) {
			b[i] = Long.parseLong(str[i]);
		}

		long cntA = 0;
		long cntB = 0;

		for (int i = 0; i < n; ++i) {
			if (a[i] > b[i])
				cntA += a[i] - b[i];
			if (b[i] > a[i])
				cntB += (b[i] - a[i]) / 2;
		}

		if (cntB >= cntA) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}
	}
}