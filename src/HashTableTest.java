import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class HashTableTest {
    public static void main(String[] args) {
        MyHashTable<MyTestingClass, String> table = new MyHashTable<>();

        Random random = new Random();
        Set<MyTestingClass> keySet = new HashSet<>();

        int duplicates = 0;
        int totalInsertions = 10000;

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < totalInsertions; i++) {
            MyTestingClass key = new MyTestingClass(random.nextInt(100000), "Name" + i);
            if (!keySet.add(key)) {
                duplicates++;
            }
            table.put(key, "Value" + i);
        }

        long endTime = System.currentTimeMillis();

        // Print summary info
        System.out.println("Total entries inserted: " + totalInsertions);
        System.out.println("Duplicate keys encountered: " + duplicates);
        System.out.println("Time taken: " + (endTime - startTime) + " ms");

        // Print number of elements in each bucket
        table.printBucketSizes();
    }
}
