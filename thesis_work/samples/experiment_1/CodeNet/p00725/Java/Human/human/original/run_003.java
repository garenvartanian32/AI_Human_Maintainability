import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main{
	public static int[][] map;
	public static int w, h;
	public static final int[] dx = {1, 0, -1, 0};
	public static final int[] dy = {0, 1, 0, -1};
	public static int gx, gy;
//	public static int turn;
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		while(true){
			w = in.nextInt();
			h = in.nextInt();
			if(w == 0) break;
			map = new int[h][w];
			int sx = 0;
			int sy = 0;
			best = 11;
			for(int i=0; i<h; i++){
				for(int j=0; j<w; j++){
					map[i][j] = in.nextInt();
					if(map[i][j] == 2){
						sx = j;
						sy = i;
						map[i][j] = 0;
					}else if(map[i][j] == 3){
						gx = j;
						gy = i;
					}
				}
			}
			dfs(sy, sx, 0);
			System.out.println(best==11? -1: best);
		}
	}
	
	public static int best;
	public static void dfs(int y, int x, int depth){
		if(depth == 10) return;
//		if(y == gy && x == gx){
//			best = Math.min(best, depth);
//			return;
//		}
		for(int i=0; i<4; i++){
			int nx = x+dx[i];
			int ny = y+dy[i];
			if(out(ny, nx) || map[ny][nx] == 1) continue;
			while(!out(ny, nx) && map[ny][nx] == 0){
				nx += dx[i];
				ny += dy[i];
			}
			if(out(ny, nx)) continue;
			if(map[ny][nx] == 3){
				best = Math.min(best, depth+1);
				return;
			}
			map[ny][nx] = 0;
			nx -= dx[i];
			ny -= dy[i];
			dfs(ny, nx, depth+1);
			map[ny+dy[i]][nx+dx[i]] = 1;
		}
	}
	
	public static boolean out(int y, int x){
		return x < 0 || x >= w || y < 0 || y >= h;
	}
}