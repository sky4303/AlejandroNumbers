package AlejandroNumbers.Queries;

import AlejandroNumbers.Filters.ComparisonStrategyFilters.ComparisonStrategy;
import AlejandroNumbers.Filters.ExtraFilters.BigIntFilter;

import java.math.BigInteger;

public class AbnormalAlejandroNumberFinderParams {
    private final int abnormalityIndicator;
    private final BigInteger seed;
    private final BigInteger startedWithSeed;
    private final int maxIterations;
    private final ComparisonStrategy comparisonStrategy;
    private final int maxresults;
    private final boolean indefinite;
    private final BigIntFilter filter;
    private final BigInteger filterParam;
    private final boolean allowTimeJumps;
    private final int timeoutallowedInMillis;
    private final float timeJumpMultiplicator;
    private final boolean accountForSeedLenght;
    private final boolean log;

    public AbnormalAlejandroNumberFinderParams(int abnormalityIndicator, BigInteger startseed, int maxIterations, ComparisonStrategy comparisonStrategy, int maxResults, boolean indefinite, BigIntFilter filter, BigInteger filterParam, boolean allowTimeJumps, int timeoutAllowed, float timejumpMultiplicator, boolean accountForSeedLenght, boolean log) {
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
        this.timeoutallowedInMillis = timeoutAllowed;
        this.timeJumpMultiplicator = timejumpMultiplicator;
        this.accountForSeedLenght = accountForSeedLenght;
        this.log = log;
    }

    public int getAbnormalityIndicator() {
        return abnormalityIndicator;
    }

    public BigInteger getSeed() {
        return seed;
    }

    public BigInteger getStartedWithSeed() {
        return startedWithSeed;
    }

    public int getMaxIterations() {
        return maxIterations;
    }

    public ComparisonStrategy getComparisonStrategy() {
        return comparisonStrategy;
    }

    public int getMaxresults() {
        return maxresults;
    }

    public boolean isIndefinite() {
        return indefinite;
    }

    public BigIntFilter getFilter() {
        return filter;
    }

    public BigInteger getFilterParam() {
        return filterParam;
    }

    public boolean isAllowTimeJumps() {
        return allowTimeJumps;
    }

    public int getTimeoutallowedInMillis() {
        return timeoutallowedInMillis;
    }

    public float getTimeJumpMultiplicator() {
        return timeJumpMultiplicator;
    }

    public boolean isAccountForSeedLenght() {
        return accountForSeedLenght;
    }

    public boolean isLog() {
        return log;
    }
}
