import java.util.ArrayList;
import java.util.List;

class StringCalculator {

    public static int add(final String text) {

        int sum = 0;
        List<Integer> negativeNumbers = new ArrayList();
        String delimiter = ",|n";
        String noWithoutDelimiter = text;
        String[] stringArray = text.split(",|n");

        if (stringArray.length == 0) {
            return sum;
        }



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

        return sum;

    }
    private static int add(final String text, final String delimiter) {
       
        int sum = 0;
        String[] stringArray = text.split(delimiter);
        for (int i = 0; i < stringArray.length; i++) {
            if (!stringArray[i].isEmpty()) {
                sum += Integer.parseInt(stringArray[i].trim());
            }
        }
        return sum;
    }

    public int GetCalledCount() {
        int count = 0;

        return 0;
    }
}