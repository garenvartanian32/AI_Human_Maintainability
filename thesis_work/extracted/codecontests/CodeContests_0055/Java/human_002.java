import java.util.*;
class Main{
	static Scanner s = new Scanner(System.in);
	public static void main(String[] args){
		String[] suit = {"S ","H ","C ","D "};
		TreeSet<String> in = new TreeSet<>();
		s.nextLine();
		while(s.hasNextLine()) {
			in.add(s.nextLine());
		}
		String str;
		for(String s:suit) {
			for(int i=1;i<=13;++i) {
				str = s+i;
				if(!in.contains(str)) {
					System.out.println(str);
				}
			}
		}
	}
}