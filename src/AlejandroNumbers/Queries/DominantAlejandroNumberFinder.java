package AlejandroNumbers.Queries;

import AlejandroNumbers.AlejandroNumberContainer;
import AlejandroNumbers.Generator.AlejandroNumberGenerator;
import AlejandroNumbers.Utils.MathHelper;

import java.math.BigInteger;
import java.util.List;

public class DominantAlejandroNumberFinder {
    private final List<AlejandroNumberContainer> alejandroNumbers;
    private final OnAlejandroNumberDominantInArrayListener onAlejandroNumberDominantInArrayListener;

    public DominantAlejandroNumberFinder(List<AlejandroNumberContainer> alejandroNumbers, OnAlejandroNumberDominantInArrayListener onAlejandroNumberDominantInArrayListener) {
        this.alejandroNumbers = alejandroNumbers;
        this.onAlejandroNumberDominantInArrayListener = onAlejandroNumberDominantInArrayListener;
    }

    public void startDominantIntSearch(BigInteger atSeed) {
        StringBuilder stringBuilder = new StringBuilder();
        for (AlejandroNumberContainer alejandroNumber : alejandroNumbers) {
            int currentDominant = alejandroNumber.getDominantNumber();
            stringBuilder.append(currentDominant);
        }
        onAlejandroNumberDominantInArrayListener.dominant(Character.getNumericValue(MathHelper.getMaxOccuringChar(stringBuilder.toString())), atSeed);
    }

    public static void getDominantFromSeed(BigInteger alejandroNumberSeed, int maxIterations, OnAlejandroNumberDominantInArrayListener onAlejandroNumberDominantInArrayListener) {
        AlejandroNumberGenerator alejandroNumberGenerator = new AlejandroNumberGenerator((alejandroNums, searchedSeed) -> {
            DominantAlejandroNumberFinder alejandroNumberDominantFinder = new DominantAlejandroNumberFinder(alejandroNums,
                    onAlejandroNumberDominantInArrayListener);
            alejandroNumberDominantFinder.startDominantIntSearch(alejandroNumberSeed);
        });
        alejandroNumberGenerator.startAlejandroNumberGeneration(maxIterations, alejandroNumberSeed);
    }

}
