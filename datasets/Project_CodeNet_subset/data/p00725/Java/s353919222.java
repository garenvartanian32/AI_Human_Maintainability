import java.util.*;
import static java.util.Arrays.*;
import static java.lang.Math.*;

public class Main {
	
	static Scanner sc = new Scanner(System.in);
	static int w, h, sx, sy;
	static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
	static int[][] field = new int[22][22];
	
	public static void main(String[] args) {
		while(read()) {
			System.out.println(solve());
		}
	}
	
	static boolean read() {
		w = sc.nextInt();
		h = sc.nextInt();
		if(w == 0) 
			return false;
		
		for(int i = 0; i < field.length; i++)
			fill(field[i], -1);
		
		for(int i = 1; i <= h; i++) {
			for(int j = 1; j <= w; j++) {
				field[i][j] = sc.nextInt();
				if(field[i][j] == 2) {
					sx = j; sy = i;
					field[i][j] = 0;
				}
			}
		}
		return true;
	}
	
	static int solve() {
		int ans = rec(sx, sy, 0);
		return (ans > 10) ? -1 : ans;
	}
	
	static int rec(int x, int y, int step) {
		//ツ探ツ催オツ氾債暗債づーツ陳エツつヲツづつ「ツづゥツづ個づ、ツ妥つソツ静伉づィ
		if(step >= 10)
			return step + 1;
		
		int ans = 11;
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i], ny = y + dy[i];
			if(field[ny][nx] == 1 || field[ny][nx] == -1)
				continue;
			while(field[ny][nx] == 0) {
				nx += dx[i]; ny += dy[i];
			}
			if(field[ny][nx] == 3) {
				ans = step + 1;
				break;
			}
			
			if(field[ny][nx] == 1) {
				field[ny][nx] = 0;
				ans = min(ans, rec(nx - dx[i], ny - dy[i], step + 1));
				field[ny][nx] = 1;
			}
		}
		return ans;
	}
}