public class BSTTest {
    public static void main(String[] args) {
        BST<Integer, String> tree = new BST<>();

        // Insert key-value pairs
        tree.put(5, "five");
        tree.put(3, "three");
        tree.put(7, "seven");
        tree.put(2, "two");
        tree.put(4, "four");
        tree.put(6, "six");
        tree.put(8, "eight");

        // Replace existing value
        String oldValue = tree.put(3, "THREE");
        System.out.println("Old value for key 3: " + oldValue);  // Should print "three"

        // In-order traversal using the iterator
        System.out.println("\nIn-order traversal:");
        for (Integer key : tree) {
            System.out.println("Key: " + key + ", Value: " + tree.get(key));
        }

        // Test contains and get methods
        int testKey = 10;
        System.out.println("\nContains key " + testKey + "? " + tree.contains(testKey));
        System.out.println("Value for key " + testKey + ": " + tree.get(testKey));  // Should return null

        // Print size of the tree
        System.out.println("\nSize of tree: " + tree.size());
    }
}
