import java.util.*;

public class Main {

	static int startX, startY;
	static int[] moveX = {0,0,-1,1};
	static int[] moveY = {-1,1,0,0};
	static int w, h;
	static int maxMove = 10;
	static int ans;

	public static void finish (int move) {
		if (ans == -1) {
			ans = move;
		} else {
			ans = Math.min(move, ans);
		}
	}

	public static boolean ok (int y, int x) {
		return 0 <= y && y < h && 0 <= x && x < w;
	}

	public static void show (int[][] field) {

		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				System.out.printf("%2d ", field[i][j]);
			}
			System.out.println();
		}
		System.out.println();

	}

	public static void dfs (int y, int x, int move, int[][] field) {
//		System.out.println(y + "," + x + "   : " + move);
//		show(field);

		if (move == maxMove) return;

		for (int i = 0; i < moveX.length; i++) {

			if (ok(y + moveY[i],x + moveX[i]) &&
				field[y + moveY[i]][x + moveX[i]] != 1) {
				
				int nextY = y, nextX = x;

				while (ok(nextY + moveY[i],nextX + moveX[i])) {
					if (field[nextY + moveY[i]][nextX + moveX[i]] == 1) break;
					nextY += moveY[i];
					nextX += moveX[i];
					if (field[nextY][nextX] == 3) {
						finish(move + 1);
						return;
					}
				}

				boolean flag = false;

				if (ok(nextY + moveY[i],nextX + moveX[i])) {
					field[nextY + moveY[i]][nextX + moveX[i]] = 0;
					flag = true;
				} else {
					continue;
				}

				dfs(nextY,nextX,move + 1,field);

				if (flag) {
					field[nextY + moveY[i]][nextX + moveX[i]] = 1;
				}

			}

		}

	}

	public static void main(String[] args) {

		int[][] field;
		Scanner sc = new Scanner(System.in);

		while (true) {

			w = sc.nextInt();
			h = sc.nextInt();
			if (w == 0 && h == 0) break;
			ans = -1;
			field = new int[h][w];
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					field[i][j] = sc.nextInt();
					if (field[i][j] == 2) {
						startX = j;
						startY = i;
					}
				}
			}
			dfs(startY, startX, 0, field);
			System.out.println(ans);
		}

	}

}