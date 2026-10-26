import java.util.*;
import java.io.*;

public class Solution{
	ArrayList<Integer>[] list;
	boolean[] visited;
	int dest;
	public Solution(){
		Scanner sc = new Scanner(System.in);
		int tests = sc.nextInt();
		
		for(int t=0; t<tests; t++){
			int n = sc.nextInt();
			int m = sc.nextInt();
			int a = sc.nextInt();
			int b = sc.nextInt();
			
			list = new ArrayList[n+1];
			for(int i=1; i<=n; i++){
				list[i] = new ArrayList<Integer>();
			}
			
			visited = new boolean[n+1];
			dest = b;
			
			for(int i=0; i<m; i++){
				int n1 = sc.nextInt();
				int n2 = sc.nextInt();
				list[n1].add(n2);
				list[n2].add(n1);
			}
			
			dfs(a);
			
			int bOuter = 0;
			for(int i=1; i<=n; i++){
				if(!visited[i]){
					bOuter++;
				}
				else{
					visited[i] = false;
				}
			}
			
			dest = a;
			dfs(b);
			
			int aOuter = 0;
			for(int i=1; i<=n; i++){
				if(!visited[i]){
					aOuter++;
				}
			}
			
			long res = ((bOuter*1L)*aOuter);
			
			System.out.println(res);
		}
	}
	
	public void dfs(int node){
		if(visited[node]){
			return;
		}
		
		visited[node] = true;
		if(node == dest){
			return;
		}
		
		for(int child: list[node]){
			if(!visited[child]){
				dfs(child);
			}
		}
	}
	
	public static void main(String[] args){
		new Solution();
	}
}