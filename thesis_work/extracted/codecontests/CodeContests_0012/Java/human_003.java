import java.util.Scanner;

public class HelpVasilisaTheWise {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int rowOne = scanner.nextInt();
        int rowTwo = scanner.nextInt();
        int columnOne = scanner.nextInt();
        int columnTwo = scanner.nextInt();
        int diagonalOne = scanner.nextInt();
        int diagonalTwo = scanner.nextInt();
        boolean isFound = false;

        int[][] gems = new int[2][2];


        if ((rowOne == columnOne || rowTwo == columnTwo) ||
                (rowOne < 3 || rowTwo < 3 || columnOne < 3 || columnTwo < 3 || diagonalOne < 3 || diagonalTwo < 3)) {

            System.out.println(-1);

        } else {
            int min = Math.min(Math.min(rowOne, columnOne), diagonalOne);
            int gem;

            for (int k = 1, j = 0; k < 10; k++, j++) {
                if (min > 9) {
                    gem = (min % 9) + j;
                } else {
                    gem = min - k;
                }

                if ((rowOne - gem) == gem || (columnOne - gem) == gem || (diagonalOne - gem) == gem) {
                    gems[0][0] = min - gem;
                } else {
                    gems[0][0] = gem;
                }
                gem = gems[0][0];
                gems[0][1] = rowOne - gem;
                gems[1][0] = columnOne - gem;
                gems[1][1] = diagonalOne - gem;


                if (gem < 10 && gem > 0 &&
                        gems[0][1] < 10 && gems[0][1] > 0 &&
                        gems[1][0] < 10 && gems[1][0] > 0 &&
                        gems[1][1] < 10 && gems[1][1] > 0 &&
                        (gems[0][0] + gems[0][1]) == rowOne && (gems[1][0] + gems[1][1]) == rowTwo &&
                        (gems[0][0] + gems[1][0]) == columnOne && (gems[0][1] + gems[1][1]) == columnTwo &&
                        (gems[0][0] + gems[1][1]) == diagonalOne && (gems[0][1] + gems[1][0]) == diagonalTwo &&
                        (gems[0][0] != gems[0][1] && gems[0][0] != gems[1][0] && gems[0][0] != gems[1][1]) &&
                        (gems[0][1] != gems[1][0] && gems[0][1] != gems[1][1]) &&
                        (gems[1][0] != gems[1][1])) {

                    isFound = true;
                    break;

                }

            }
            if (isFound) {
                System.out.println(gems[0][0] + " " + gems[0][1]);
                System.out.println(gems[1][0] + " " + gems[1][1]);
            } else {
                System.out.println(-1);
            }
        }
    }
}
