package AlejandroNumbers.Filters.ExtraFilters;

import java.math.BigInteger;

public interface BigIntFilter {
    boolean shouldAllowThroughFilter(BigInteger filteredAgainstBigint, BigInteger filterParameter);
}
