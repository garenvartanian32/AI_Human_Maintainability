import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String password = scanner.next();
        char passwordChar1 = password.charAt(0);
        char passwordChar2 = password.charAt(1);

        int wordsNumber = scanner.nextInt();
        String[] words = new String[wordsNumber];

        for(int w = 0; w < wordsNumber; w++) {
            words[w] = scanner.next();
        }

        boolean canUnlock = false;

        // Is there an exact match?
        for(String word : words) {
            if (word.equals(password)) {
                canUnlock = true;
                break;
            }
        }

        if (!canUnlock) {
            boolean firstCharFound = false;

            for(String word : words) {
                if (word.charAt(1) == passwordChar1) {
                    firstCharFound = true;
                    break;
                }
            }

            if (firstCharFound) {
                for(String word : words) {
                    if (word.charAt(0) == passwordChar2) {
                        canUnlock = true;
                        break;
                    }
                }
            }

        }


        if(canUnlock) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

}
