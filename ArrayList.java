// Written by chen6704
public class ArrayList<T extends Comparable<T>> implements List<T>{
    private boolean isSorted;
    private T[] arrayList;
    private int size = 0;

    public ArrayList(){
        arrayList = (T[]) new Comparable[2];
        isSorted = true;
    }

    // helper function:
    private void newArray(){                // twice the length of the original array if the arrayList is full
        T[] newArray = (T[]) new Comparable[2 * arrayList.length];
        for (int i = 0; i < arrayList.length; i ++){
            newArray[i] = arrayList[i];
        }
        arrayList = newArray;
    }

    public boolean add(T element){
        if (element == null){
            return false;
        } else {
            if (size != arrayList.length) {
                for (int i = 0; i < arrayList.length; i++) {
                    if (arrayList[i] == null) {
                        if (i - 1 >= 0 && element.compareTo(arrayList[i - 1]) < 0) {
                            isSorted = false;
                        }
                        arrayList[i] = element;
                        break;
                    }
                }
            } else {
                newArray();
                arrayList[arrayList.length / 2] = element;
                if (arrayList[arrayList.length / 2].compareTo(arrayList[arrayList.length / 2 - 1]) < 0){
                    isSorted = false;
                }
            }
            size ++;
            return true;
        }
    }

    public boolean add(int index, T element){
        if (element == null | index < 0 | index >= size){
            return false;
        } else {
            if (size + 1 <= arrayList.length){
                T[] temp = (T[]) new Comparable[arrayList.length + 1];
                for (int i = 0; i <= index; i++){
                    temp[i] = arrayList[i];
                }
                temp[index] = element;
                for (int i = index; i < arrayList.length; i++){
                    temp[i + 1] = arrayList[i];
                }
                arrayList = temp;
            } else {
                newArray();
                T[] temp = (T[]) new Comparable[arrayList.length + 1];
                for (int i = 0; i <= index; i++){
                    temp[i] = arrayList[i];
                }
                temp[index] = element;
                for (int i = index; i < arrayList.length; i++){
                    temp[i + 1] = arrayList[i];
                }
                arrayList = temp;
            }
            if (element.compareTo(arrayList[index + 1]) > 0) {
                isSorted = false;
            }
            size ++;
            return true;
        }
    }

    public void clear(){
        arrayList = (T[]) new Comparable[2];
        isSorted = true;
        size = 0;
    }

    public T get(int index){
        if (index < 0 | index >= arrayList.length){
            return null;
        } else {
            return arrayList[index];
        }
    }

    public int indexOf(T element){
        if (element == null){
            return -1;
        } else {
            if (isSorted){                                          // increase efficiency
                for (int i = 0; i < size; i++){
                    if (element.equals(arrayList[i])){
                        return i;
                    } else if (arrayList[i].compareTo(element) > 0){
                        return -1;
                    }
                }
                return -1;
            } else {
                for (int i = 0; i < size; i++){
                    if (element.equals(arrayList[i])){
                        return i;
                    }
                }
                return -1;
            }
        }
    }

    public boolean isEmpty(){
        for (int i = 0; i < arrayList.length; i++){
            if (arrayList[i] != null){
                return false;
            }
        }
        return true;
    }

    public int size(){
        return size;
    }

    public void sort(){
        if (!isSorted){
            int minIndex;
            T temp;
            for (int i = 0; i < size - 1; i++){
                minIndex = i;
                for (int j = i + 1; j < size; j++){
                    if (arrayList[j].compareTo(arrayList[minIndex]) < 0){
                        minIndex = j;
                    }
                }
                temp = arrayList[minIndex];
                arrayList[minIndex] = arrayList[i];
                arrayList[i] = temp;
            }
            isSorted = true;
        }
    }

    public T remove(int index){
        if (index >= size | index < 0){
            return null;
        } else {
            T[] temp = (T[]) new Comparable[arrayList.length -1];;
            T element = arrayList[index];           // store the element at given index used for return
            for (int i = 0; i < index; i++){
                temp[i] = arrayList[i];
            }
            for (int j = index + 1; j < arrayList.length; j ++){
                temp[j -1] = arrayList[j];
            }
            arrayList = temp;
            size --;

            // check if the list is sorted after removal
            if (size <= 1){
                isSorted = true;
            }
            if (!isSorted){
                isSorted = true;             // assume that the list is sorted
                for (int k = 0; k < size; k ++){
                    if (k - 1 >= 0 && arrayList[k].compareTo(arrayList[k - 1]) < 0){
                        isSorted = false;
                    }
                }
            }

            return element;
        }
    }

