package deque;

import java.util.Iterator;
import java.util.Objects;

public class LinkListDeque<T> implements Iterable<T> , Deque<T>{
   private class Node{
       private final T item;
       private Node next;
       private Node prev;
       Node (T i,Node n, Node p)
       {
           item=i;
           next=n;
           prev=p;
       }
   }
   private final Node sentinel;
   private int size;

   public LinkListDeque(){
       sentinel=new Node(null,null,null);
       sentinel.next=sentinel;
       sentinel.prev=sentinel;
       size=0;
   }

   @Override
   public void addFirst(T x){
       Node temp=new Node(x,sentinel.next,sentinel);
       sentinel.next.prev=temp;
       sentinel.next=temp;
      size+=1;
   }
   @Override
   public void addLast(T x){
       Node temp=new Node(x,sentinel,sentinel.prev);
       sentinel.prev.next=temp;
       sentinel.prev=temp;
       size+=1;
   }
   @Override
   public int size(){
       return size;
   }

    @Override
    public void printDeque(){
       if(!this.isEmpty()){
           Node curr=sentinel.next;
           while(curr.next!=sentinel){
               System.out.print(curr.item+" ");
               curr=curr.next;
           }
           System.out.print(curr.item);
       }
       System.out.print("\n");
    }

   @Override
   public T removeFirst(){
       if(this.isEmpty()){
           return null;
       }
       Node temp=sentinel.next;
       temp.next.prev=sentinel;
       sentinel.next=temp.next;
       size-=1;
       return temp.item;
   }
    @Override
    public T removeLast(){
       if(this.isEmpty()){
           return null;
       }
       Node temp=sentinel.prev;
       sentinel.prev=temp.prev;
       temp.prev.next=sentinel;
       size-=1;
       return temp.item;
    }

    @Override
    public T get(int index){
       if(index >= size || index<0){
           return null;
       }
       Node curr;
       if(index <= size/2) {
           curr = sentinel.next;
           for (int i = 0; i < index; i++) {
               curr = curr.next;
           }
       }else{
           curr=sentinel.prev;
           for(int i=size-1;i>index;i--){
               curr=curr.prev;
           }
       }
       return curr.item;
    }
    public T getRecursive(int index){
       if(index >= size || index<0){
           return null;
       }
       return getRecursiveHelper(index,sentinel.next);//
    }
   //we have to create a private helper method to assist us to make a recursive call related to  array slice
    private T getRecursiveHelper(int index,Node curr){
       if(index==0){
           return curr.item;
       }
       return getRecursiveHelper(index-1,curr.next);
    }
   @Override
   public boolean equals(Object o){
       if(o==null){
           return false;
       }
       if(o==this){
           return true;
       }
       if(!(o instanceof Deque)){//judge whether o is a Deque or not
           return false;
       }
       Deque<?> other=(Deque<?>) o;
       if(this.size()!=other.size()){
           return false;
       }
       for(int i=0;i<size;i++){
           if(!Objects.equals(this.get(i),other.get(i))){
               return false;
           }
       }
       return true;
   }



   @Override
    public Iterator<T> iterator(){
       return new LinkListDequeIterator();
   }
    private class LinkListDequeIterator implements Iterator<T>{
       private Node curr;
       LinkListDequeIterator(){
           curr=sentinel.next;
       }
       public boolean hasNext(){
           return curr !=sentinel;
       }//why?it should be curr.next!=sentinel

       public T next(){
           T item=curr.item;
           curr=curr.next;
           return item;
       }




    }
}
