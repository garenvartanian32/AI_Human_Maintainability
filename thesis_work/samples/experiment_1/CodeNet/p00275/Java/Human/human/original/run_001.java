import java.util.*;
public class Main{
    public static void main(String[] args){
	new Main().run();
    }
    Scanner sc = new Scanner(System.in);

    int field, p;
    int[] players;
    char[] yama;

    void run(){
	while(sc.hasNext()){
	    p = sc.nextInt();
	    if(p!=0){
		players = new int[p];
		field = 0;
		yama = sc.next().toCharArray();
		solve();
	    }
	}
    }

    void solve(){
	int turn = 0;
	for(int i=0; i<yama.length; i++){
	    if(yama[i]=='M'){
		players[turn]++;
	    }
	    else if(yama[i]=='S'){
		field += players[turn]+1;
		players[turn] = 0;
	    }
	    else if(yama[i]=='L'){
		players[turn] += field+1;
		field = 0;
	    }
	    turn = (turn+1)%p;
	}
	Arrays.sort(players);

	for(int i=0; i<players.length; i++)
	    System.out.print(players[i]+" ");
	System.out.println(field);
    }
}