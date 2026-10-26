import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int d = sc.nextInt();
		int a = sc.nextInt();
		Obj[] arr = new Obj[n];
		for (int i = 0; i < n; i++) {
			Obj o = new Obj();
			o.x = sc.nextInt();
			o.h = sc.nextInt();
			arr[i] = o;
		}
		sc.close();

		Arrays.sort(arr, (o1, o2) -> o1.x - o2.x);
		long ans = 0;
		long rem = 0;
		TreeMap<Long, Long> map = new TreeMap<>();
		for (Obj o : arr) {
			int time = (o.h + a - 1) / a;
			Map<Long,Long> hm = map.headMap((long) o.x);
			for (long l : hm.values()) {
				rem += l;
			}
			long val = ans - rem;
			val = Math.max(time - val, 0);
			ans += val;
			hm.clear();
			map.put((long) o.x + 2 * d, val);
		}
		System.out.println(ans);
	}

	static class Obj {
		int x, h;
	}
}
