import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int H1;
	static int M1;
	static int H2,M2;
	static int K;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		read();
	}

	private static void read() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] words = br.readLine().split("\\s+");
		
		H1  = Integer.valueOf(words[0]);
		M1 = Integer.valueOf(words[1]);
		H2 = Integer.valueOf(words[2]);
		M2 = Integer.valueOf(words[3]);
		K = Integer.valueOf(words[4]);
		
		int minute1 = H1*60 + M1;
		int minute2 = H2*60 + M2;
		int diff = minute2 - minute1;
		
		System.out.println(diff - K);

		br.close();
	}
}