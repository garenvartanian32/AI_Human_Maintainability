import java.util.*;

public class Main{

	private void doit(){
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext()){
			String s = sc.next();
			if(s.charAt(0) == 'x' && s.charAt(s.length() - 1) == 'x'){
				System.out.println("x");
			}
			else{
				System.out.println("o");
			}
		}
	}

	private void debug(Object... o) {
		System.out.println("debug = " + Arrays.deepToString(o));
	}

	public static void main(String[] args) {
		new Main().doit();
	}
}