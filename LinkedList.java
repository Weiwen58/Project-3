// Written by chen6704
public class LinkedList<T extends Comparable<T>> implements List<T> {
    private boolean isSorted;
    private Node<T> begin = null;
    private int trackSize = 0;

    public LinkedList(){
        begin = new Node<T>(null, null);
        isSorted = true;
    }

    public boolean add(T element){
        if (element == null){
            return false;
        } else {
            Node<T> newItem = new Node<T>(element, null);
            Node<T> ptr = begin.getNext();
            Node<T> trailer = begin;
            while (ptr != null){
                if (newItem.getData().compareTo(ptr.getData()) < 0){
                    isSorted = false;
                }
                trailer = ptr;
                ptr = ptr.getNext();
            }
            trailer.setNext(newItem);
            trackSize++;
            return true;
        }
    }

    public boolean add(int index, T element){
        if (element == null | index < 0 | index >= trackSize){
            return false;
        } else {
            Node<T> newItem = new Node<T>(element, null);
            Node<T> ptr = begin.getNext();
            Node<T> trailer = begin;
            int count = 0;
            while (count != index){
                count ++;
                trailer = ptr;
                ptr = ptr.getNext();
            }
            if (newItem.getData().compareTo(ptr.getData()) > 0){
                isSorted = false;
            }
            newItem.setNext(ptr);
            trailer.setNext(newItem);
            trackSize++;
            return true;
        }
    }

    public void clear(){
        begin = new Node<T>(null, null);
        isSorted = true;
        trackSize = 0;
    }

    public T get(int index){
        if (index >= trackSize | index < 0){
            return null;
        } else {
            Node<T> ptr = begin.getNext();
            Node<T> trailer = begin;
            int count = 0;
            while (count != index & ptr.getNext() != null){
                count ++;
                trailer = ptr;
                ptr = ptr.getNext();
            }
            return ptr.getData();
        }
    }

    public int indexOf(T element){
        if (element == null){
            return -1;
        } else {
            if (isSorted){                          // increase efficiency
                Node<T> trailer = begin;
                int count = 0;
                while (count != trackSize){
                    count ++;
                    trailer = trailer.getNext();
                    if (element.equals(trailer.getData())){
                        return count - 1;           // should not count the first node which is a dummy head
                    } else if (trailer.getData().compareTo(element) > 0){
                        return -1;
                    }
                }
                return -1;
            } else {
                Node<T> trailer = begin;
                int count = 0;
                while (count != trackSize){
                    count ++;
                    trailer = trailer.getNext();
                    if (element.equals(trailer.getData())){
                        return count - 1;           // should not count the first node which is a dummy head
                    }
                }
                return -1;
            }
        }
    }

    public boolean isEmpty(){
        if (begin.getNext() == null){       // if the second node is null, then the list is empty
            return true;
        } else {
            return false;
        }
    }

    public int size(){
        return trackSize;
    }

    public void sort(){
        if (!isSorted){
            Node<T> ptr;
            Node<T> trailer = begin.getNext();
            Node<T> min;
            T temp;
            while (trailer != null){
                ptr = trailer.getNext();
                min = trailer;
                while (ptr != null){
                    if (ptr.getData().compareTo(min.getData()) < 0){
                        min = ptr;
                    }
                    ptr = ptr.getNext();
                }
                if (trailer != min){
                    temp = trailer.getData();
                    trailer.setData(min.getData());
                    min.setData(temp);
                }
                trailer = trailer.getNext();
            }
            isSorted = true;
        }
    }

   public T remove(int index){
       if (index >= trackSize | index < 0){
           return null;
       } else {
           Node<T> ptr = begin.getNext();
           Node<T> trailer = begin;
           int count = 0;
           while (count != index){
               count ++;
               trailer = ptr;
               ptr = ptr.getNext();
           }
           trailer.setNext(ptr.getNext());
           trackSize --;

           // check if the list is sorted after removal
           if (trackSize <= 1){
               isSorted = true;
           }
           if (!isSorted) {
               Node<T> trailer2 = begin.getNext();
               Node<T> ptr2 = trailer2.getNext();
               isSorted = true;             // assume that the list is sorted.
               while (trailer2 != null && ptr2 != null) {
                   if (trailer2.getData().compareTo(ptr2.getData()) > 0) {
                       isSorted = false;
                   }
                   trailer2 = ptr2;
                   ptr2 = ptr2.getNext();
               }
           }

           return ptr.getData();
       }
   }

