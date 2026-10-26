import java.util.Scanner;
import java.util.Arrays;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Set;

public class Main {
	char[][] fie;
	boolean[][] used;
	int h,w;
	int dx[]={1,0,-1,0,1,1,-1,-1};
	int dy[]={0,1,0,-1,1,-1,1,-1};

	int goindex(int first,int len){
		return first*200+len;
	}

	public void run(){
		Scanner sc = new Scanner(System.in);
		fie = new char[21][11];
		while(true){
			h=sc.nextInt();
			w=sc.nextInt();
			if(h+w==0)break;
			ArrayList<TreeSet<String>> magic = new ArrayList<TreeSet<String>>();
			for(int i=0;i<5200;i++){
				magic.add(new TreeSet<String>());
			}
			for(int i=0;i<h;i++){
				String line = sc.next();
				for(int j=0;j<w;j++){
					fie[j][i]=line.charAt(j); 
				}
			}
			String res="0";
			for(int i=0;i<h;i++){
				for(int j=0;j<w;j++){
					for(int k=0;k<8;k++){
						boolean[][] used = new boolean[21][11];
						String str="";
						int x=j,y=i;
						while(!used[x][y]){
							used[x][y]=true;
							str+=fie[x][y];
							if(str.length()>=2){
								if(magic.get(goindex(str.charAt(0)-'A',str.length())).contains(str)){
									if(res.length()<str.length() || (res.length()==str.length() && str.compareTo(res)<0))res=str;
								}else if(res.length()<=str.length())magic.get(goindex(str.charAt(0)-'A',str.length())).add(str);
							}
							y=(y+dy[k]+h)%h;
							x=(x+dx[k]+w)%w;
						}
					}
				}
			}
			System.out.println(res);
		}
	}

	public static void main(String[] args) {
		new Main().run();
	}
}