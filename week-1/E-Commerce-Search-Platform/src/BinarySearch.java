import java.util.Arrays;
import java.util.Comparator;

public class BinarySearch {
    public static Product search(Product[] sortedProducts, int targetId) {
        int low = 0;
        int high = sortedProducts.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int midId = sortedProducts[mid].getProductId();

            if (midId == targetId) {
                return sortedProducts[mid];
            } else if (midId < targetId) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null; 
    }

    public static void sortProducts(Product[] products) {
        Arrays.sort(products, Comparator.comparingInt(Product::getProductId));
    }
}

