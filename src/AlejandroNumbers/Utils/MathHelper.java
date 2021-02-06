package AlejandroNumbers.Utils;

import java.math.BigInteger;

public class MathHelper {
    static int countOccurrences(BigInteger bigInteger,
                                BigInteger searchedBigInt) {
        int count = 0;
        while (bigInteger.compareTo(BigInteger.ZERO) > 0) {
            if (bigInteger.mod(new BigInteger("10")).equals(searchedBigInt))
                count++;
            bigInteger = bigInteger.divide(new BigInteger("10"));
        }
        return count;
    }

    static int maxOccurring(BigInteger searchedBigInt) {
        if (searchedBigInt.compareTo(BigInteger.ZERO) < 0)
            searchedBigInt = searchedBigInt.multiply(new BigInteger("-1"));
        int result = 0;
        int max_count = 1;
        for (int d = 0; d <= 9; d++) {
            int count = countOccurrences(searchedBigInt, new BigInteger(String.valueOf(d)));
            if (count >= max_count) {
                max_count = count;
                result = d;
            }
        }
        return result;
    }

    static final int ASCII_SIZE = 256;
    public static char getMaxOccuringChar(String str)
    {
        int count[] = new int[ASCII_SIZE];

        int len = str.length();
        for (int i=0; i<len; i++)
            count[str.charAt(i)]++;
        int max = -1;
        char result = ' ';

        for (int i = 0; i < len; i++) {
            if (max < count[str.charAt(i)]) {
                max = count[str.charAt(i)];
                result = str.charAt(i);
            }
        }
        return result;
    }
}