    public void equalTo(T element){
        if (element != null){
            int count = 0;
            if (isSorted){                                                          // if the arrayList is sorted, increase efficiency
                for (int i = 0; i < size; i++) {
                    if (arrayList[i].equals(element)) {
                        arrayList[count++] = arrayList[i];
                        if (!arrayList[i + 1].equals(element)) {            // check if the next element matches the given element
                            break;                                                    // if not, end the loop
                        }
                    } else {
                        arrayList[i] = null;
                    }
                }
                size = count;
            } else {
                for (int i = 0; i < size; i++) {
                    if (arrayList[i].equals(element)) {
                        arrayList[count++] = arrayList[i];
                    } else {
                        arrayList[i] = null;
                    }
                }
                size = count;
            }
            isSorted = true;
        }
    }

    public void reverse(){
        if (size > 1){
            T temp;
            for (int i = 0; i < size / 2; i ++){                    // swap the first element with last element, second with last second element, and so on
                temp = arrayList[i];
                arrayList[i] = arrayList[size - 1 - i];
                arrayList[size - 1 - i] = temp;
            }

            // check if the list is sorted
            isSorted = true;             // assume that the list is sorted
            for (int j = 0; j < size; j ++){
                if (j - 1 >= 0 && arrayList[j].compareTo(arrayList[j - 1]) < 0){
                    isSorted = false;
                }
            }
        }
    }

    public void merge(List<T> otherList){
        ArrayList<T> other = (ArrayList<T>) otherList;
        sort();
        other.sort();
        T[] newArray = (T[]) new Comparable[size + other.size];
        int index = 0;                                      // track the index of newArray
        int trackIndex1 = 0;                            // track the index of this arrayList
        int trackIndex2 = 0;                            // track the index of other arrayList
        while (trackIndex1 < size && trackIndex2 < other.size){
            if (arrayList[trackIndex1].compareTo(other.arrayList[trackIndex2]) <= 0){            // add the element in this list in the new list
                newArray[index++] = arrayList[trackIndex1++];
            } else {                                                                                                         // add the element in other list in the new list
                newArray[index++] = other.arrayList[trackIndex2++];
            }
        }
        while (trackIndex1 < size){                                                                              // other arrayList is completely added in newArray, but this arrayList isn't
            newArray[index++] = arrayList[trackIndex1++];                                            // add rest of the elements of this arrayList in newArray
        }
        while (trackIndex2 < size){                                                                              // this arrayList is completely added in newArray, but other arrayList isn't
            newArray[index++] = other.arrayList[trackIndex2++];                                   // add rest of the elements of other arrayList in newArray
        }
        arrayList = newArray;
        size += other.size;
    }

    /**
     * Rotate this list to the right by n positions. This rotation must be done IN PLACE. Any use of
     * intermediate data structures will yield your solution invalid. If
     * n is less than or equal to 0 OR the list length is less than or equal to 1, return false without rotating.
     * Returns true otherwise after completing the rotation. Updates isSorted accordingly.
     * ArrayList hint: try to think about how the number of rotations could be simplified down based
     * on the size of the array.
     * LinkedList hint: try to think about the linked list in a circular way when rotating.
     *
     * @param n number of rotations.
     * @return if the rotation was successful.
     */
    public boolean rotate(int n){
        if (n <= 0 | size <= 1){
            return false;
        } else {
            for (int i = 0; i < n; i ++){
                T temp = arrayList[size - 1];                               // store the last element
                for (int j = size - 1; j > 0; j --){                        // shift every element 1 index to the right
                    arrayList[j] = arrayList[j-1];
                }
                arrayList[0] = temp;                                        // override first element with the last element that was stored in temp
            }

            // check if the list is sorted
            isSorted = true;             // assume that the list is sorted
            for (int k = 0; k < size; k ++){
                if (k - 1 >= 0 && arrayList[k].compareTo(arrayList[k - 1]) < 0){
                    isSorted = false;
                }
            }

            return true;
        }
    }

    public String toString(){
        String s = "";
        for (int i = 0; i < size; i ++){
            s += arrayList[i].toString() + "\n";
        }
        return s;
    }

    public boolean isSorted(){
        return isSorted;
    }
}
