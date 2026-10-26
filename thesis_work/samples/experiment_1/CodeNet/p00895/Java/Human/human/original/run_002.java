import java.util.*;

class Main{

    int h, w;
    int[] dx = {0, 0, 1, -1, 1, 1, -1, -1};
    int[] dy = {1, -1, 0, 0, 1, -1, 1, -1};
    char[][] donut;

    void solve(){
        Scanner sc = new Scanner(System.in);

        while(true){
            h = sc.nextInt();
            w = sc.nextInt();
            if(h==0 && w==0) break;

            donut = new char[h][w];
            for(int i=0; i<h; i++){
                String line = sc.next();
                donut[i] = line.toCharArray();
            }

            int max = 0;
            StringBuilder minString = new StringBuilder();

            for(int l=h*w; l>=2; l--){
                HashSet<String> set = new HashSet<String>();
                for(int i=0; i<h; i++){
                    for(int j=0; j<w; j++){
                        for(int k=0; k<8; k++){                        
                            StringBuilder s = new StringBuilder();
                            s.append(donut[i][j]);
                            int ny = (i + dy[k]) % h == -1 ? h-1 : (i + dy[k]) % h;
                            int nx = (j + dx[k]) % w == -1 ? w-1 : (j + dx[k]) % w;
                            while(!(ny==i && nx==j)){
                                if(s.length()==l) break;
                                s.append(donut[ny][nx]);
                                ny = (ny + dy[k]) % h == -1 ? h-1 : (ny + dy[k]) % h;
                                nx = (nx + dx[k]) % w == -1 ? w-1 : (nx + dx[k]) % w;
                            }
                            if(s.length()!=l) continue;

                            if(!set.add(s.toString())){
                                if(minString.length()==0) minString = s;
                                else minString = min(s, minString);
                                max = l;
                            }
                        }
                    }
                }
                if(max!=0) break;
            }

            if(minString.length()==0) System.out.println(0);
            else System.out.println(minString.toString());
        }
    }

    StringBuilder min(StringBuilder s1, StringBuilder s2){
        int l1 = s1.length(), l2 = s2.length();
        for(int i=0; i<Math.min(l1, l2); i++){
            if(s1.charAt(i)<s2.charAt(i)) return s1;
            else if(s1.charAt(i)>s2.charAt(i)) return s2;
        }
        if(l1<l2) return s1;
        return s2;
    }

    public static void main(String[] args){
        new Main().solve();
    }
}