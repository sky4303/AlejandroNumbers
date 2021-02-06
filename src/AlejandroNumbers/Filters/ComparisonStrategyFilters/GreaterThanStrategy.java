package AlejandroNumbers.Filters.ComparisonStrategyFilters;

public class GreaterThanStrategy implements ComparisonStrategy {

    @Override
    public boolean compare(int a, int b) {
        return a>b;
    }

    @Override
    public String description() {
        return "greater than ";
    }
}
