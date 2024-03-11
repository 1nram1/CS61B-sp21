package deque;

import java.util.Iterator;
import java.util.Objects;

public class ArrayDeque<T> implements Iterable<T>,Deque<T>{
    // it is similar to the circular queue
    private T[] items;
    private int size;//size可以通过nextfirst和 nextlast计算出来的，还需要写吗
    private int nextfirst;

    private int nextlast;
    @SuppressWarnings("unchecked")
    public ArrayDeque(){
        items=(T[]) new Object[8];
        size=0;
        nextfirst=7;//the position before the first element
        nextlast=0; //the position after the last element
    }
    //循环队列，或者说这种有循环特征的数组，无论是nextfirst（指向第一个元素的前面一个位置）
    // 还是nextlast（指向前一个元素的后一个位置），抑或是别的类似与迭代器，指向数组某个位置的指针
    // 移动的规则都是一样的
    //往后移动，就是（ +1）%items.length
    //往前移动，就是（-1+items.length)%items.length

    @SuppressWarnings("unchecked")
private void resize(int capacity){
         T[] a=(T[]) new Object[capacity];
         int oldIndex=(nextfirst+1) % items.length;
         for(int newIndex = 0;newIndex < size;newIndex++){
             a[newIndex]=items[oldIndex];
             oldIndex=(oldIndex+1)%items.length;
         }//就是一个一个把旧数组复制到新数组
        items=a;
         nextfirst=capacity-1;
         //数组的最后一个位置，因为first指向第一个元素的前一个位置，我们用的是循环数组
         nextlast=size;
    }
    @Override
   public void addFirst(T item){
       if(size== items.length){
           resize(size*2);
       }
       items[nextfirst]=item;
       nextfirst=(nextfirst-1+items.length)%items.length;
       //与addlast后，nextlast指针需要移动位置同理，-1+length仅仅为了考虑到指针循环的特殊情况而已，
        // 理解为nextfirst往前移动一位即可
        size+=1;
    }
    @Override
    public void addLast(T item){
         if(size==items.length){
             resize(size*2);
         }
         items[nextlast]=item;
         nextlast=(nextlast+1)%items.length;
         //仅仅是为了考虑需要循环的特殊情况而已，理解为nextlast往后移一位即可
         size+=1;

    }
    @Override
    public int size(){
       return size;
    }

    @Override
    public void printDeque(){
      for(int i=(nextfirst+1)%items.length;i!=nextlast;i=(i+1)%items.length){
          System.out.print(items[i]+" ");
      }
    }

    //a helper method ,which can remove thoroughly and do resize work
    private T removeTemplate(int next){
        T temp=items[next];
        items[next]=null;
        size-=1;
        if(items.length >= 16 && size < items.length/4){
            resize(items.length/2);
        }
        return temp;
    }
    @Override
    public T removeFirst(){
      if(size==0){
          return null;
      }
      nextfirst=(nextfirst+1)%items.length;
      return removeTemplate(nextfirst);
    }
    @Override
    public T removeLast(){
         if(size==0){
             return null;
         }
         nextlast=(nextlast-1+ items.length)% items.length;//nextlast move backwards
         return removeTemplate(nextlast);
    }
    @Override
    public T get(int index){
        if(index<0 || index >= size)// index默认从0开始
        {
            return null;
        }
        return items[(nextfirst+1+index)% items.length] ;
    }
    @Override//equals of arrayDeque is the same as that of LinkListDeque
    public boolean equals(Object o){
       if(o==null){
           return false;
       }
       if(o==this){
           return true;
       }
       if(!(o instanceof Deque)){
           return false;
       }
       Deque<?> other=(Deque<?>) o;
       if(this.size() != other.size()){
           return false;
       }
       for(int i=0;i<size;i++){
           if(!(Objects.equals(this.get(i),other.get(i)))){
               return false;
           }
       }
       //compare one by one
        return true;
    }
    @Override
    public Iterator<T> iterator(){
          return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T>{
        private int wizPos;
        ArrayDequeIterator(){
            wizPos=0;
        }
        public boolean hasNext(){
            return wizPos<size;
        }
        public T next(){
            T returnItem=items[(nextfirst+1+wizPos)%items.length];
            wizPos += 1;
            return returnItem;
        }
    }
}