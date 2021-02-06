package AlejandroNumbers.Queries;

import AlejandroNumbers.Filters.ComparisonStrategyFilters.ComparisonStrategy;
import AlejandroNumbers.Filters.ExtraFilters.BigIntFilter;
import AlejandroNumbers.Visualizers.AlejandroNumberGrapher;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class AbnormalDominantAlejandroNumberFinder {
    public static final int DEFAULT_ABNORMALITY_THRESHOLD = 1;
    private final ComparisonStrategy comparisonStrategy;
    private final int maxresults;
    private final boolean indefinite;
    private final BigInteger startedWithSeed;
    private final BigIntFilter filter;
    private final BigInteger filterParam;
    private final int timeoutallowedInMillis;
    private final boolean accountForSeedLenght;
    private final boolean log;
    private int foundResults = 0;
    private int abnormalityIndicator;
    private BigInteger seed;
    private int maxIterations;
    private List<Integer> associatedAbnormalities = new ArrayList<>();
    private List<BigInteger> alejandroNumberSeeds = new ArrayList<>();
    private final boolean allowTimeJumps;
    private Timer timer = new Timer();
    private double timeJumpMultiplicator;

    public AbnormalDominantAlejandroNumberFinder(AbnormalAlejandroNumberFinderParams abnormalAlejandroNumberFinderParams) {
        this.abnormalityIndicator = abnormalAlejandroNumberFinderParams.getAbnormalityIndicator();
        this.seed = abnormalAlejandroNumberFinderParams.getSeed();
        this.startedWithSeed = abnormalAlejandroNumberFinderParams.getStartedWithSeed();
        this.maxIterations = abnormalAlejandroNumberFinderParams.getMaxIterations();
        this.comparisonStrategy = abnormalAlejandroNumberFinderParams.getComparisonStrategy();
        this.maxresults = abnormalAlejandroNumberFinderParams.getMaxresults();
        this.indefinite = abnormalAlejandroNumberFinderParams.isIndefinite();
        this.filter = abnormalAlejandroNumberFinderParams.getFilter();
        this.filterParam = abnormalAlejandroNumberFinderParams.getFilterParam();
        this.allowTimeJumps = abnormalAlejandroNumberFinderParams.isAllowTimeJumps();
        this.timeoutallowedInMillis = abnormalAlejandroNumberFinderParams.getTimeoutallowedInMillis();
        this.timeJumpMultiplicator = abnormalAlejandroNumberFinderParams.getTimeJumpMultiplicator();
        this.accountForSeedLenght = abnormalAlejandroNumberFinderParams.isAccountForSeedLenght();
        this.log = abnormalAlejandroNumberFinderParams.isLog();
    }

    public void setSeed(BigInteger seed) {
        this.seed = seed;
    }

    public void setTimeJumpMultiplicator(double timeJumpMultiplicator) {
        this.timeJumpMultiplicator = timeJumpMultiplicator;
    }

    public int getFoundResults() {
        return foundResults;
    }

    public void stop() {
        this.continueSearching = false;
    }

    boolean continueSearching = true;

    public void startAbnormalSearch() {
        continueSearching = true;
        while (continueSearching || indefinite) {
            DominantAlejandroNumberFinder.getDominantFromSeed(seed, maxIterations, (dominantIntInArray, atSeed) -> {
                if (comparisonStrategy.compare(dominantIntInArray, abnormalityIndicator)) {
                    timer.cancel();
                    startTooLongTimer();
                    if (filter.shouldAllowThroughFilter(atSeed, filterParam)) {

                        if (log)
                            System.out.println(foundResults + ": " + "Dominance of " + dominantIntInArray + " found at seed " + atSeed + " with " + maxIterations + " iterations");
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
        timer = new Timer();
        timer.schedule(new RemindTask(), timeoutallowedInMillis);
    }


    int timeJumps = 1;

    class RemindTask extends TimerTask {
        public void run() {
            timeJumps++;
            Double currentJumpFactor = Math.pow(timeJumps, timeJumpMultiplicator);
            BigDecimal multipliedResultSeed = new BigDecimal(seed).multiply(new BigDecimal(String.valueOf(currentJumpFactor)));
            seed = multipliedResultSeed.toBigInteger();
            if (log) System.out.println("Jumped by Factor " + currentJumpFactor.toString() + " now at seed " + seed);
            timer.cancel(); //Terminate the timer thread
            startTooLongTimer();
        }
    }
}
