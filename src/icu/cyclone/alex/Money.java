package icu.cyclone.alex;

public class Money {
    private static String[] unit = new String[]{"", "one", "two", "three", "four", "five", "six", "seven", "eight",
            "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen",
            "nineteen"};
    private static String[] decade = new String[]{"", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy",
            "eighty", "ninety"};
    private static String[] groups = new String[]{"", " thousand ", " million ", " billion "};

    public static String toString(double currency) {
        check(currency);

        int dollarsCount = (int) (currency);
        int centsCount = (int) ((currency * 100) % 100);

        String dollars = currencyToString(dollarsCount, "dollar");
        String cents = currencyToString(centsCount, "cent");

        String separator = (dollarsCount > 0) && (centsCount > 0) ? " " : "";

        return dollars + separator + cents;
    }

    private static void check(double currency) {
        if (currency < 0) {
            throw new IllegalArgumentException("Value must be positive");
        }
        if (currency > Integer.MAX_VALUE) {
            throw new IllegalArgumentException("Max value can be converted is " + Integer.MAX_VALUE);
        }
    }

    private static String currencyToString(int count, String currency) {
        if (count == 0) {
            return "";
        }
        currency = count == 1 ? currency : currency + "s";
        return numberToString(count) + " " + currency;
    }

    private static String numberToString(int number) {
        if (number == 0) {
            return "";
        }

        String strNumber = "";

        for (int i = 0; i < groups.length; i += 1) {
            if (number % 1000 > 0) {
                strNumber = hundreds(number) + groups[i] + strNumber;
            }

            number /= 1000;

            if (number == 0) {
                break;
            }
        }

        return strNumber.trim();
    }

    private static String hundreds(int number) {
        number %= 1000;

        if (number == 0) {
            return "";
        }
        if (number / 100 == 0) {
            return units(number % 100);
        }
        if (number % 100 == 0) {
            return units(number / 100) + " hundred";
        }
        return units(number / 100) + " hundred " + units(number % 100);
    }

    private static String units(int number) {
        number %= 100;

        if (number < 20) {
            return unit[number];
        }
        if ((number % 10) == 0) {
            return decade[(number / 10)];
        }
        return decade[(number / 10)] + "-" + units(number % 10);
    }
}
