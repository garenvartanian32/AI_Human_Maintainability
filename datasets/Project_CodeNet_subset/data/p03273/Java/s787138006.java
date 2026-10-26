import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner std = new Scanner(System.in);
        int h = std.nextInt();
        int w = std.nextInt();
        
        List<List<String>> tables = new ArrayList<>();
        for (int i = 0; i < h; i++) {
            tables.add(new ArrayList<>());
        }
        
        for (int i = 0; i < h; i++) {
            String line = std.next();
            String[] split = line.split("");
            for (int j = 0; j < split.length; j++) {
                tables.get(i).add(split[j]);
            }
        }
        
        for (int i = h - 1; i >= 0; i--) {
            List<String> lines = tables.get(i);
            if (!lines.contains("#")) {
                tables.remove(i);
            }
        }

        for (int i = w - 1; i >= 0; i--) {
            boolean none = true;
            for (List<String> line : tables) {
                if (line.get(i).equals("#")) {
                    none = false;
                    break;
                }
            }

            if (none) {
                for (List<String> line : tables) {
                    line.remove(i);
                }
            }
        }

        tables.forEach(it -> {
            System.out.println(String.join("", it));
        });
    }
}
