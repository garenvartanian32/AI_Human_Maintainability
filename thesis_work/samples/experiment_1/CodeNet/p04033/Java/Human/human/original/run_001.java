
import java.util.*;
import java.io.*;
public class Main{
	public static void main(String[] args) throws IOException {
		try(BufferedReader kb= new BufferedReader(new InputStreamReader(System.in))){
			StringTokenizer st = new StringTokenizer(kb.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(a<=0 && b>=0){
				System.out.println("Zero");
			}
			else if (a==b){
				System.out.println("Positive");
			}
			else if(a>0){
				System.out.println("Positive");
			}
			else if(((Math.abs(a)-Math.abs(b))%2)==0){
				System.out.println("Negative");
			}
			else{
				System.out.println("Positive");
			}
			
		}
	}
}