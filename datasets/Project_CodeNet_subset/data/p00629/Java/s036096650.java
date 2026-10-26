import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {
  public static void main(String[] args){
    new Main();
  }
  public Main(){
    Scanner sc = new Scanner(System.in);
    while(true){
      int n = sc.nextInt();
      if(n==0)break;
      ArrayList<team> tms = new ArrayList<>();
      for(int i=0;i<n;i++){
        tms.add(new team(sc.nextInt(),sc.nextInt(),sc.nextInt(),sc.nextInt()));
      }
      Collections.sort(tms, new teamComp());
      int[] count = new int[1001];
      int saiyo = 0;
      for(int i=0;i<n;i++){
        int lim;
        if(saiyo<10)lim = 3;
        else if(saiyo<20)lim = 2;
        else if(saiyo<26)lim = 1;
        else break;
        team tm = tms.get(i);
        if(count[tm.school]>=lim)continue;
        count[tm.school]++;
        saiyo++;
        System.out.println(tm.id);
      }
    }
  }
  private class team{
    public int id, school, ac, penalty;
    public team(int id,int school,int ac,int penalty){
      this.id = id;
      this.school = school;
      this.ac = ac;
      this.penalty = penalty;
    }
  }
  private class teamComp implements Comparator<team>{
    public int compare(team a, team b){
      if(a.ac > b.ac) return -1;
      else if(a.ac < b.ac) return 1;
      else{
        if(a.penalty > b.penalty) return 1;
        else if(a.penalty < b.penalty) return -1;
        else{
          if(a.id > b.id) return 1;
          else return -1;
        }
      }
    }
  }
}