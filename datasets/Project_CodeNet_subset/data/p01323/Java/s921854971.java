import java.util.*;

class Main{

    char[][] field;
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};
    int h = 12, w = 6;
    ArrayList<int[]> clearList;

    void solve(){
        Scanner sc = new Scanner(System.in);

        int dataset = sc.nextInt();
        while(dataset-->0){
            field = new char[h][w];
            for(int i=0; i<h; i++){
                field[i] = sc.next().toCharArray();
            }

            int cnt = 0;
            while(true){
                clearList = new ArrayList<int[]>();
                for(int i=0; i<h; i++){
                    for(int j=0; j<w; j++){
                        if(field[i][j]!='.' && field[i][j]!='O') find(i, j);
                    }
                }
                if(clearList.size()==0) break;
                clear(clearList);
                down();
                cnt++;
            }
            System.out.println(cnt);
        }
    }

    boolean find(int y, int x){
        char clear = field[y][x];
        ArrayList<int[]> clearList2 = new ArrayList<int[]>();
        LinkedList<int[]> list = new LinkedList<int[]>();
        boolean[][] v = new boolean[h][w];
        list.add(new int[]{x, y});

        while(list.size()>0){
            int[] qq = list.poll();
            int xx = qq[0], yy = qq[1];

            if(v[yy][xx]) continue;
            v[yy][xx] = true;
            clearList2.add(new int[]{xx, yy});

            for(int i=0; i<4; i++){
                int nx = xx + dx[i], ny = yy + dy[i];
                if(nx<0 || nx>=w || ny<0 || ny>=h || field[ny][nx]!=clear) continue;
                list.add(new int[]{nx, ny});
            }
        }

        if(clearList2.size()>=4){
            for(int[] xy : clearList2) clearList.add(xy);
        }
        return false;
    }

    void print(){
        for(int i=0; i<h; i++)
            System.out.println(Arrays.toString(field[i]));
        System.out.println("------");
    }

    void clear(ArrayList<int[]> clearList1){
        for(int[] xy : clearList1){
            int x = xy[0], y = xy[1];
            field[y][x] = '.';
            for(int i=0; i<4; i++){
                int nx = x + dx[i], ny = y + dy[i];
                if(nx<0 || nx>=w || ny<0 || ny>=h || field[ny][nx]!='O') continue;
                field[ny][nx] = '.';
            }
        }
    }

    void down(){
        for(int k=0; k<w; k++){
            for(int i=h-1; i>=0; i--){
                if(field[i][k]!='.') continue;
                int idx = -1;
                for(int j=i; j>=0; j--){
                    if(field[j][k]!='.'){
                        idx = j;
                        break;
                    }
                }
                if(idx==-1) break;
                else{
                    char tmp = field[i][k];
                    field[i][k] = field[idx][k];
                    field[idx][k] = tmp;
                }
            }
        }
    }

    public static void main(String[] args){
        new Main().solve();
    }
}