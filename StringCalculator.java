import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.joining;

class StringCalculator {

    public static int add(final String text) {

        int sum = 0;

        List<String> numbersList = getNumbers(text);
        List<Integer> negativeNumbers = new ArrayList();
        String delimiter = ",|n";
        String noWithoutDelimiter = text;
        String[] stringArray = text.split(",|n");
        if (stringArray.length == 0) {
            return sum;
        }
        /*
         * if (stringArray.length > 2) { throw new RuntimeException(
         * "Upto 2 numbers separated by , are allowed"); }
         */
        if (text.startsWith("//")) {
            int delimiterIndex = text.indexOf("//") + 2;
            delimiter = text.substring(delimiterIndex, delimiterIndex + 1);
            noWithoutDelimiter = text.substring(text.indexOf("n") + 1);
            return add(noWithoutDelimiter, delimiter);
        }
        if (stringArray.length > 0) {
            for (int i = 0; i < stringArray.length; i++) {
                if (!stringArray[i].isEmpty()) {
                    int numberInt = Integer.parseInt(stringArray[i]);
                    if (numberInt < 0) {
                        negativeNumbers.add(Integer.parseInt(stringArray[i]));
                    }else if(numberInt <= 1000){
                        sum += numberInt;
                    }
                }
            }
            if (negativeNumbers.size() > 0) {
                throw new RuntimeException("Negatives not allowed: " + negativeNumbers.toString());
            }
            return sum;
        }

        return sumArray(numbersList);
        /*
         * } else { return 0; }
         */
    }
    private static int add(final String text, final String delimiter) {
        // TODO Auto-generated method stub
        int sum = 0;
        String[] stringArray = text.split(delimiter);
        for (int i = 0; i < stringArray.length; i++) {
            if (!stringArray[i].isEmpty()) {
                sum += Integer.parseInt(stringArray[i].trim());
            }
        }
        return sum;
    }

    private static final String PIPE_DELIMITER = "|";
    private static final String PIPE_DELIMITER_ESCAPED = "\\|";
    private static final String ESCAPED_RANGE = "\\[";
    private static final String EMPTY_STRING = "";
    public static final int START_INDEX_OFFSET = 3;
    private static Pattern customDelimitersValidator = Pattern.compile("//(\\[(\\D+)])+\n.*");
    private static Matcher customDelimitersMatcher;
    private static int startIndex;

    public static List<String> getNumbers(String string) {
        String delimiters = getDelimiters(string);
        return separateNumbers(string, delimiters);
    }

    private static List<String> separateNumbers(String string, String delimiters) {
        if (areCustomDelimitersValid(string)) {
            string = string.substring(startIndex);
        }
        return asList(string.split(delimiters));
    }

    private static String getDelimiters(String string) {
        String delimiters = ",|\n";
        if (areCustomDelimitersValid(string)) {
            delimiters += PIPE_DELIMITER + getEscapedChars();
        }
        return delimiters;
    }

    private static String getEscapedChars() {
        String bracketsRemoved = removeBrackets(getCustomDelimiters());
        return Pattern.compile(PIPE_DELIMITER_ESCAPED).splitAsStream(bracketsRemoved).map(Pattern::quote).collect(joining(PIPE_DELIMITER));
    }

    private static String getCustomDelimiters() {
        String customDelimiters = customDelimitersMatcher.group(1);
        setStartIndex(customDelimiters);
        return customDelimiters;
    }

    private static String removeBrackets(String customDelimiters) {
        return customDelimiters.replaceFirst(ESCAPED_RANGE, EMPTY_STRING)
                .replaceAll(ESCAPED_RANGE, PIPE_DELIMITER)
                .replaceAll("]", EMPTY_STRING);
    }

    private static void setStartIndex(String customDelimiters) {
        startIndex = customDelimiters.length() + START_INDEX_OFFSET;
    }

    private static boolean areCustomDelimitersValid(String string) {
        customDelimitersMatcher = customDelimitersValidator.matcher(string);
        return customDelimitersMatcher.matches();
    }

    private static int sumArray(List<String> numbersList) {
        return numbersList.stream()
                .filter(s -> Integer.parseInt(s) <= 1000)
                .mapToInt(Integer::parseInt)
                .sum();
    }
}