package Data_structures_And_Algorithms_in_Java;
// stack implemented using the LinkedList provided in java.util package
import java.util.*;
public class Stack <T> {
    private LinkedList list= new LinkedList<>();

    public Stack () {};                             //constructor

    public Stack (T element) {                         //constructor with argument
        push(element);
    }

    public int size(){                                 // returns the size of the stack
        return list.size();
    }

    public boolean isEmpty(){                           // checks if the stack is empty
        return size()==0;
    }

    public void push(T element){                        // add an element to the top of the stack
        list.addLast(element);
    }

    public T pop(){
        if(isEmpty()) throw new EmptyStackException();      // remove an element from the top of the stack
        return (T) list.getLast();
    }

    public T peek(){                                        // returns the element at the top of the stack
        if(isEmpty()) throw new EmptyStackException();      
        return (T) list.getLast();
    }

    public T search(T element){                            // returns a boolean value while searching for the element
        if(isEmpty()) throw new EmptyStackException();
        if (list.contains(element)) return element;
        else return null;
    }

    public String asString(){                            // returns the stack as a string
        if(isEmpty()) throw new EmptyStackException();
        return list.toString();
    }

        public void printVertically(){                    // prints the stack vertically
        System.out.println();       
        for (int i = size()-1; i >=0 ; i--) {
            System.out.println(list.get(i));
        }
        System.out.println();
    }

}
