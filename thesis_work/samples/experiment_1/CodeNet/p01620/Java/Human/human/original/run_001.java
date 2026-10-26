import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	String[] TABLE = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

	public static void main(String[] args) {
		new Main().run();
	}
	void run(){
		Scanner sc = new Scanner(System.in);
		while(true){
			int a,b,c; String target;
			Queue <Integer> q = new LinkedList<Integer>();

			a = sc.nextInt();

			if(a == 0){
				break;
			}

			for(int i=0; i < a ; i++){
				q.add(sc.nextInt());
			}

			target = sc.next();
			String[] tgtchr = new String[target.length()];
			for(int i=0; i < target.length(); i++){
				tgtchr[i] = String.valueOf(target.charAt(i));
			}

			for(int i=0; i < target.length(); i++){
				for(int j=0; j < TABLE.length; j++){
					//System.out.println("out");
					if(TABLE[j].equals(tgtchr[i])){
						//System.out.println("in");
						c = q.poll();
						b = j - c;
						if(b < 0){
							b = 52 + b;
						}
						q.add(c);
						System.out.print(TABLE[b]);
					}
				}
			}
			System.out.println();
		}
	}
}