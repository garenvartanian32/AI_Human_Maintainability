

import javax.net.ssl.SNIHostName;
import javax.swing.plaf.synth.SynthTextAreaUI;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static String mainFunction() {
        Scanner inputReceiver = new Scanner(System.in);
        String password = inputReceiver.nextLine();
        int numberOfBarks = Integer.parseInt(String.valueOf(inputReceiver.nextLine()));
        String[] barks = new String[numberOfBarks];
        for (int i = 0; i < numberOfBarks; i++) {
            barks[i] = inputReceiver.nextLine();
        }
        for (int i = 0; i < numberOfBarks; i ++) {
            String currentBark = barks[i];
            for (int j = i; j < numberOfBarks; j ++) {
                //System.out.println(currentBark.charAt(1) + barks[j].charAt(0));
                //System.out.println(currentBark.charAt(0) + barks[j].charAt(1));
                if (password.equals(barks[i]) || password.equals(String.valueOf(currentBark.charAt(1)) + String.valueOf(barks[j].charAt(0))) || password.equals(String.valueOf(barks[j].charAt(1)) + String.valueOf(currentBark.charAt(0)))) {
                    return "YES";
                    }
                }
            }
        return "NO";
    }

    public static void main(String[] args) {
        System.out.println(mainFunction());
    }
}


