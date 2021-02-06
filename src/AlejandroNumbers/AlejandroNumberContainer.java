package AlejandroNumbers;

import AlejandroNumbers.Utils.MathHelper;

import java.math.BigInteger;

public class AlejandroNumberContainer {
    public static final BigInteger DEFAULT_SEED = BigInteger.ONE;
    public static final int DEFAULT_ITERATIONS = 10;
    public static final int DEFAULT_ABNORMALITY_THRESHOLD = 1; //Most occuring Int for most seeds;
    private final BigInteger alejandroNumberValue;
    private BigInteger fromSeed;
    private int withIterations;

    public AlejandroNumberContainer(BigInteger number) {
        this.alejandroNumberValue = number;
    }

    public BigInteger getAlejandroNumberValue() {
        return alejandroNumberValue;
    }

    public int getDominantNumber(){
        return Character.getNumericValue(MathHelper.getMaxOccuringChar(alejandroNumberValue.toString()));
    }
}
