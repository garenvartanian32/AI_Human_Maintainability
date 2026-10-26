import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(System.in);
		while (true) {
			int H = scan.nextInt();
			if (H == 0) {
				scan.close();
				break;
			}
			int W = scan.nextInt();

			char[][] table = new char[H][];
			for (int i = 0; i < table.length; i++) {
				table[i] = scan.next().toCharArray();
			}

			String answer = "";
			int[] dir = { -1, 0, 1 };
			@SuppressWarnings("unchecked")
			ArrayList<String>[][] parts = new ArrayList[26][201];
			for (int i = 0; i < parts.length; i++) {
				for (int j = 0; j < parts[i].length; j++) {
					parts[i][j] = new ArrayList<String>();
				}
			}

			for (int i = 0; i < table.length; i++) {
				for (int j = 0; j < table[i].length; j++) {
					for (int k = 0; k < dir.length; k++) {
						for (int l = 0; l < dir.length; l++) {
							if (dir[k] == dir[l] && dir[k] == 0) {
								continue;
							}
							int nowi = i;
							int nowj = j;
							StringBuilder builder = new StringBuilder();
							builder.append(table[nowi][nowj]);
							while (true) {
								nowi = (nowi + dir[k] + H) % H;
								nowj = (nowj + dir[l] + W) % W;

								if (nowi == i && nowj == j) {
									break;
								}

								builder.append(table[nowi][nowj]);
								if (parts[table[i][j] - 'A'][builder.length()]
										.contains(builder.toString())) {
									if (builder.length() > answer.length()) {
										answer = builder.toString();
									} else if (builder.length() == answer
											.length()) {
										if (answer
												.compareTo(builder.toString()) > 0) {
											answer = builder.toString();
										}
									}
								} else if (builder.length() >= 2) {
									parts[table[i][j] - 'A'][builder.length()]
											.add(builder.toString());
								}
							}
						}
					}

				}
			}
			if (answer.equals("")) {
				System.out.println(0);
			} else {
				System.out.println(answer);
			}
		}

	}
}