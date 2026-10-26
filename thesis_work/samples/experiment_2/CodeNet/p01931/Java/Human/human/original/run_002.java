import java.util.*;

class Main{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());
		String line = sc.nextLine();
		int count=0;
		int answer=n;
		for(int i=0;i<n;i++){
			if(line.charAt(i)=='x'){
				count+=1;
			}else{
				count=0;
			}
			if(count==2){
				answer=i;
				break;
			}
		}
		System.out.println(answer);
	}
}