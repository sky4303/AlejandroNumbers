package AlejandroNumbers.Queries;

import AlejandroNumbers.Filters.ComparisonStrategyFilters.ComparisonStrategy;
import AlejandroNumbers.Filters.ExtraFilters.BigIntFilter;
import AlejandroNumbers.Visualizers.AlejandroNumberGrapher;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class AbnormalDominantAlejandroNumberFinder {
    public static final int DEFAULT_ABNORMALITY_THRESHOLD = 1;
    private final ComparisonStrategy comparisonStrategy;
    private final int maxresults;
    private final boolean indefinite;
    private final BigInteger startedWithSeed;
    private final BigIntFilter filter;
    private final BigInteger filterParam;
    private int foundResults = 0;
    private int abnormalityIndicator;
    private BigInteger seed;
    private int maxIterations;
    private List<Integer> associatedAbnormalities = new ArrayList<>();
    private List<BigInteger> alejandroNumberSeeds = new ArrayList<>();
    private final boolean allowTimeJumps;

    public AbnormalDominantAlejandroNumberFinder(int abnormalityIndicator, BigInteger startseed, int maxIterations, ComparisonStrategy comparisonStrategy, int maxResults, boolean indefinite, BigIntFilter filter, BigInteger filterParam, boolean allowTimeJumps) {
        this.abnormalityIndicator = abnormalityIndicator;
        this.seed = startseed;
        this.startedWithSeed = startseed;
        this.maxIterations = maxIterations;
        this.comparisonStrategy = comparisonStrategy;
        this.maxresults = maxResults;
        this.indefinite = indefinite;
        this.filter = filter;
        this.filterParam = filterParam;
        this.allowTimeJumps = allowTimeJumps;
    }

    boolean continueSearching = true;

    public void startAbnormalSearch() {
        while (continueSearching || indefinite) {
            DominantAlejandroNumberFinder.getDominantFromSeed(seed, maxIterations, (dominantIntInArray, atSeed) -> {
                if (comparisonStrategy.compare(dominantIntInArray, abnormalityIndicator)){
                    startTooLongTimer();
                    if (filter.shouldAllowThroughFilter(atSeed, filterParam)) {

                        System.out.println(foundResults + ": " + "Dominance of " + dominantIntInArray + " found at seed "
                                + atSeed + " with " + maxIterations + " iterations");
                        associatedAbnormalities.add(dominantIntInArray);
                        alejandroNumberSeeds.add(atSeed);
                        foundResults++;
                        if (foundResults >= maxresults) {
                            BigInteger[] alejandroNumberSeedsArray = new BigInteger[alejandroNumberSeeds.size()];
                            Integer[] associatedAbnormalitiesArray = new Integer[associatedAbnormalities.size()];
                            alejandroNumberSeeds.toArray(alejandroNumberSeedsArray);
                            associatedAbnormalities.toArray(associatedAbnormalitiesArray);
                            AlejandroNumberGrapher.plot(alejandroNumberSeedsArray, associatedAbnormalitiesArray, maxIterations, comparisonStrategy, abnormalityIndicator, startedWithSeed);
                            continueSearching = false;
                        }
                    }
                }
            });
            seed = seed.add(new BigInteger("1"));
        }
    }

    private void startTooLongTimer() {
        //If it takes too long, we will artificially add to the seed and start from there, the steps will always get bigger:

    }
}
