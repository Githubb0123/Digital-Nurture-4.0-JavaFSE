public class Main {
    public static void main(String[] args) {
        Product[] products = {
            new Product(101, "Wireless Mouse", "Electronics"),
            new Product(203, "Cotton T-Shirt", "Fashion"),
            new Product(145, "Coffee Mug", "Home"),
            new Product(307, "Running Shoes", "Sports")
        };

        Product linearResult = LinearSearch.search(products, 145);
        System.out.println("Linear Search Result: " + 
            (linearResult != null ? linearResult.getProductName() : "Not Found"));

        BinarySearch.sortProducts(products); 
        Product binaryResult = BinarySearch.search(products, 203);
        System.out.println("Binary Search Result: " + 
            (binaryResult != null ? binaryResult.getProductName() : "Not Found"));
    }
}
