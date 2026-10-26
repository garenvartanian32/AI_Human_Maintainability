import java.io.*;
import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int c1x = sc.nextInt();
    int c1y = sc.nextInt();
    int c1r = sc.nextInt();
    int c2x = sc.nextInt();
    int c2y = sc.nextInt();
    int c2r = sc.nextInt();

    int diffX = Math.abs(c1x - c2x);
    int diffY = Math.abs(c1y - c2y);
    int xyR = c1r + c2r;
    int right1X = c1x + c1r;
    int left1X = c1x - c1r;
    int right2X = c2x + c2r;
    int left2X = c2x - c2r;
    int top1Y = c1y + c1r;
    int bottom1Y = c1y - c1r;
    int top2Y = c2y + c2r;
    int bottom2Y = c2y - c2r;

    if(c1x == c2x) {
      if(diffY == xyR) { //外接
        System.out.println(3);
      } else if(diffY > xyR) { // 離れ
        System.out.println(4);
      } else {
        if(((top1Y < top2Y) && (bottom1Y > bottom2Y)) ||
        ((top1Y > top2Y) && (bottom1Y < bottom2Y))) { // 内包
          System.out.println(0);
        } else if(((top1Y == top2Y) || (bottom1Y == bottom2Y)) || (top1Y == top2Y) || (bottom1Y == bottom2Y)) { // 内接
          System.out.println(1);
        } else System.out.println(2); // 交わる
      }
    } else if(c1y == c2y) {
      if(diffX == xyR) { //外接
        System.out.println(3);
      } else if(diffX > xyR) { // 離れ
        System.out.println(4);
      } else {
        if(((right1X < right2X) && (left1X > left2X)) ||
        ((right1X > right2X) && (left1X < left2X))) { // 内包
          System.out.println(0);
        } else if(((right1X <= right2X) || (left1X <= left2X)) &&
        ((right1X >= right2X) || (left1X >= left2X))) { // 内接
          System.out.println(1);
        } else System.out.println(2); // 交わる
      }
    } else {
      double d = Math.sqrt(diffX*diffX + diffY*diffY);
      if(d > xyR) { //離れ
        System.out.println(4);
      } else if(d == xyR) {
        System.out.println(3);
      } else {
        if((((right1X < right2X) && (left1X > left2X) && (!(d+c1r > c2r))) && ((top1Y < top2Y) && (bottom1Y > bottom2Y) && (!(d+c1r > c2r)))) ||
        (((right1X > right2X) && (left1X < left2X) && (!(d+c2r > c1r))) && ((top1Y > top2Y) && (bottom1Y < bottom2Y)) && (!(d+c2r > c1r)))) { // 内包
          System.out.println(0);
        } else if(((right1X == right2X) || (left1X == left2X) || (top1Y == top2Y) || (bottom1Y == bottom2Y)) && (!(d+c1r > c2r))) { // 内接
          System.out.println(1);
        } else System.out.println(2); // 交わる
      }
    }
  }
}
