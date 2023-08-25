package lab9;

import com.sun.source.tree.ReturnTree;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Your name here
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns the value mapped to by KEY in the subtree rooted in P.
     *  or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if (p == null) {
            return null;
        }
        if (p.key.compareTo(key) == 0) {
            return p.value;
        } else if (p.key.compareTo(key) > 0) {
            return getHelper(key, p.left);
        } else {
            return getHelper(key, p.right);
        }
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return getHelper(key, root);
    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
      * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        if (p == null) {
            return new Node(key, value);
        }
        if (p.key.compareTo(key) == 0) {
            p.value = value;
        } else if (p.key.compareTo(key) > 0) {
            p.left = putHelper(key, value, p.left);
        } else {
            p.right = putHelper(key, value, p.right);
        }
        return p;
    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        root = putHelper(key, value, root);
        size++;
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////
    private void traverseAndAdd(Set<K> keys, Node p) {
        if (p == null) {
            return;
        }
        keys.add(p.key);
        traverseAndAdd(keys, p.left);
        traverseAndAdd(keys, p.right);
    }

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        traverseAndAdd(set, root);
        return set;
    }

    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    //findSuccessor 找到欲删除节点的右子树的最左节点
    private Node findSuccessor(Node p) {
        if (p.left == null) {
            return p;
        }
        return findSuccessor(p.left);
    }
    private V removeHelper(Node p, K target) {
        if (p == null) {
            return null;
        }
        if ( p.key.equals(target)) {
            V ret = p.value;
            // TODO: 删除key后如何重新填补二叉树
            if (p.left == null && p.right == null) {
                p = null;
            } else if (p.left == null || p.right == null) {
                if ( p.left != null) {
                    p = p.left;
                } else {
                    p = p.right;
                    p.right = null;
                }
            } else {
                Node successor = findSuccessor(p.right);
                p.value = successor.value;
                p.key = successor.key;
                successor = null;
            }
            return ret;
        }
        int cmp = target.compareTo(p.key);
        if (cmp < 0) {
            return removeHelper(p.left, target);
        } else {
            return removeHelper(p.right, target);
        }
    }
    @Override
    public V remove(K key) {
        size--;
        return removeHelper(root, key);
    }

    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }
}
