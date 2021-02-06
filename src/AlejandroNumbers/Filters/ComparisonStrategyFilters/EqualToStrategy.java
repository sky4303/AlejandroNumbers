package AlejandroNumbers.Filters.ComparisonStrategyFilters;

public class EqualToStrategy implements ComparisonStrategy {
    @Override
    public boolean compare(int a, int b) {
        return a==b;
    }

    @Override
    public String description() {
        return "equal to ";
    }
}
