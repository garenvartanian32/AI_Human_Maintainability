import java.util.*;
public class Main 
{
	public static void main(String[] args) 
	{
		Scanner stdIn = new Scanner(System.in);
		int n = stdIn.nextInt();
		while(n-- != 0)
		{
			String str = stdIn.next();
			Deque<Character> train = new ArrayDeque<Character>();
			Set<Character> marked = new TreeSet<Character>();
			train.addFirst(str.charAt(0));
			marked.add(str.charAt(0));
			for(int i = 1; i < str.length(); i += 3)
			{
				if(str.charAt(i) == '-' && str.charAt(i + 1) == '>')
				{
					if(!marked.contains(str.charAt(i + 2)))
					{
						marked.add(str.charAt(i + 2));
						train.addLast(str.charAt(i + 2));
					}
				}
				else if(str.charAt(i) == '<' && str.charAt(i + 1) == '-')
				{
					if(!marked.contains(str.charAt(i + 2)))
					{
						marked.add(str.charAt(i + 2));
						train.addFirst(str.charAt(i + 2));
					}
				}
			}
			Iterator it = train.iterator();
			while(it.hasNext())
			{
				System.out.print(it.next());
			}
			System.out.println("");
		}
	}
}