import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int s = sc.nextInt();
        List<Integer> list = new ArrayList<>();
        int i = 1;
        list.add(s);
        while(true){
            i ++;
            if(s % 2 == 0){
                s /= 2;
            }else {
                s = s * 3 + 1;
            }
            if(list.contains(s)){
                System.out.println(i);
                break;
            }
            list.add(s);
        }

    }
}