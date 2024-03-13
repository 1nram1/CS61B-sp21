//package bstmap;
//
//import java.util.HashSet;
//import java.util.Iterator;
//import java.util.Set;
//
//
//public class BSTMap<K extends Comparable<K>, V> implements Map61B<K,V>
//{
//    private class BSTnode {
//
//        public BSTnode leftchild;
//        public BSTnode rightchild;
//
//        public K key;
//
//        public V value;
//
//        public BSTnode(K key, V value) {
//            leftchild = null;
//            rightchild = null;
//            key = key;
//            value = value;
//        }
//    }
//
//
//    private BSTnode sentinel;
//    private int size;
//
//    public BSTMap() {
//        sentinel = null ;
//        size = 0;
//    }
//    @Override
//    public void clear() {
//        size = 0;
//        sentinel = null;
//    }
//
//    @Override
//    public V get(K key) {
//        return gethelper(sentinel,key) ;
//    }
//
//    private V gethelper(BSTnode T, K key) {
//        if(T == null) return null;
//           if(key == null) return null;
//           if ( key.equals(T.key)) {
//                return T.value;
//            } else if (key.compareTo(T.key) < 0 ) {
//               return gethelper(T.leftchild,key) ;
//           } else {
//               return gethelper(T.rightchild, key);
//           }
//    }
//
//@Override
//
//public void put(K key ,V value) {
//        puthelper(sentinel,key,value);
//}
//
//private BSTnode puthelper (BSTnode T , K key,V value) {
//    if(T == null) {
//               size += 1;
//               return new BSTnode(key,value);
//           }
//    if(key.compareTo(T.key) < 0) {
//               T.leftchild = puthelper(T.leftchild,key,value);
//    } else if (key.compareTo(T.key) > 0) {
//               T.rightchild = puthelper(T.rightchild ,key , value);
//    } else {
//               T.value = value;
//           }
//         return T ;
//    }
//
//
//
//
//    /* Returns true if this map contains a mapping for the specified key. */
//    @Override
//    public boolean containsKey(K key)
//    {
//        if(sentinel == null) {
//            return false;
//        }
//        return get(key) != null;
//    }
//
//
//    /* Returns the value to which the specified key is mapped, or null if this
//     * map contains no mapping for the key.
//     */
////    @Override
////    public V get(K key){
////        if(sentinel == null) {
////            return null;
////        }
////        V lookup = sentinel.get(key);
////        if (lookup == null) {
////            return null;
////        }
////        return lookup;
////    }
//    //get 也可以用helper method 的方法来写，就是把BSTnode 里面的成员函数拿到外面这个类中，设为private ，参数中有一个BSTnode即可.
//    //this 换为node，递归调用时改一下格式即可，本质一样。以后可以尽量用helper method
//
//
//
//    /* Returns the number of key-value mappings in this map. */
//    @Override
//    public int size(){
//        return size;
//    }
//
//    /* Associates the specified value with the specified key in this map. */
////    @Override
////    public void put(K key, V value){
////         sentinel.put(key, value);
////    }
//
//
//
//    //Returns a Set view of the keys contained in this map. Not required for Lab 7.
//
//    @Override
//    public Set<K> keySet(){
//        //throw new UnsupportedOperationException();
//        HashSet <K> set =new HashSet<>() ;
//        KeySetHelper(sentinel , set) ;
//        return set;
//    }
//
//    private void KeySetHelper(BSTnode T , Set<K> set) {
//        if (T == null) {
//            return;
//        }
//        set.add(T.key);
//        KeySetHelper(T.leftchild , set);
//        KeySetHelper(T.rightchild , set);
//    }
//
//    /* Removes the mapping for the specified key from this map if present.
//      */
//
////    @Override
////    public
//    @Override
//    public V remove(K key){
//       throw  new UnsupportedOperationException() ;
//    }
//
////    private V removehelper (BSTnode T, K key) {
////        if(T == null) {
////            return null;
////        }
////        if(gethelper(T,key) == null) {
////            return null;
////        }
////    }
//
//    /* Removes the entry for the specified key only if it is currently mapped to
//     * the specified value. Not required for Lab 7. If you don't implement this,
//     * throw an UnsupportedOperationException.*/
//    @Override
//    public V remove(K key, V value){
//        throw new UnsupportedOperationException();
//    }
//
//    @Override
//    public Iterator<K> iterator() {
//     throw new UnsupportedOperationException();
//    }
//
//    public void printInOrder() {
//        printInOrderHelper(sentinel);
//    }
//    private void printInOrderHelper(BSTnode T) {
//        if(T == null) {
//            return ;
//        }
//        else {
//            printInOrderHelper(T.leftchild );
//            System.out.println(T.key.toString() + " -> " + T.value.toString());
//            printInOrderHelper(T.rightchild);
//        }
//    }
//
//
//}

