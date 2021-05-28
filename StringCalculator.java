
class StringCalculator {

    public static int add(final String text) {

        String[] stringArray = text.split(",|n");


        int sum = 0;
        for (int i = 0; i < stringArray.length; i++) {
            if (stringArray[i] != "") {
                sum += Integer.parseInt(stringArray[i]);
            }
        }
        return sum;

    }
}