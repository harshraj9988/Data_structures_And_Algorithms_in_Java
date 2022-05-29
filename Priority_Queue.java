package Data_structures_And_Algorithms_in_Java;

// priority queue using list based heap and map

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Priority_Queue<T extends Comparable<T>> {

    // number of elements inside the head
    private int heapSize = 0;

    // internal capacity of the heap
    private int heapCapacity = 0;

    // a dynamic list to keep track of the elements inside the head
    private List<T> heap = null;

    // map to keep track of indices of an element in the heap
    private Map<T, TreeSet<Integer>> map = new HashMap<>();

    // construct an initially empty priority queue
    public Priority_Queue() {
        this(1);
    }

    // construct a priority queue with an initial capacity
    public Priority_Queue(int sz) {
        heap = new ArrayList<>(sz);
    }

    // construct a priority queue using heapify in O(n) time
    public Priority_Queue(T[] elements){
        heapSize = heapCapacity = elements.length;
        heap = new ArrayList<T>( heapCapacity );

        //place all elements in head
        for(int i=0; i< heapSize; i++){
            mapAdd(elements[i], i);
            heap.add(elements[i]);
        }

        //Heapify process in O(n)
        for(int i = Math.max(0 , (heapSize/2)-1);i>=0; i--){
            sink(i);
        }
    }

    //construct a priority queue in O(nlog(n))
    public Priority_Queue(Collection<T> elements){
        this(elements.size());
        for(T elems : elements) add(elems);
    }

    //checks if the priority queue is empty or not
    public boolean isEmpty(){
        return heapSize == 0;
    }

    //clear everything inside the heap O(n)
    public void clear(){
        for (int i = 0; i < heapCapacity; i++) 
            heap.set(i, null);
        heapSize = 0;
        map.clear();
    }

    //returns the size of the heap
    public int size(){
        return heapSize;
    }

    //return the value with the lowest priority in the priority queue
    // return null if the priority queue is empty
    public T peek(){
        if(isEmpty()) return null;
        return heap.get(0);
    }

    //removes the root of the heap 0(nlog(n))
    public T poll(){
        return removeAt(0);
    }

    //check if the elements is present in the head O(1)
    public boolean contains(T element){
        if(element == null ) return false;
        return map.containsKey(element);
    }

    //adds an element to the priority queue O(log(n))
    public void add(T element){
        if(element == null) throw new IllegalArgumentException();

        if(heapSize < heapCapacity){
            heap.set(heapSize, element);
        }
        else{
            heap.add(element);
            heapCapacity++;
        }

        mapAdd(element, heapSize);

        swim(heapSize);
        heapSize++;
    }

    //test if the value of node i <= node j
    private boolean less(int i, int j){
        T node1 = heap.get(i);
        T node2 = heap.get(2);
        return node1.compareTo(node2) <= 0;
    }

    //bottom up node swim O(log(n))
    private void swim(int k){

        //index of the next parent node with respect to k
        int parent = (k-1)/2;

        //keep swimming while we have not reached the roo and we are less than our parent
        while(k>0 && less(k, parent)){
                
            //exchange k with parent
            swap( k , parent );
            k = parent;

            //index of the next parent node with respect to k
            parent = (k-1)/2;
        }
    }

    //sind the node from top to down O(log(n))
    private void sink(int k){
        while( true ){

            int left = 2 * k + 1;       //left node
            int right = 2 * k + 2;      //right node
            int smallest = left;        //assuming left node is the smallest node of the two children

            // find which is smaller, left or right
            if( right < heapSize && less(right , left))
                smallest = right;

            //stop if we cannot sink k anymore
            if( left >= heapSize || less( k, smallest)) break;

            //move down the tree following the smallest node
            swap(smallest , k);
            k = smallest;
        }
    }

    //swap two nodes O(1)
    private void swap(int i, int j){
        T element_i = heap.get(i);
        T element_j = heap.get(j);
        
        heap.set(i, element_j);
        heap.set(j, element_i);

        mapSwap(element_i, element_j, i, j);
    }

    //removes a particular element in the heap O(log(n))
    public boolean remove(T element){
        if(element ==  null) return false;

        Integer index = mapGet(element);
        if (index != null) removeAt(index);
        return index != null;
    }

    //removes a node at a particular index O(log(n))
    private T removeAt(int i){
        if(isEmpty()) return null;
        
        heapSize--;
        T removed = heap.get(i);
        swap(i, heapSize);

        //delete the value
        heap.set(heapSize, null);

        //removed the last element
        if(i == heapSize) return removed;
        mapRemove(removed, heapSize);

        T elem = heap.get(i);

        //try sinking the element
        sink(i);

        //if sinking didn't work, try swimming
        if(heap.get(i).equals(elem))
            swim(i);
        
        return removed;
    }

    //recursively checks if this heap is a min heap
    public boolean isMinHeap(int k){
        //if we are outside the bounds of heap, return true
        if(k>=heapSize) return true;

        int left = 2*k+1;
        int right = 2*k+2;

        //make sure that the current node is less than both of its children, if they exist
        if( left < heapSize && !less(k,left)) return false;
        if( right < heapSize && !less(k,right)) return false;

        //recurse on both childs to make sure they are also valid heaps;
        return isMinHeap(left) && isMinHeap(right);
    }

    //add a node and its index to the map
    private void mapAdd(T value, int index){
        TreeSet<Integer> set = map.get(value);

        //new value being inserted into the map
        if(set == null){
            set = new TreeSet<>();
            set.add(index);
            map.put(value, set);

            //value already exist in map
        }else set.add(index);
    }

    //remove the index at a given value O(log(n))
    private void mapRemove(T value, int index){
        TreeSet<Integer> set = map.get(value);
        set.remove(index);
        if(set.size() == 0 ) map.remove(value);
    }

    //Extract an index position for the given value
    private Integer mapGet(T value){
        TreeSet <Integer> set = map.get(value);
        if( set != null) return set.last();
        return null;
    }

    //Exchange the index of two nodes internally within the map
    private void mapSwap(T val1, T val2, int val1index, int val2index){
        Set<Integer> set1 = map.get(val1);
        Set<Integer> set2 = map.get(val2);

        set1.remove(val1index);
        set2.remove(val2index);

        set1.add(val2index);
        set2.add(val1index);
    }

    @Override public String toString(){
        return heap.toString();
    }
}