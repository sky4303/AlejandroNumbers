package AlejandroNumbers.Filters.ExtraFilters;

import java.math.BigInteger;

public class ExclusionFilter implements BigIntFilter {
    @Override
    public boolean shouldAllowThroughFilter(BigInteger filteredAgainstBigint, BigInteger filterParameter) {
        return !filteredAgainstBigint.toString().contains(filteredAgainstBigint.toString());
    }
}
