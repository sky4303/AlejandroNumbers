package AlejandroNumbers;

import AlejandroNumbers.Filters.ComparisonStrategyFilters.EqualToStrategy;
import AlejandroNumbers.Filters.ComparisonStrategyFilters.GreaterThanStrategy;
import AlejandroNumbers.Filters.ExtraFilters.AllowAll;
import AlejandroNumbers.Filters.ExtraFilters.ExclusionFilter;
import AlejandroNumbers.Filters.ExtraFilters.MustContainNumber;
import AlejandroNumbers.Queries.AbnormalAlejandroNumberFinderParams;
import AlejandroNumbers.Queries.AbnormalDominantAlejandroNumberFinder;
import AlejandroNumbers.optimizers.AbnormalAlejandroNumberFinderOptimizer;
import AlejandroNumbers.optimizers.OnDoubleOptimizationFinishedListener;

import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        AbnormalAlejandroNumberFinderParams abnormalAlejandroNumberFinderParams = new AbnormalAlejandroNumberFinderParams(
                1, new BigInteger("1"), 10, new GreaterThanStrategy(),
                100000, true, new AllowAll(), new BigInteger("22"), true,
                10000, 1f, true, false);

        AbnormalAlejandroNumberFinderOptimizer abnormalAlejandroNumberFinderOptimizer =
                new AbnormalAlejandroNumberFinderOptimizer(1, 0.0001, 2.0, 200000);

        abnormalAlejandroNumberFinderOptimizer.setOptimizationFinishedListener((optimizedValue, reachedResultSze)
                -> System.out.println("Optimmization found at " + optimizedValue + " with " + reachedResultSze + " results."));

        abnormalAlejandroNumberFinderOptimizer.optimize(abnormalAlejandroNumberFinderParams);
    }
}
