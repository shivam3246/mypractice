import java.util.*;

public class ReorderDataInLogFiles {

    public static String[] reorderLogFiles(String[] logs) {
        int n = logs.length;
        String[] letterLogs = new String[n];
        String[] digitLogs = new String[n];
        int digitCount = 0;
        int letterCount = 0;

        for (String log : logs) {
            int idx = log.indexOf(' ');
            char firstChar = log.charAt(idx + 1);
            if (Character.isDigit(firstChar)) {
                digitLogs[digitCount++] = log;
            } else {
                letterLogs[letterCount++] = log;
            }
        }

        // Sort letter logs with custom comparator using selection sort
        for (int i = 0; i < letterCount - 1; i++) {
            for (int j = i + 1; j < letterCount; j++) {
                String[] strI = letterLogs[i].split(" ", 2);
                String[] strJ = letterLogs[j].split(" ", 2);
                int compare = strI[1].compareTo(strJ[1]);
                if (compare > 0 || (compare == 0 && strI[0].compareTo(strJ[0]) > 0)) {
                    String temp = letterLogs[i];
                    letterLogs[i] = letterLogs[j];
                    letterLogs[j] = temp;
                }
            }
        }

        String[] result = new String[n];
        int idx = 0;

        for (int i = 0; i < letterCount; i++) {
            result[idx++] = letterLogs[i];
        }
        for (int i = 0; i < digitCount; i++) {
            result[idx++] = digitLogs[i];
        }

        return result;
    }

    public static void main(String[] args) {
        String[] logs = {
            "dig1 8 1 5 1",
            "let1 art can",
            "dig2 3 6",
            "let2 own kit dig",
            "let3 art zero"
        };

        String[] result = reorderLogFiles(logs);
        System.out.println(Arrays.toString(result));
    }
}
