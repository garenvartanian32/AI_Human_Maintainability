import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 * Total happiness once each gag is discounted by its position in the show.
 *
 * <p>The nth gag performed loses n happiness, so ordering never matters: the
 * penalties are always 1, 2, ..., gagCount in some assignment.
 */
public class Sol {

    public static void main(String[] args) throws IOException {
        StreamTokenizer input = new StreamTokenizer(
                new BufferedReader(new InputStreamReader(System.in)));

        input.nextToken();
        long gagCount = (long) input.nval;

        long totalValue = 0;
        for (long gag = 0; gag < gagCount; gag++) {
            input.nextToken();
            totalValue += (long) input.nval;
        }

        long totalPenalty = gagCount * (gagCount + 1) / 2;
        System.out.println(totalValue - totalPenalty);
    }
}