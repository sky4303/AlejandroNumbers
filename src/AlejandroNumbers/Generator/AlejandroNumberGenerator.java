package AlejandroNumbers.Generator;

import AlejandroNumbers.AlejandroNumberContainer;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class AlejandroNumberGenerator {

    private final OnAlejandroNumbersGeneratedListener onAlejandroNumbersGeneratedListener;
    List<AlejandroNumberContainer> alejandroNumberArray = new ArrayList<>();

    public AlejandroNumberGenerator(OnAlejandroNumbersGeneratedListener onAlejandroNumbersGeneratedListener) {
        this.onAlejandroNumbersGeneratedListener = onAlejandroNumbersGeneratedListener;
    }

    public static String convertIntToAlejandroNumber(BigInteger numberBigInt) {
        String num = numberBigInt.toString();
        StringBuilder result = new StringBuilder();
        char repeat = num.charAt(0);
        num = num.substring(1) + " ";
        int times = 1;

        for (char actual : num.toCharArray()) {
            if (actual != repeat) {
                result.append(times).append(repeat);
                times = 1;
                repeat = actual;
            } else {
                times += 1;
            }
        }
        return result.toString();
    }

    public void startAlejandroNumberGeneration(int maxIterations, BigInteger alejandroNumber) {
        alejandroNumberArray.clear();
        for (int i = 1; i <= maxIterations; i++) {
            alejandroNumber = new BigInteger(convertIntToAlejandroNumber(alejandroNumber));
            alejandroNumberArray.add(new AlejandroNumberContainer(alejandroNumber));
        }
        onAlejandroNumbersGeneratedListener.generated(alejandroNumberArray, alejandroNumber);
    }
}
