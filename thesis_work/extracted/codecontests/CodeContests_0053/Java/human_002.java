import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.parseInt;

/**
 * Pigeonhole principle
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line;
		String[] words;

		int N = parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		Map<Integer, List<Integer>> map = new HashMap<>();

		String ans = "";

		while (st.hasMoreTokens()) {
			int a = parseInt(st.nextToken());
			int m = a % (N - 1);
			map.putIfAbsent(m, new ArrayList<>());
			map.get(m).add(a);
			if (map.get(m).size() > 1) {
				ans += map.get(m).get(0);
				ans += " ";
				ans += map.get(m).get(1);
				break;
			}
		}

		System.out.println(ans);
	}
}


