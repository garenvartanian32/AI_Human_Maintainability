import java.awt.Point;
import java.io.*;
import java.util.*;

public class memorymanager {
 	
 	public static void main(String[] args) throws Exception {
 		if ("Satayev".equals(System.getProperty("user.name"))) {
			long start = System.nanoTime();
			new memorymanager().solve(new FileInputStream("input"));
			System.err.printf("Time elapsed: %d ms.\n", (System.nanoTime()-start)/1000000);
		}
		else
			new memorymanager().solve(System.in);
 	}

 	void solve(InputStream is) throws Exception {
		Scanner in = new Scanner(is);
		int T = in.nextInt(), n = in.nextInt(); 
		in.nextLine();
		int index = 1;
		
		TreeSet<Point> set = new TreeSet<Point>(new Comparator<Point>() {
			@Override
			public int compare(Point a, Point b) {
				return a.x - b.x;
			}
		});
		
		set.add(new Point(1, n+1));
		Point p = new Point();
		HashMap<Integer, Point> map = new HashMap<Integer, Point>();
		
		for (int t = 0; t < T; t++) {
			String operation = in.next();
			if ("alloc".equals(operation)) {
				int m = in.nextInt();
				p = null;
				for (Point q : set)
					if (q.y - q.x >= m) {
						p = q;
						break;
					}
				if ( p == null ) {
					System.out.println("NULL");
					continue;
				}
				map.put(index, new Point(p.x, p.x + m));
				set.remove(p);
				p.x += m;
				set.add(p);				
				System.out.println(index++);
			}
			else if ("erase".equals(operation)) {
				int m = in.nextInt();
				if (!map.containsKey(m)) {
					System.out.println("ILLEGAL_ERASE_ARGUMENT");
					continue;
				}
				set.add(map.remove(m));
				LinkedList<Point> list = new LinkedList<Point>();
				list.addAll(set);
				set.clear();
				p = list.pollFirst();
				while (!list.isEmpty()) {
					Point q = list.pollFirst();
					if (p.y == q.x) {
						p.y = q.y;
						continue;
					} else {
						set.add(p);
						p = q;
					}
				}
				set.add(p);
			}
			else {
				TreeMap<Point, Integer> mp = new TreeMap<Point, Integer>(new Comparator<Point>() {
					@Override
					public int compare(Point a, Point b) {
						return a.x - b.x;
					}
				});
				for (int i : map.keySet())
					mp.put(map.get(i), i);
				
				map.clear();
				int last = 1;
				p = new Point();
				for (Point q : mp.keySet()) {
					p.y = q.y - (q.x - last);
					p.x = last;
					last = p.y;
					map.put(mp.get(q), new Point(p));
				}
				
				set.clear();
				set.add(new Point(last, n+1));
			}
			if (t + 1 < T)
				in.nextLine();
		}		
 	}

}