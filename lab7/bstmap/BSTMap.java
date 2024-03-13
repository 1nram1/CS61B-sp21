package bstmap;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class BSTMap<K extends Comparable<K>, V> implements Map61B<K,V>
{
    private class BSTnode{
        private
        BSTnode leftchild;
        BSTnode rightchild;

        K key;

        V value;
        public
        BSTnode(K key,V value) {
            leftchild=null;
            rightchild=null;
            key=key;
            value=value;
        }

//       V get(K k) {
//
//           if(this == null) return null;
//           if(k == null) return null;
//           if ( k.equals(key)) {
//                return this.value;
//            } else if (k.compareTo(key) < 0 ) {
//               return leftchild.get(k);
//           } else {
//               return rightchild.get(k) ;
//           }
//       }
//       public BSTnode put(K key,V value) {
//            if (this == null) {  //this 是null，他还能调用到它的成员函数吗？
//                size += 1;
//                return new BSTnode(key,value);
//            }
//            if(key.compareTo(this.key) < 0) {
//                this.leftchild = this.leftchild.put(key ,value);
//            } else if (key.compareTo(this.key) > 0) {
//                this.leftchild = this.leftchild.put(key , value);
//            } else {
//                this.value = value;
//            }
//          return this;
//       }
    }

    private BSTnode sentinel;
    private int size;

    public BSTMap() {
        sentinel = null ;
        size = 0;
    }
    @Override
    public void clear() {
        size = 0;
        sentinel = null;
    }

    @Override
    public V get(K key) {
        return gethelper(sentinel,key) ;
    }

    private V gethelper(BSTnode T, K key) {
        if(T == null) return null;
           if(key == null) return null;
           if ( key.equals(T.key)) {
                return T.value;
            } else if (key.compareTo(T.key) < 0 ) {
               return gethelper(T.leftchild,key) ;
           } else {
               return gethelper(T.rightchild, key);
           }
    }

@Override

public void put(K key ,V value) {
        puthelper(sentinel,key,value);
}

private BSTnode puthelper (BSTnode T , K key,V value) {
    if(T == null) {
               size += 1;
               return new BSTnode(key,value);
           }
    if(key.compareTo(T.key) < 0) {
               T.leftchild = puthelper(T.leftchild,key,value);
    } else if (key.compareTo(T.key) > 0) {
               T.rightchild = puthelper(T.rightchild ,key , value);
    } else {
               T.value = value;
           }
         return T ;
    }




    /* Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key)
    {
        if(sentinel == null) {
            return false;
        }
        return get(key) != null;
    }


    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
//    @Override
//    public V get(K key){
//        if(sentinel == null) {
//            return null;
//        }
//        V lookup = sentinel.get(key);
//        if (lookup == null) {
//            return null;
//        }
//        return lookup;
//    }
    //get 也可以用helper method 的方法来写，就是把BSTnode 里面的成员函数拿到外面这个类中，设为private ，参数中有一个BSTnode即可.
    //this 换为node，递归调用时改一下格式即可，本质一样。以后可以尽量用helper method



    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size(){
        return size;
    }

    /* Associates the specified value with the specified key in this map. */
//    @Override
//    public void put(K key, V value){
//         sentinel.put(key, value);
//    }



    /* Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException. */
    @Override
    public Set<K> keySet(){
        //throw new UnsupportedOperationException();
        HashSet <K> set =new HashSet<>() ;
        KeySetHelper(sentinel , set) ;
        return set;
    }

    private void KeySetHelper(BSTnode T , Set<K> set) {
        if (T == null) {
            return;
        }
        set.add(T.key);
        KeySetHelper(T.leftchild , set);
        KeySetHelper(T.rightchild , set);
    }

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key){
        throw new UnsupportedOperationException();
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 7. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value){
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
     throw new UnsupportedOperationException();
    }

    public void printInOrder() {
        printInOrderHelper(sentinel);
    }
    private void printInOrderHelper(BSTnode T) {
        if(T == null) {
            return ;
        }
        else {
            printInOrderHelper(T.leftchild );
            System.out.println(T.key.toString() + " -> " + T.value.toString());
            printInOrderHelper(T.rightchild);
        }
    }


}
