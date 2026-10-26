import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.OptionalInt;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.IntStream;

public class Main {

	public static void main(String[] args) {
		read().stream()//
				.map(new Main()::execute)//
				.forEach(System.out::println);
	}

	private static List<Integer> read() {
		Scanner sc = new Scanner(System.in);

		int q = sc.nextInt();
		List<Integer> valueList = new ArrayList<>();
		for (int i = 0; i < q; i++) {
			valueList.add(sc.nextInt());
		}

		sc.close();
		return valueList;
	}

	public int execute(int originalValue) {
		return execute(originalValue, new HashSet<>());
	}

	public int execute(int originalValue, Set<Integer> valueSet) {
		if (originalValue < 10) {
			return valueSet.size();
		} else if (valueSet.contains(originalValue)) {
			return -1;
		} else {
			valueSet.add(originalValue);
			return execute(next(originalValue).getAsInt(), copy(valueSet));
		}
	}

	private <T> Set<T> copy(Set<T> source) {
		Set<T> dest = new HashSet<T>();
		dest.addAll(source);
		return dest;
	}

	private OptionalInt next(int value) {
		String str = String.valueOf(value);
		return IntStream.range(1, str.length())//
				.map(i -> Integer.parseInt(str.substring(0, i)) * Integer.parseInt(str.substring(i))).max();
	}
}