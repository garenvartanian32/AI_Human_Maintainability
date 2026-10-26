

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	private static int n;
	
	 public static void main(String args[]) throws Exception {
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	        n = Integer.parseInt(br.readLine());
	        
	        for(int j=0;j<n;j++){
	        	String line = br.readLine();
	        	char[] s = line.toCharArray();
	        	Arrays.sort(s);
	        	char[] rev = new char[s.length];

	        	for(int i=0;i<s.length;i++){
	        		rev[i] = s[s.length-i-1];
	        	}
	        	int a = Integer.parseInt(String.valueOf(s));
	        	int b = Integer.parseInt(String.valueOf(rev));
	        	System.out.println(b-a);
	        }	 
	    }
}