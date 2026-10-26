import java.util.*;

public class Main {
	
	private void doit(){
		Scanner sc = new Scanner(System.in);
		int dataset =   sc.nextInt();
		while(dataset-- > 0){
			int nowx = 0, nowy = 0;
			int maxdis = 0, ansx = 0, ansy = 0;
			while(true){
				int x = sc.nextInt();
				int y = sc.nextInt();
				if((x | y) == 0) break;
				nowx += x;
				nowy += y;
				int dis = nowx * nowx + nowy * nowy;
				if(maxdis < dis){
					maxdis = dis;
					ansx = nowx;
					ansy = nowy;
				}
				else if(maxdis == dis && (ansx < nowx)){
					ansx = nowx;
					ansy = nowy;
				}
			}
			System.out.println(ansx + " " + ansy);
		}
	}
	

	private void debug(Object... o) {
		System.out.println("debug = " + Arrays.deepToString(o));
	}

	public static void main(String[] args) {
		new Main().doit();
	}
}