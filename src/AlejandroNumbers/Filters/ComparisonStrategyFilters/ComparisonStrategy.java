package AlejandroNumbers.Filters.ComparisonStrategyFilters;

public interface ComparisonStrategy {
    boolean compare(int a, int b);
    String description();
}
