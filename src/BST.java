import java.util.Iterator;
import java.util.Stack;

public class BST<K extends Comparable<K>, V> implements Iterable<K> {
    private Node root;

    private class Node {
        K key;
        V val;
        Node left, right;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    // 1) Size
    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) return 0;
        return 1 + size(node.left) + size(node.right);
    }

    // 2) Put: now returns previous value
    public V put(K key, V val) {
        V oldValue = get(key);
        root = put(root, key, val);
        return oldValue;
    }

    private Node put(Node node, K key, V val) {
        if (node == null) return new Node(key, val);
        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = put(node.left, key, val);
        else if (cmp > 0) node.right = put(node.right, key, val);
        else node.val = val;
        return node;
    }

    // 3) Get
    public V get(K key) {
        return get(root, key);
    }

    private V get(Node node, K key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) return get(node.left, key);
        else if (cmp > 0) return get(node.right, key);
        else return node.val;
    }

    // 4) Contains
    public boolean contains(K key) {
        return get(key) != null;
    }

    // 5) Delete (returns boolean success flag)
    public boolean delete(K key) {
        if (!contains(key)) return false;
        root = delete(root, key);
        return true;
    }

    private Node delete(Node node, K key) {
        if (node == null) return null;

        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = delete(node.left, key);
        else if (cmp > 0) node.right = delete(node.right, key);
        else {
            if (node.right == null) return node.left;
            if (node.left == null) return node.right;

            Node t = node;
            node = min(t.right);
            node.right = deleteMin(t.right);
            node.left = t.left;
        }
        return node;
    }

    private Node deleteMin(Node node) {
        if (node.left == null) return node.right;
        node.left = deleteMin(node.left);
        return node;
    }

    // 6) Public min key
    public K min() {
        if (root == null) return null;
        return min(root).key;
    }

    private Node min(Node node) {
        if (node.left == null) return node;
        return min(node.left);
    }

    // 7) In-order traversal iterator
    @Override
    public Iterator<K> iterator() {
        return new InOrderIterator(root);
    }

    private class InOrderIterator implements Iterator<K> {
        private Stack<Node> stack;

        public InOrderIterator(Node root) {
            stack = new Stack<>();
            pushLeft(root);
        }

        private void pushLeft(Node node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public K next() {
            Node node = stack.pop();
            pushLeft(node.right);
            return node.key;
        }
    }

    // 8) Optional: toString to view tree as sorted key list
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Iterator<K> it = iterator();
        while (it.hasNext()) {
            K key = it.next();
            sb.append(key);
            if (it.hasNext()) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
