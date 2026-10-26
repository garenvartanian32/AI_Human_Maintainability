import java.util.*;

public class readingBooks {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int k = input.nextInt();

        ArrayList<Integer>[] arr = new ArrayList[4];
        for(int i = 0; i < 4; i++){
            arr[i] = new ArrayList<Integer>();
        }
        for(int i = 0; i < n; i++){
            int p = input.nextInt();
            int bin = input.nextInt() * 2 + input.nextInt();
            arr[bin].add(p);
        }
        if(arr[3].size() + Math.min(arr[1].size(), arr[2].size()) < k){
            System.out.println(-1);
            return;
        }
        for(int i = 0; i < 4; i++){
            Collections.sort(arr[i]);
        }
        int a, b, ans;
        a = b = ans = 0;
        for(int i = 0; i < k; i ++){
            boolean A = a < arr[3].size(), B = b >= arr[1].size() || b >= arr[2].size();
            if(B || (A && arr[3].get(a) < arr[1].get(b) + arr[2].get(b))){
                ans += arr[3].get(a);
                a ++;
            }else{
                ans += arr[1].get(b) + arr[2].get(b);
                b ++;
            }
        }
        System.out.println(ans);
    }
}