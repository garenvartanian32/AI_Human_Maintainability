import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

public class Main {
	public static void main(String[] args) {
		Task task = new Task();
		task.getData();
		task.solve();
		task.printAnswer();
	}
}

class Task {
	String data;

	//StringBuilder answer = new StringBuilder();
	int answer = 0;

	public void getData() {
		Scanner console = new Scanner(System.in);

		console.next();
		data = console.next();

		console.close();
	}

	public void solve() {
		int countA = 0, countI = 0, countF = 0;
		for (int i = 0; i < data.length(); ++i) {
			if (data.charAt(i) == 'A') {
				++countA;
			}
			if (data.charAt(i) == 'I') {
				++countI;
			}
			if (data.charAt(i) == 'F') {
				++countF;
			}
		}
		//for allin
		answer += (countI > 0 ? 0 : countA);
		//for in
		answer += (countI > 1 ? 0 : countI);
		
	}

	public void printAnswer() {
		System.out.print(answer);
	}
}

class Point {
	Integer x;
	Integer y;

	Point(Integer x, Integer y) {
		this.x = x;
		this.y = y;
	}
}