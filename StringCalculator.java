class StringCalculator {

    public static int add(final String text) {

        String[] stringArray = text.split(",");

        if (stringArray.length > 2) {

            throw new RuntimeException("Upto 2 numbers separated by , are allowed");
        } else if (stringArray.length == 0) {
            return 0;
        } else {
            int sum = 0;
            for (int i = 0; i < stringArray.length; i++) {
                sum += Integer.parseInt(stringArray[i]);
            }
            return sum;
        }
    }
}