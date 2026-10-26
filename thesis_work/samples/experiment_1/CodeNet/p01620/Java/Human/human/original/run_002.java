import java.util.Scanner;
public class Main{
	public static void main(String[] args){
        new Main().run();
    }
	public void run(){
		Scanner scan = new Scanner(System.in);
		while(scan.hasNext()){
			int n = scan.nextInt();
			if(n == 0){
				break;
			}
			int[] k = new int[n];
			for(int i = 0;i < n;i++){
				k[i] = scan.nextInt();
			}
			char[] st = scan.next().toCharArray();
			char[] al = new char[52];
			for(int i = 0;i < 26;i++){
				al[i] = (char)(97+i);
				al[i+26] = (char)(65+i);
			}
			int h;
			for(int i = 0,j = 0;i < st.length;i++,j++){
				h = ((int)st[i] > 96)?(int)st[i]-97:(int)st[i]-39;
				if(j == n){
					j = 0;
				}
				h -= k[j];
				if(h < 0){
					h += 52;
				}
				System.out.print(al[h]);
			}
			System.out.println();
		}
	}
}