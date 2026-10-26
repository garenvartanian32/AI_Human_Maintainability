import java.util.Scanner;

public class Main{

	public static void main(String args[]){
		new Main().mainrun();
	}

	private Scanner scan;
	private int N,Q;
	private long[] A;

	private void mainrun() {
		scan = new Scanner(System.in);

		N = scan.nextInt();
		Q = scan.nextInt();

		A = new long[N+1];

		A[N] = 0;
		
		for(int i = 0;i < N;i++) {
			A[i] = scan.nextLong();
		}

		for(int i = 0;i < Q;i++) {
			System.out.println(Calculation(scan.nextLong()));
		}

		scan.close();
	}

	private long Calculation(long border) {
		long sum = 0,cnt = 0;
		int cursor_r = 0,cursor_l = 0;

		while(cursor_l < N) {
			if(sum + A[cursor_r] <= border && cursor_r < N) {
				sum += A[cursor_r++];
			}else {
				cnt += cursor_r - cursor_l;
				sum -= A[cursor_l++];
			}
		}

		return cnt;
	}
}

