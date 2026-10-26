
import java.util.*;
import java.lang.*;
import java.io.*;

public class Ideone
{
	public static void main (String[] args) throws java.lang.Exception
	{
		Scanner in=new Scanner(System.in);
		String name = in.nextLine().toLowerCase();
		int x = in.nextInt();
		String output="";
		for(int i=0; i < name.length(); i++) {
			char c = name.charAt(i);
			if((int) c < x +97){
				output += Character.toUpperCase(c);
			}else{
				output += Character.toLowerCase(c);
			}
			
		}
		System.out.print(output);
	}
}