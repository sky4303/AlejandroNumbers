package AlejandroNumbers.Filters.ComparisonStrategyFilters;

public class LessThanStrategy implements ComparisonStrategy {

    @Override
    public boolean compare(int a, int b) {
        return a<b;
    }

    @Override
    public String description() {
        return "less than ";
    }
}
