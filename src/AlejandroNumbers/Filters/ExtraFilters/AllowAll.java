package AlejandroNumbers.Filters.ExtraFilters;

import java.math.BigInteger;

public class AllowAll implements BigIntFilter{
    @Override
    public boolean shouldAllowThroughFilter(BigInteger filteredAgainstBigint, BigInteger filterParameter) {
        return true;
    }
}
