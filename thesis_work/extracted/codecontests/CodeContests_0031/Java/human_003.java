import java.io.IOException;
import java.util.*;

public class Solution {

    public static void main(String args[]) throws IOException {
        new Solution().run();
    }

    private void run() throws IOException {

        Scanner scanner = new Scanner(System.in);

        int t = scanner.nextInt();
        int n = scanner.nextInt();

        int[] a = new int[n];

        scanner.nextLine();

        int blockNumber = 1;
        for (int i = 0; i < t; i++) {
            String[] line = scanner.nextLine().split(" ");
            if (line[0].compareTo("alloc") == 0) {

                int size = Integer.parseInt(line[1]);
                Boolean isOk = false;
                for (int j = 0; j < n; j++) {
                    if (a[j] != 0) {
                        isOk = false;
                    } else {

                        isOk = true;
                        int pos = j;

                        if (pos + size > a.length) {
                            isOk = false;
                            break;
                        }

                        for (int k = pos + 1; k < pos + size; k++) {
                            if (a[k] != 0) {
                                isOk = false;
                                break;
                            }
                        }

                        if (isOk) {
                            for (int l = pos; l < pos + size; l++) {
                                a[l] = blockNumber;
                            }
                            blockNumber++;
                            break;
                        }
                    }

                }
                if (isOk)
                    System.out.println(blockNumber - 1);
                else
                    System.out.println("NULL");

            } else if (line[0].compareTo("erase") == 0) {
                Boolean isOk = false;
                long blockForErasing = Long.parseLong(line[1]);
                if (blockForErasing != 0) {
                    for (int j = 0; j < n; j++) {
                        if ((long) a[j] == blockForErasing) {
                            isOk = true;
                            break;
                        }
                    }

                    if (isOk) {
                        for (int j = 0; j < n; j++) {
                            if ((long) a[j] == blockForErasing) {
                                a[j] = 0;
                            }
                        }
                    } else {
                        System.out.println("ILLEGAL_ERASE_ARGUMENT");
                    }
                } else {
                    System.out.println("ILLEGAL_ERASE_ARGUMENT");
                }
            } else {
                int[] b = new int[n];
                int l = 0;
                for (int j = 0; j < n; j++) {
                    if (a[j] == 0)
                        continue;
                    b[l] = a[j];
                    l++;
                }
                a = b;
            }

        }
    }

}
