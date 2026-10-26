import java.io.*;
public class Main 
{
	public static void main(String[] args) 
	{
		BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
		try
		{
			String str = b.readLine();
			StringBuilder str2 = new StringBuilder();
			for(int i = 0; i < str.length(); ++i)
			{
				if('a' <= str.charAt(i) && str.charAt(i) <= 'z')
				{
					str2.append((char)(str.charAt(i) - 'a' + 'A'));
				}
				else if('A' <= str.charAt(i) && str.charAt(i) <= 'Z')
				{
					str2.append((char)(str.charAt(i) - 'A' + 'a'));
				}
				else
				{
					str2.append(str.charAt(i));
				}
			}
			System.out.println(str2);
		}
		catch(Exception e)
		{
			e.getStackTrace();
		}
	}
}