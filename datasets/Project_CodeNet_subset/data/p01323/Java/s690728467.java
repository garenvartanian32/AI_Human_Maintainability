import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static final int X_MAX = 6;
	public static final int Y_MAX = 12;

	public static final int[][] move_dir = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	
	public static boolean is_ok(int x, int y){
		if(x < 0 || x >= X_MAX || y < 0 || y >= Y_MAX){
			return false;
		}else{
			return true;
		}
	}
	
	public static int detect(int x, int y, char[][] map, boolean[][] visited){
		visited[y][x] = true;
		
		int ret = 1;
		for(int[] move : move_dir){
			final int nx = x + move[0];
			final int ny = y + move[1];
			
			if(!is_ok(nx, ny)){
				continue;
			}else if(visited[ny][nx]){
				continue;
			}else if(map[y][x] != map[ny][nx]){
				continue;
			}
			
			ret += detect(nx, ny, map, visited);
		}
		
		return ret;
	}
	
	public static void erase_dfs(int x, int y, char[][] map){
		char old = map[y][x];
		map[y][x] = '.';
		
		for(int[] move : move_dir){
			final int nx = x + move[0];
			final int ny = y + move[1];
			
			if(!is_ok(nx, ny)){
				continue;
			}else if(map[ny][nx] == 'O'){
				map[ny][nx] = '.';
				continue;
			}else if(old != map[ny][nx]){
				continue;
			}
			
			erase_dfs(nx, ny, map);
		}
	}
	
	public static boolean erase(char[][] map, boolean[][] checked){
		for(int i = 0; i < Y_MAX; i++){
			Arrays.fill(checked[i], false);
		}
		
		boolean erased = false;
		
		for(int y = 0; y < Y_MAX; y++){
			for(int x = 0; x < X_MAX; x++){
				if(map[y][x] == 'O' || map[y][x] == '.'){
					continue;
				}else if(checked[y][x]){
					continue;
				}
				
				int chain = detect(x, y, map, checked);
				
				if(chain >= 4){
					erase_dfs(x, y, map);
					erased = true;
				}
			}
		}
		
		return erased;
	}
	
	public static void fall(char[][] map){
		for(int x = 0; x < X_MAX; x++){
			boolean found = false;
			
			for(int y = 0; y < Y_MAX - 1; y++){
				if(map[y][x] != '.' && map[y + 1][x] == '.'){
					found = true;
					map[y+1][x] = map[y][x];
					map[y][x] = '.';
				}
			}
			
			if(found){
				x--;
			}
			//print(map);
		}
	}
	
	public static void print(char[][] map){
		for(int i = 0; i < Y_MAX; i++){
			for(int j = 0; j < X_MAX; j++){
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("-------------");
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		char[][] map = new char[Y_MAX][X_MAX];
		boolean[][] checked = new boolean[Y_MAX][X_MAX];
		
		final int T = sc.nextInt();
		
		for(int t = 0; t < T; t++){
			for(int i = 0; i < Y_MAX; i++){
				char[] input = sc.next().toCharArray();
				
				for(int j = 0; j < X_MAX; j++){
					map[i][j] = input[j];
				}
			}
			
			int count = 0;
			//print(map);
			while(true){
				boolean ok = erase(map, checked);
				
				if(!ok){
					break;
				}
				//print(map);
				fall(map);
				//print(map);
				count++;
			}
			
			System.out.println(count);
		}

	}

}