   public void equalTo(T element){
        if (element != null) {
            if (isSorted){                                                  // if the arrayList is sorted, reduce efficiency
                Node<T> ptr = begin.getNext();
                Node<T> trailer = begin;
                trackSize = 0;                                             // set size to 0 and increments when the element is matched
                while (ptr != null){
                    if (ptr.getData().equals(element)){
                        trailer = trailer.getNext();
                        trackSize ++;
                        if (!ptr.getData().equals(element)){        // check if the next index has the same element
                            break;                                             // if not, end the loop
                        }
                    } else {
                        trailer.setNext(ptr.getNext());                // skip the node that has a different element
                    }
                    ptr = ptr.getNext();
                }
            } else {
                Node<T> ptr = begin.getNext();
                Node<T> trailer = begin;
                while (ptr != null){
                    if (ptr.getData().equals(element)){
                        trailer = trailer.getNext();
                    } else {
                        trailer.setNext(ptr.getNext());               // skip the node that has a different element
                        trackSize --;
                    }
                    ptr = ptr.getNext();
                }
                isSorted = true;
            }
        }

   }

   public void reverse(){
        if (begin.getNext() != null || begin.getNext().getNext() != null){
            Node<T> trailer = begin.getNext();
            Node<T> ptr = trailer.getNext();
            while (ptr != null){
                trailer.setNext(ptr.getNext());
                ptr.setNext(begin.getNext());
                begin.setNext(ptr);
                ptr = trailer.getNext();
            }
       }

        // check if it is sorted
       if (trackSize <= 1){
           isSorted = true;
       }
       Node<T> trailer2 = begin.getNext();
       Node<T> ptr2 = trailer2.getNext();
       isSorted = true;             // assume that the list is sorted.
       while (ptr2 != null) {
           if (trailer2.getData().compareTo(ptr2.getData()) > 0) {
               isSorted = false;
           }
           trailer2 = ptr2;
           ptr2 = ptr2.getNext();
       }
   }

    public void merge(List<T> otherList){
        if (otherList != null) {
            LinkedList<T> other = (LinkedList<T>) otherList;
            sort();
            other.sort();
            Node<T> trailer1 = begin.getNext();                             // track the nodes in this LinkedList
            Node<T> ptr1 = trailer1.getNext();                              // track the nodes in this LinkedList
            Node<T> trailer2 = other.begin.getNext();                   // track the nodes in other LinkedList
            Node<T> ptr2 = trailer2.getNext();                              // track the nodes in other LinkedList
            while (ptr1 != null && ptr2 != null) {
                if (trailer1.getData().compareTo(trailer2.getData()) <= 0 && ptr1.getData().compareTo(trailer2.getData()) >= 0) {     // when the node/nodes in other LinkedList is/are merged between adjacent two nodes in this LinkedList
                    ptr2 = trailer2.getNext();
                    trailer1.setNext(trailer2);
                    trailer2.setNext(ptr1);
                    trailer1 = trailer2;
                    trailer2 = ptr2;
                } else {
                    if (ptr1.getNext() != null) {
                        ptr1 = ptr1.getNext();
                        trailer1 = trailer1.getNext();
                    } else {                                                    // this LinkedList is completely merged, but other LinkedList isn't
                        ptr1.setNext(ptr2);
                    }
                }
            }
            trackSize += other.trackSize;
        }
    }

    public boolean rotate(int n){
        if (n <= 0 | trackSize <= 1){
            return false;
        } else {
            Node<T> ptr = begin.getNext();
            Node<T> trailer = begin;
            int count = 0;
            while (count != (trackSize - n)){               // rotating n times means that shift (size-n) nodes to the right
                count ++;
                trailer = ptr;
                ptr = ptr.getNext();
            }
            Node <T> temp = ptr;
            while (ptr.getNext() != null){
                ptr = ptr.getNext();
            }
            trailer.setNext(null);
            ptr.setNext(begin.getNext());
            begin.setNext(temp);

            // check if it is sorted
            Node<T> trailer2 = begin.getNext();
            Node<T> ptr2 = trailer2.getNext();
            isSorted = true;             // assume that the list is sorted.
            while (ptr2 != null) {
                if (trailer2.getData().compareTo(ptr2.getData()) > 0) {
                    isSorted = false;
                }
                trailer2 = ptr2;
                ptr2 = ptr2.getNext();
            }

            return true;
        }
    }

    public  String toString(){
        String s = "";
        Node<T> ptr = begin.getNext();
        while (ptr != null){
            s += ptr.getData().toString() + "\n";
            ptr = ptr.getNext();
        }
        return s;
    }

    public boolean isSorted(){
        return isSorted;
    }

}

