import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Minutes left for study between waking and bedtime.
 */
public class Sol {

    private static final int MINUTES_PER_HOUR = 60;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer line = new StringTokenizer(reader.readLine());

        int wakeHour = Integer.parseInt(line.nextToken());
        int wakeMinute = Integer.parseInt(line.nextToken());
        int sleepHour = Integer.parseInt(line.nextToken());
        int sleepMinute = Integer.parseInt(line.nextToken());
        int studyLength = Integer.parseInt(line.nextToken());

        int awakeMinutes = toMinutes(sleepHour, sleepMinute) - toMinutes(wakeHour, wakeMinute);
        System.out.println(Math.max(0, awakeMinutes - studyLength));
    }

    private static int toMinutes(int hour, int minute) {
        return hour * MINUTES_PER_HOUR + minute;
    }
}