import java.util.ArrayList;
import java.util.Scanner;

public class Main{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		int a,b,flag = 1;
		
		while(true){
			a = sc.nextInt();
			b = sc.nextInt();
			
			if(a == 0 && b == 0)break;
			if(flag == 0)
				System.out.println();
			flag = 0;
			
			for(int i = a; i <= b; i++){
				if(i % 400 == 0){
					list.add(i);
					continue;
				}
				else if(i % 100 == 0){
					continue;
				}
				else if(i % 4 == 0){
					list.add(i);
				}
			}
			if(list.size() == 0)
				System.out.println("NA");
			else
				for(int i = 0; i < list.size(); i++){
					System.out.println(list.get(i));
				}
			
			list.clear();
		}
		sc.close();
	}
}