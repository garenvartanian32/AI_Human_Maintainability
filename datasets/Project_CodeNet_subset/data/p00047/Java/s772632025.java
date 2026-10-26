import java.util.*;

class Main {
	Scanner sc = new Scanner(System.in);

	public void run() {
		String now="A";
		while(sc.hasNext()) {
			String[] str=sc.next().split(",");
			if(now.equals(str[0]))now=str[1];
			else if(now.equals(str[1])) now=str[0];
		}
		ln(now);
	}

	public static void main(String[] args) {
		new Main().run();
	}

	public static void pr(Object o) {
		System.out.print(o);
	}

	public static void ln(Object o) {
		System.out.println(o);
	}

	public static void ln() {
		System.out.println();
	}
}