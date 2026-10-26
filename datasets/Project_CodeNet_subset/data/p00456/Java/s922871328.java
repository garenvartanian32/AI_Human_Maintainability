import java.util.Scanner;
import java.util.Arrays;

public class Main {

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int[] waseda = new int[10];
		int[] keio = new int[10];
		for(int i=0; i<10; i++) {
			waseda[i]=in.nextInt();
		}
		for(int k=0; k<10; k++) {
			keio[k]=in.nextInt();
		}
		Arrays.sort(waseda);
		int pointw = waseda[7]+waseda[8]+waseda[9];
		Arrays.sort(keio);
		int pointk = keio[7]+keio[8]+keio[9];
		
		System.out.println(pointw+" "+pointk);
		}
	}