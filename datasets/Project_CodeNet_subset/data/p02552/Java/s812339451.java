import java.util.*;


class Main{
    public static int biFind(int[] target , String con , int cri){
        //return the first index if found, -1 if not found, -2 if error
        int k = target.length -1;
        int nope = 0;
        int mid = k + (k-nope)/2+1;
        if(con.equals("<")){
            if(target[k]>=cri){
                return -1;
            }else if(target[0]<cri){
                return 0;
            }
            while(nope<k-1){
                mid = nope + (k-nope)/2;
                if(target[mid]<cri){
                    k = mid;
                }else{
                    nope = mid;
                }
            }
            return k;
        }else if(con.equals("<=")){
            if(target[k]>cri){
                return -1;
            }else if(target[0]<=cri){
                return 0;
            }
            while(nope>k){
                mid = k + (k-nope)/2+1;
                if(target[mid]<=cri){
                    k = mid;
                }else{
                    nope = mid;
                }
            }
            return k;
        }else{
            return -2;
        }
    }


    //ans
    public static void main(String[] args){
	Scanner sc = new Scanner(System.in);
      int i = sc.nextInt();
      System.out.println(1-i);
    }
}