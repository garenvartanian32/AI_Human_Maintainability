import java.util.*;

public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while(true){
			int w = sc.nextInt();
			int h = sc.nextInt();
			if(w==0 && h==0) break;
			
			int[][] map = new int[h][w];
			for(int i=0;i<h;i++){
				for(int j=0;j<w;j++){
					map[i][j] = sc.nextInt();
				}
			}
			
			int[] c = new int[4];
			for(int i=0;i<4;i++){
				c[i] = sc.nextInt();
			}
			
			int[][][] cost = new int[4][h][w];
			for(int i=0;i<4;i++){
				for(int j=0;j<h;j++){
					for(int k=0;k<w;k++){
						cost[i][j][k] = Integer.MAX_VALUE;
					}
				}
			}
			cost[0][0][0] = 0;
			
			LinkedList<Integer> x = new LinkedList<Integer>();
			LinkedList<Integer> y = new LinkedList<Integer>();
			LinkedList<Integer> d = new LinkedList<Integer>();
			x.offer(0);
			y.offer(0);
			d.offer(0);
			int p, q, r, s;
			int[] e = new int[4];
			while(d.size()!=0){
				p = x.poll();
				q = y.poll();
				r = d.poll();
				s = cost[r][q][p];
				for(int i=0;i<4;i++){
					e[r] = c[i];
					if(map[q][p]==i) e[r] = 0;
					r++;
					if(r==4) r = 0;
				}
				
				if(p!=w-1 && cost[0][q][p+1]>s+e[0]){
					cost[0][q][p+1] = s+e[0];
					x.offer(p+1);
					y.offer(q);
					d.offer(0);
				}
				if(q!=h-1 && cost[1][q+1][p]>s+e[1]){
					cost[1][q+1][p] = s+e[1];
					x.offer(p);
					y.offer(q+1);
					d.offer(1);
				}
				if(p!=0 && cost[2][q][p-1]>s+e[2]){
					cost[2][q][p-1] = s+e[2];
					x.offer(p-1);
					y.offer(q);
					d.offer(2);
				}
				if(q!=0 && cost[3][q-1][p]>s+e[3]){
					cost[3][q-1][p] = s+e[3];
					x.offer(p);
					y.offer(q-1);
					d.offer(3);
				}
			}
			
			int ans = Math.min(cost[0][h-1][w-1], cost[1][h-1][w-1]);
			System.out.println(ans);
		}	
	}	
}