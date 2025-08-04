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

    public static String[] anotherMethod(String[] logs) {
        Arrays.sort(logs, (log1, log2) -> {
            String[] split1 = log1.split(" ", 2);
            String[] split2 = log2.split(" ", 2);
            boolean isDigit1 = Character.isDigit(split1[1].charAt(0));
            boolean isDigit2 = Character.isDigit(split2[1].charAt(0));
            if (!isDigit1 && !isDigit2) { // both letter-logs
                int cmp = split1[1].compareTo(split2[1]);
                if (cmp != 0) return cmp; 
                return split1[0].compareTo(split2[0]);
            }
            if (!isDigit1 && isDigit2) return -1; // letter-log before digit-log
            if (isDigit1 && !isDigit2) return 1;  // digit-log after letter-log
            return 0; //both digits-letter logs are in order

        });
        return logs;
    }
    //Bucket + Merge Stable partition
        public static String[] iLikedThis(String[] logs) {
        List<String> letterLogs = new ArrayList<>();
        List<String> digitLogs = new ArrayList<>();
        for (String log : logs) {
            int idx = log.indexOf(' ');
            if (Character.isDigit(log.charAt(idx + 1))) {
                digitLogs.add(log);
            } else {
                letterLogs.add(log);
            }
        }
        letterLogs.sort((a, b) -> {
            String idA = a.substring(0, a.indexOf(' '));
            String logA = a.substring(a.indexOf(' ') + 1);
            String idB = b.substring(0, b.indexOf(' '));
            String logB = b.substring(b.indexOf(' ') + 1);
            int cmp = logA.compareTo(logB);
            if (cmp != 0) return cmp;
            return idA.compareTo(idB);
        });
        letterLogs.addAll(digitLogs);
        return letterLogs.toArray(new String[0]);
    }



    public static void main(String[] args) {
        String[] logs = {
            "dig1 8 1 5 1",
            "let1 art can",
            "dig2 3 6",
            "let2 own kit dig",
            "let3 art zero"
        };

        // String[] result = reorderLogFiles(logs);
        // System.out.println(Arrays.toString(result));
        // String []anwer = anotherMethod(logs);
        // System.out.println(anwer);
        String []answer2 = iLikedThis(logs);
        System.out.println(answer2);

        }
}
