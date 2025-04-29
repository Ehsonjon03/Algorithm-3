import java.util.LinkedList;

public class MyHashTable<K, V> {
    private static final int DEFAULT_SIZE = 11; // Default size of the hash table
    private LinkedList<HashNode<K, V>>[] chainArray;

    // Constructor initializes the hash table with the default size
    public MyHashTable() {
        chainArray = new LinkedList[DEFAULT_SIZE];
        for (int i = 0; i < DEFAULT_SIZE; i++) {
            chainArray[i] = new LinkedList<>();
        }
    }

    // Method to insert a key-value pair into the hash table
    public void put(K key, V value) {
        int index = hash(key); // Get the hash index
        HashNode<K, V> newNode = new HashNode<>(key, value); // Create a new node
        chainArray[index].add(newNode); // Add the node to the appropriate chain
    }

    // Method to retrieve a value associated with a key
    public V get(K key) {
        int index = hash(key); // Get the hash index
        for (HashNode<K, V> node : chainArray[index]) {
            if (node.key.equals(key)) { // Check for matching key
                return node.value; // Return the associated value
            }
        }
        return null; // Return null if the key is not found
    }

    // Hashing function to calculate the index for a given key
    private int hash(K key) {
        return Math.abs(key.hashCode()) % chainArray.length; // Return the index within the bounds of the array
    }

    // Method to print the size of each bucket (linked list) in the hash table
    public void printBucketSizes() {
        for (int i = 0; i < chainArray.length; i++) {
            System.out.println("Bucket " + i + " contains " + chainArray[i].size() + " elements.");
        }
    }

    // Inner class to represent each node in the linked list
    private static class HashNode<K, V> {
        K key;
        V value;

        // Constructor to initialize the node with key-value pair
        HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}