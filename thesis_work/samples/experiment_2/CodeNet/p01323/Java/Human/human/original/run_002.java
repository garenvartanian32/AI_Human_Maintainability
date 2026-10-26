
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		new Main().run();
	}

	private void run() {
		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
		while (t-- > 0) {
			map = new char[h][w];
			for (int i = 0; i < h; i++)
				map[i] = scanner.next().toCharArray();
			int ans = 0;
			v = new boolean[h][w];
			while (true) {
				boolean flag = false;
				for (int i = 0; i < h; i++)
					Arrays.fill(v[i], false);
				for (int y = 0; y < h; y++) {
					for (int x = 0; x < w; x++) {
						if (map[y][x] == '.' || map[y][x] == 'O' || v[y][x])
							continue;
						step = 0;
						if (!slove(y, x, map[y][x]))
							continue;
						flag = true;
						dfs(y, x, map[y][x]);
					}
				}
				if (!flag) {
					System.out.println(ans);
					break;
				}
				ans++;
				for (int i = 0; i < 11; i++) {
					for (int y = 0; y < 11; y++) {
						for (int x = 0; x < w; x++) {
							if (map[y][x] != '.' && map[y + 1][x] == '.') {
								map[y + 1][x] = map[y][x];
								map[y][x] = '.';
							}
						}
					}
				}
			}
		}
	}

	private void dfs(int y, int x, char c) {
		map[y][x] = '.';
		for (int[] m : move) {
			int ny = y + m[0];
			int nx = x + m[1];
			if (!isOK(ny, nx))
				continue;
			if (map[ny][nx] == 'O') {
				map[ny][nx] = '.';
				continue;
			}
			if (map[ny][nx] != c)
				continue;
			dfs(ny, nx, c);
		}

	}

	private boolean slove(int y, int x, char c) {
		step++;
		v[y][x] = true;
		if (step == 4)
			return true;
		for (int[] m : move) {
			int ny = y + m[0];
			int nx = x + m[1];
			if (!isOK(ny, nx))
				continue;
			if (map[ny][nx] != c || v[ny][nx])
				continue;
			if (slove(ny, nx, c))
				return true;
		}
		return false;
	}

	private boolean isOK(int ny, int nx) {
		return 0 <= ny && ny < h && 0 <= nx && nx < w;
	}

	int h = 12, w = 6;
	int step;
	char[][] map;
	boolean[][] v;
	int[][] move = { { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 } };
}