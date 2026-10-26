import java.util.*;
import java.io.*;

public class zeptothief{
	public static void main(String args[]) throws Exception{
		Scanner input=new Scanner(System.in);
		int N=input.nextInt();
		String level=input.next();
//		System.out.println(level);
		for(int i=0; i<N; i++){
			loop:
			for(int j=1; j<N; j++){
//				System.out.println(i+" "+j);
				for(int k=0; k<5; k++){
//					System.out.print(level.charAt(i+j*k));
					if((i+j*k)>=level.length()){
						continue loop;
					}
					if(level.charAt(i+j*k)!='*'){
						continue loop;
					}
				}
				System.out.println("yes");
				return;
			}
		}
		System.out.println("no");
	}
}