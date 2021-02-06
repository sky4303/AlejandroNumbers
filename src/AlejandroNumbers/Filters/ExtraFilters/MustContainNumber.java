package AlejandroNumbers.Filters.ExtraFilters;

import java.math.BigInteger;

public class MustContainNumber implements BigIntFilter{
    @Override
    public boolean shouldAllowThroughFilter(BigInteger filteredAgainstBigint, BigInteger filterParameter) {
        return filteredAgainstBigint.toString().contains(filterParameter.toString());
    }
}