package bstmap;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class BSTNode {
        public K key;
        public V value;
        public BSTNode left;
        public BSTNode right;

        public BSTNode(K k, V v) {
            key = k;
            value = v;
        }
    }

    private BSTNode root;
    private int size;

    public BSTMap() {
        root = null;
        size = 0;
    }

    public void printInOrder() {
        printInOrderHelper(root);
    }

    private void printInOrderHelper(BSTNode node) {
        if (node == null) {
            return;
        }
        printInOrderHelper(node.left);
        System.out.println(node.key.toString() + " -> " + node.value.toString());
        printInOrderHelper(node.right);
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        return containsKeyHelper(root, key);
    }

    private boolean containsKeyHelper(BSTNode node, K key) {
        if (node == null) {
            return false;
        }
        int cmp = key.compareTo(node.key);
        if (cmp > 0) {
            return containsKeyHelper(node.right, key);
        } else if (cmp < 0) {
            return containsKeyHelper(node.left, key);
        } else {
            return true;
        }
    }

    @Override
    public V get(K key) {
        return getHelper(root, key);
    }

    private V getHelper(BSTNode node, K key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp > 0) {
            return getHelper(node.right, key);
        } else if (cmp < 0) {
            return getHelper(node.left, key);
        } else {
            return node.value;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        root = putHelper(root, key, value);
    }

    private BSTNode putHelper(BSTNode node, K key, V value) {
        if (node == null) {
            size += 1;
            return new BSTNode(key, value);
        }
        int cmp = key.compareTo(node.key);
        if (cmp > 0) {
            node.right = putHelper(node.right, key, value);
        } else if (cmp < 0) {
            node.left = putHelper(node.left, key, value);
        } else {
            node.value = value;
        }
        return node;
    }

    @Override
    public Set<K> keySet() {
        HashSet<K> set = new HashSet<>();
        keySetHelper(root, set);
        return set;
    }

    private void keySetHelper(BSTNode node, Set<K> set) {
        if (node == null) {
            return;
        }
        set.add(node.key);
        keySetHelper(node.left, set);
        keySetHelper(node.right, set);
    }

    @Override
    public V remove(K key) {
        if (!containsKey(key)) {
            return null;
        }
        V value = get(key);
        root = removeHelper(root, key);
        size -= 1;
        return value;
    }

    private BSTNode removeHelper(BSTNode node, K key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp > 0) {
            node.right = removeHelper(node.right, key);
        } else if (cmp < 0) {
            node.left = removeHelper(node.left, key);
        } else {
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            BSTNode temp = node;
            node = minNode(temp.right);
            node.right = removeMin(temp.right);
            node.left = temp.left;
        }
        return node;
    }

    private BSTNode minNode(BSTNode node) {
        if (node.left == null) {
            return node;
        }
        return minNode(node.left);
    }

    private BSTNode removeMin(BSTNode node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = removeMin(node.left);
        return node;
    }

    @Override
    public V remove(K key, V value) {
        if (!containsKey(key) || !get(key).equals(value)) {
            return null;
        }
        root = removeHelper(root, key);
        size -= 1;
        return value;
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }
}

