package deque;
import java.util.Iterator;

public interface Deque<T>{
   public void addLast(T item);

   public void addFirst(T item);

   public default boolean isEmpty()
    {
        return size()==0;
    }

    public int size();

    public void printDeque();

    public T removeFirst();

   public T removeLast();

    T get(int index);

    boolean equals(Object o);

    Iterator<?> iterator();
}