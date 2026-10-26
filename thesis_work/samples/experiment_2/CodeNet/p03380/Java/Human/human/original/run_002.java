import java.util.*;

public class Main {
	int n;
	List<Integer> a = new ArrayList<>();
	int max, result;

	private Main(Scanner in) {
		n = Integer.parseInt(in.nextLine());
		String[] tokens = in.nextLine().split(" ");
		for (int i = 0; i < n; ++i) {
			a.add(Integer.parseInt(tokens[i]));
		}
	}

	private void calc() {
		// 最大値をとる
		max = a.stream().max(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				if (o1 < o2) {
					return -1;
				} else if (o1 > o2) {
					return 1;
				} else {
					return 0;
				}
			}
		}).get().intValue();
		double half = (double) max / 2.0;
		result = -1;
		double diff = Double.MAX_VALUE;
		for (Integer val : a) {
			if (val != max && diff > Math.abs((double) val - half)) {
				diff = Math.abs((double) val - half);
				result = val;
			}
		}
	}

	private void show() {
		System.out.println(max + " " + result);
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Main ins = new Main(in);
		in.close();
		ins.calc();
		ins.show();
	}

}