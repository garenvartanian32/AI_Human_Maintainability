import java.util.HashSet;
import java.util.Scanner;
import java.util.stream.IntStream;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int h = sc.nextInt(), w = sc.nextInt();

        char[][] map = new char[h][w];
        for (int i = 0; i < h; i++) {
            map[i] = sc.next().toCharArray();
        }

        HashSet<Integer> yset = new HashSet<>(), xset = new HashSet<>();
        IntStream.range(0, h).forEach(yset::add);
        IntStream.range(0, w).forEach(xset::add);

        outer:
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++)
                if (map[y][x] != '.') continue outer;
            yset.remove(y);
        }
        outer:
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++)
                if (map[y][x] != '.') continue outer;
            xset.remove(x);
        }
        
        yset.forEach(y -> {
            xset.forEach(x -> System.out.print(map[y][x]));
            System.out.println();
        });
    }
}