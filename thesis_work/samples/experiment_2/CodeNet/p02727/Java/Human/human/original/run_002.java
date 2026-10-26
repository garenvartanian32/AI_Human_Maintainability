import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	
	public static void main(String[] args) throws IOException{
		Main mainObj = new Main();
		mainObj.solve();
	}
	
	public void solve() throws IOException{
		FastScanner fs = new FastScanner();
		
		int x = fs.nextInt();
		int y = fs.nextInt();
		int a = fs.nextInt();
		int b = fs.nextInt();
		int c = fs.nextInt();
		
		Comparator<Apple> appleComparator = Comparator.comparing(Apple::getUmasa).reversed();
		ArrayList<Apple> appleTreeSet = new ArrayList<>();
		
		for(int i = 0; i < a; i++){
			appleTreeSet.add(new Apple(0, fs.nextLong()));
		}
		for(int i = 0; i < b; i++){
			appleTreeSet.add(new Apple(1, fs.nextLong()));
		}
		for(int i = 0; i < c; i++){
			appleTreeSet.add(new Apple(2, fs.nextLong()));
		}
		
		appleTreeSet.sort(appleComparator);
		
		int red = 0;
		int green = 0;
		int noColor = 0;
		long sumOfUmasa = 0;
		
		for(Apple apple : appleTreeSet){
			if(apple.color == 0){
				if(red < x){
					red++;
					sumOfUmasa += apple.umasa;
				}
			}else if(apple.color == 1){
				if(green < y){
					green++;
					sumOfUmasa += apple.umasa;
				}
			}else{
				noColor++;
				sumOfUmasa += apple.umasa;
			}
			
			if(red+green+noColor == x+y){
				break;
			}
		}
		
		System.out.println(sumOfUmasa);
	}

	
	public class Apple{
		int color;//0: red, 1: green, 2: no color
		long umasa;
		
		public Apple(int color, long umasa){
			this.color = color;
			this.umasa = umasa;
		}
		
		public long getUmasa(){
			return umasa;
		}
	}
	
	public class FastScanner {
		
		BufferedReader reader;
		private StringTokenizer st;
		
		public FastScanner() {
			st = null;
			reader = new BufferedReader(new InputStreamReader(System.in));
		}

		
		public String next() throws IOException {
			if(st == null || !st.hasMoreElements()) {
				st = new StringTokenizer(reader.readLine());
			}
			return st.nextToken();
		}
		
		public String nextLine() throws IOException {
			st = null;
			String readLine = null;
			readLine = reader.readLine();
			return readLine;
		}
		
		public int nextInt() throws NumberFormatException, IOException {
			return Integer.parseInt(next());
		}
		
		public long nextLong() throws NumberFormatException, IOException {
			return Long.parseLong(next());
		}
		
		public int[] nextIntArr(int n) throws NumberFormatException, IOException {
			int[] retArr = new int[n];
			for(int i = 0; i < n; i++) {
				retArr[i] = nextInt();
			}
			return retArr;
		}
		
		public long[] nextLongArr(int n) throws NumberFormatException, IOException {
			long[] retArr = new long[n];
			for(int i = 0; i < n; i++) {
				retArr[i] = nextLong();
			}
			return retArr;
		}
		
		public void close() throws IOException {
			reader.close();
		}
	}
}


