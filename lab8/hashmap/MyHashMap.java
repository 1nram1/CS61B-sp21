package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    //存bucket的那个核心数组，hashtable的核心数组
    //这个核心数组是个指针数组，但每个指针是指向什么的，有待确定
    //这是个指向collection<node>的数组，collection<node>可以是多种数据结构


     private int capacity; //number of buckets

     private int size; //number of Node;
    // private HashSet<K> set;

     private double loadFactor ;

     private static final int DEFAULT_INITIAL_SIZE = 16;
     private static final double DEFAULT_LOAD_FACTOR = 0.75;


    /** Constructors */
    public MyHashMap() {
        this(DEFAULT_INITIAL_SIZE,DEFAULT_LOAD_FACTOR);
    }

    public MyHashMap(int initialSize) {
        this(initialSize,DEFAULT_INITIAL_SIZE);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        capacity = initialSize;
        loadFactor = maxLoad;
        buckets = createTable(capacity);
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    @SuppressWarnings("unchecked")
    private Collection<Node>[] createTable(int tableSize) {
        Collection<Node>[] table = new Collection[tableSize];
        for (int i = 0; i < tableSize; i+= 1) {
            table[i] = createBucket();
        }
        return table;
    }

    private int getIndex(K key) {
        return Math.floorMod(key.hashCode(),capacity);
    }
    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!

    /** Removes all of the mappings from this map. */
    @Override
     public void clear() {
        buckets = createTable(DEFAULT_INITIAL_SIZE);
        size = 0;
    }

    /** Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        int index = getIndex(key);
        if (buckets[index].isEmpty()) {
            return false;
        }
        //buckets[index]是个可迭代的数据结构
        for (Node node : buckets[index]) {
            if (node.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        int index = getIndex(key);//就是通过key来找结点这一步体现了是一个map。hashcode是个中间商
        if (buckets[index].isEmpty()) {
            return null;
        }
        for (Node node : buckets[index]) {
            if(node.key.equals(key)) {
                return node.value;
            }
        }
        return null;
    }

    @Override
    /** Returns the number of key-value mappings in this map. */
    public int size() {
        return size;
    }


    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    @Override
    public void put(K key, V value) {
        int index = getIndex(key) ;
//        if(buckets[index].isEmpty()) {
//            buckets[index].add(new Node(key,value));
//        }
        if(get(key) != null) {
            for (Node node : buckets[index]) {
                if (node.key.equals(key)) {
                    node.value=value;
                }
            }
        } else {
            buckets[index].add(new Node(key,value));
            size += 1;
        }
    }




    /** Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        HashSet<K> set = new HashSet<>();
        for (Collection<Node> bucket : buckets) {
            for (Node node : bucket) {
                set.add(node.key);
            }
        }
        //双重遍历去遍历每一个结点
        return set;
    }

/**
 * Removes the mapping for the specified key from this map if present.
 * Not required for Lab 8. If you don't implement this, throw an
 * UnsupportedOperationException.
 */
   @Override
   //你会发现大部分操作。都是要先找到结点所在哪个buckets，都要先调用getindex
    public V remove(K key) {
       if (!containsKey(key)) {
           return null;
       }
       V value = null;
        int index = getIndex(key);
        for (Node node :buckets[index]) {
            if(node.key.equals(key)) {
                value = node.value;
                buckets[index].remove(node);
                size -=1;
            }
        }
        return value;
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    @Override
    public V remove(K key, V value) {
        if (!containsKey(key)) {
            return null;
        }
        int index = getIndex(key);
        V result =null;
        for (Node node :buckets[index]) {
            if(node.key.equals(key) && node.value.equals(value)) {
                buckets[index].remove(node);
                size -=1;
               return node.value;
            }
        }
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }

}

//when meet negative hashcode we use Math.floorMod  System.out.println(-1 % 4);
//    System.out.println(Math.floorMod(-1, 4))
