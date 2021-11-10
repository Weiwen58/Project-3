public class LinkedList<T extends Comparable<T>> implements List<T> {
    private boolean isSorted = true;
    private Node<T> begin = null;

    public LinkedList(){
        begin = new Node<T>(null, null);
    }

    public boolean add(T element){
        if (element == null){
            return false;
        } else {
            Node<T> newItem = new Node<T>(element, null);
            Node<T> ptr = begin.getNext();
            Node<T> trailer = begin;
            while (ptr != null){
                if (newItem.getData().toString().compareTo(ptr.getData().toString()) < 0){
                    isSorted = false;
                }
                trailer = ptr;
                ptr = ptr.getNext();
            }
            trailer.setNext(newItem);
            return true;
        }
    }

    public boolean add(int index, T element){
        if (element == null | index < 0){
            return false;
        } else {
            Node<T> newItem = new Node<T>(element, null);
            Node<T> trailer = begin.getNext();
            int count = 0;
            while (count != index){
                if (newItem.getData().toString().compareTo(trailer.getData().toString()) > 0){
                    isSorted = false;
                }
                count ++;
                trailer = trailer.getNext();
            }
            newItem.setNext(trailer);
            trailer = newItem;
            return true;
        }
    }

    public void clear(){
        begin = new Node<T>(null, null);
        isSorted = true;
    }

    public T get(int index){
        if (index < 0){
            return null;
        } else {
            Node<T> trailer = begin.getNext();
            int count = 0;
            while (count != index){
                count ++;
                trailer = trailer.getNext();
            }
            return trailer.getData();
        }
    }

    public int indexOf(T element){
        return 1;
    }

    public boolean isEmpty(){
        if (begin == null){
            return true;
        } else {
            return false;
        }
    }

    public int size(){
        return 1;
    }

    public void sort(){

    }

   public T remove(int index){
    return begin.getData();
   }

   public void equalTo(T element){

   }

   public void reverse(){

   }

    public void merge(List<T> otherList){

    }

    public boolean rotate(int n){
        return true;
    }

    public  String toString(){
        return "";
    }

    public boolean isSorted(){
        return isSorted();
    }

}

