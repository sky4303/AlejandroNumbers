package AlejandroNumbers;

import AlejandroNumbers.Filters.ComparisonStrategyFilters.EqualToStrategy;
import AlejandroNumbers.Filters.ComparisonStrategyFilters.GreaterThanStrategy;
import AlejandroNumbers.Filters.ExtraFilters.AllowAll;
import AlejandroNumbers.Filters.ExtraFilters.MustContainNumber;
import AlejandroNumbers.Queries.AbnormalDominantAlejandroNumberFinder;

import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        AbnormalDominantAlejandroNumberFinder abnormalDominantAlejandroNumberFinder =
                new AbnormalDominantAlejandroNumberFinder(1,
                        new BigInteger("1"),
                        5,
                        new EqualToStrategy(), 200000, false, new AllowAll(), new BigInteger("0"), true);
        abnormalDominantAlejandroNumberFinder.startAbnormalSearch();
    }

}
