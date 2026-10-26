import java.util.*;
import java.lang.reflect.*;
import org.javatuples.*;
import java.security.*;
import java.math.*;
import java.io.*;
import java.util.stream.*;

class Problem {
    public static long isBored(String S) {
        long boredomCount = 0;
        String[] sentences = S.split("[.?!]");

        for (String sentence : sentences) {
            String cleanedSentence = sentence.trim();

            if (startsWithWordI(cleanedSentence)) {
                boredomCount++;
            }
        }

        return boredomCount;
    }

    private static boolean startsWithWordI(String sentence) {
        return sentence.equals("I") || sentence.startsWith("I ");
    }
}