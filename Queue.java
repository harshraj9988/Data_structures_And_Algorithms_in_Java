package Data_structures_And_Algorithms_in_Java;

// Queue implemented using doubly linked list of java collections class

import java.util.LinkedList;

public class Queue <T> {

    private LinkedList<T> list = new LinkedList<T>();

    public Queue(){}                                //default constructor

    public Queue(T firstElement){                   //adds first element of the queue while initializing it
        offer(firstElement);
    }

    public int size(){                             //returns the size of the queue
        return list.size();
    }

    public boolean isEmpty(){                       //checks if the queue is empty or not
        return size() == 0;
    }

    public T peek(){                                //returns the first element of the queue
        if(isEmpty()) throw new RuntimeException("Empty Queue");
        return list.peekFirst();
    }

    public T poll(){                                //returns and remove the first element of the queue
        if(isEmpty()) throw new RuntimeException("Empty Queue");
        return list.removeFirst();
    }

    public void offer(T element){                   //adds an element to the last of the queue
        list.addLast(element);
    }

}