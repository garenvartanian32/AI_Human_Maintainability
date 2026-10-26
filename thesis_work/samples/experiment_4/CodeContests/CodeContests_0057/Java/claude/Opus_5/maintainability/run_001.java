import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Largest x for which a binary matrix survives x-compression.
 *
 * <p>Larger x always implies every smaller divisor works, so we scan divisors
 * downwards and stop at the first whose blocks are all uniform.
 */
public class Sol {

    private static final int HEX_BITS = 4;

    private static int size;
    private static int[][] prefix;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        size = Integer.parseInt(reader.readLine().trim());

        byte[][] matrix = new byte[size][size];
        for (int row = 0; row < size; row++) {
            String hexRow = reader.readLine().trim();
            int column = 0;
            for (int i = 0; i < hexRow.length(); i++) {
                int value = Character.digit(hexRow.charAt(i), 16);
                for (int shift = HEX_BITS - 1; shift >= 0; shift--) {
                    matrix[row][column++] = (byte) ((value >> shift) & 1);
                }
            }
        }

        prefix = buildPrefixSums(matrix);

        for (int side = size; side >= 1; side--) {
            if (size % side == 0 && everyBlockIsUniform(side)) {
                System.out.println(side);
                return;
            }
        }
    }

    /** Standard 2-D prefix sums, padded with a zero row and column. */
    private static int[][] buildPrefixSums(byte[][] matrix) {
        int[][] sums = new int[size + 1][size + 1];
        for (int row = 1; row <= size; row++) {
            int running = 0;
            for (int column = 1; column <= size; column++) {
                running += matrix[row - 1][column - 1];
                sums[row][column] = sums[row - 1][column] + running;
            }
        }
        return sums;
    }

    private static boolean everyBlockIsUniform(int side) {
        for (int top = 0; top < size; top += side) {
            for (int left = 0; left < size; left += side) {
                if (!blockIsUniform(top, left, side)) {
                    return false;
                }
            }
        }
        return true;
    }

    /** True when the side x side block is entirely zeros or entirely ones. */
    private static boolean blockIsUniform(int top, int left, int side) {
        int bottom = top + side;
        int right = left + side;
        int ones = prefix[bottom][right] - prefix[top][right]
                 - prefix[bottom][left] + prefix[top][left];
        return ones == 0 || ones == side * side;
    }
}