package AlejandroNumbers.Generator;

import AlejandroNumbers.AlejandroNumberContainer;

import java.math.BigInteger;
import java.util.List;

public interface OnAlejandroNumbersGeneratedListener {
    void generated(List<AlejandroNumberContainer> alejandroNums, BigInteger searchedSeed);
}
