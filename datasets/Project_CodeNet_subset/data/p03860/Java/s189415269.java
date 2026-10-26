import java.util.Scanner;
public class Main {
public static void main(String[] args) {
	Scanner input = new Scanner(System.in);
	String a = input.next();
	String first_letter="";
	first_letter=a.valueOf(a.charAt(0));
	String s=input.next();
	String second_letter=" ";
	second_letter=s.valueOf(s.charAt(0));
	String b = input.next();
	String third_letter="";
	third_letter=b.valueOf(b.charAt(0));
	System.out.println(first_letter + second_letter + third_letter);
}
}
