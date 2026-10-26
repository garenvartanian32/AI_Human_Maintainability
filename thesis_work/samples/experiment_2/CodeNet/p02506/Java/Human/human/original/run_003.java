
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String w,t;
		String[] strArray;
		int n=0;

		w = br.readLine().toLowerCase();


		while(!(t=br.readLine()).equals("END_OF_TEXT")){
			strArray = t.split(" ");

			for(int i=0; i<strArray.length; i++){
				if(strArray[i].toLowerCase().equals(w)){
					n++;
				}
			}
		}
		System.out.println(n);
	}
}