import java.util.*;
public class Main 
{
	public static void main(String[] args) 
	{
		Scanner stdIn = new Scanner(System.in);
		String str = stdIn.next();
		if(str.charAt(0) == str.charAt(str.length() - 1) && str.charAt(0) == 'x')
		{
			System.out.println('x');
		}
		else
		{
			System.out.println('o');
		}
	}
}