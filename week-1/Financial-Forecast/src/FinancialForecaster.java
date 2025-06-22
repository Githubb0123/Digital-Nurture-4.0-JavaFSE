import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FinancialForecaster {
    private static int currentDepth = 0;
    private static int maxDepth = 0;
    
    /**
     * Recursively computes future value applying growth rates
     * @param baseValue   Initial investment amount
     * @param growthRates Array of growth rates (e.g., 0.05 for 5%)
     * @param index       Current period index to process
     * @return Forecasted value after applying all rates
     */
    public static double forecastRecursive(double baseValue, double[] growthRates, int index) {
        currentDepth++;
        maxDepth = Math.max(maxDepth, currentDepth);
        
        if (index < 0) {
            currentDepth--;
            return baseValue;
        }
        
        double prevValue = forecastRecursive(baseValue, growthRates, index - 1);
        double result = prevValue * (1 + growthRates[index]);
        
        currentDepth--;
        return result;
    }
    
    /**
     * Iterative implementation for better performance
     * @return Forecasted value after applying all growth rates
     */
    public static double forecastIterative(double baseValue, double[] growthRates) {
        double value = baseValue;
        for (double rate : growthRates) {
            value *= (1 + rate);
        }
        return value;
    }
    
    /**
     * Memoized recursion for efficient repeated queries
     * @param cache Stores intermediate results (period -> value)
     * @return Forecasted value for specified period
     */
    public static double forecastMemo(double baseValue, double[] growthRates, int index, 
                                      Map<Integer, Double> cache) {
        if (index < 0) return baseValue;
        
        if (cache.containsKey(index)) {
            return cache.get(index);
        }
        
        double prevValue = forecastMemo(baseValue, growthRates, index - 1, cache);
        double result = prevValue * (1 + growthRates[index]);
        cache.put(index, result);
        
        return result;
    }
    
    public static void main(String[] args) {
        double baseAmount = 1_000.0;
        double[] annualRates = {0.07, 0.06, 0.065, 0.055}; 
        
        System.out.println("=== Financial Forecasting Demo ===");
        System.out.println("Base amount: ₹" + baseAmount);
        System.out.println("Growth rates (annual): " + Arrays.toString(annualRates));
        System.out.println();
        
        maxDepth = 0;
        double recursiveResult = forecastRecursive(baseAmount, annualRates, annualRates.length - 1);
        System.out.printf("1) Recursive result: ₹%.2f  (max stack depth = %d)%n", 
                         recursiveResult, maxDepth);
        
        double iterativeResult = forecastIterative(baseAmount, annualRates);
        System.out.printf("2) Iterative result: ₹%.2f  (no recursion stack)%n", iterativeResult);
        
        Map<Integer, Double> cache = new HashMap<>();
        double memoizedResult = forecastMemo(baseAmount, annualRates, annualRates.length - 1, cache);
        System.out.printf("3) Memoized recursion: ₹%.2f  (cache size = %d)%n", 
                         memoizedResult, cache.size());
        
        System.out.println("\n=== Performance: Repeated Queries ===");
        int[] queryPeriods = {1, 3}; 
        
        long startTime = System.nanoTime();
        double iterYear2 = forecastIterative(baseAmount, Arrays.copyOfRange(annualRates, 0, queryPeriods[0] + 1));
        long iterTime1 = System.nanoTime() - startTime;
        
        startTime = System.nanoTime();
        double iterYear4 = forecastIterative(baseAmount, Arrays.copyOfRange(annualRates, 0, queryPeriods[1] + 1));
        long iterTime2 = System.nanoTime() - startTime;
        
        cache.clear();
        startTime = System.nanoTime();
        double memoYear2 = forecastMemo(baseAmount, annualRates, queryPeriods[0], cache);
        long memoTime1 = System.nanoTime() - startTime;
        
        startTime = System.nanoTime();
        double memoYear4 = forecastMemo(baseAmount, annualRates, queryPeriods[1], cache);
        long memoTime2 = System.nanoTime() - startTime;
        
        System.out.printf("Iterative: Year 2 (₹%.2f) in %,d ns | Year 4 (₹%.2f) in %,d ns%n",
                         iterYear2, iterTime1, iterYear4, iterTime2);
        System.out.printf("Memoized: Year 2 (₹%.2f) in %,d ns | Year 4 (₹%.2f) in %,d ns%n",
                         memoYear2, memoTime1, memoYear4, memoTime2);
        
        System.out.println("\n=== Complexity Analysis ===");
        System.out.println("Pure Recursion:");
        System.out.println("  • Time: O(n) - n recursive calls");
        System.out.println("  • Space: O(n) stack depth - risk of stack overflow");
        System.out.println("\nIterative Approach:");
        System.out.println("  • Time: O(n) - single pass through rates");
        System.out.println("  • Space: O(1) - constant memory usage");
        System.out.println("\nMemoized Recursion:");
        System.out.println("  • Time: O(n) first call, O(1) subsequent queries");
        System.out.println("  • Space: O(n) for cache storage");
        
        System.out.println("\n=== Optimization Recommendations ===");
        System.out.println("1. Use iterative approach for single calculations");
        System.out.println("2. Use memoization for repeated partial queries");
        System.out.println("3. For large datasets (>10,000 periods):");
        System.out.println("   - Precompute all values iteratively");
        System.out.println("   - Store results in array for O(1) access");
        System.out.println("4. Handle extremely large periods with log-space calculations");
    }
}