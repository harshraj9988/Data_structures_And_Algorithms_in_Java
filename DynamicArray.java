public class DynamicArray<T> {
    private T[] array;
    private int length = 0;       // array capacity being used.
    private int capacity = 0;     // total capacity of array.

    public DynamicArray() {             //initializing the array with 8 units unless the user defines manually.
        this(1);
    }

    public DynamicArray(int capacity) {         //initializing the array with a custom size
        if (capacity <= 0) throw new IllegalArgumentException("Invalid Capacity: " + capacity);
        this.capacity = capacity;
        array = (T[]) new Object[capacity];
    }

    public T get(int index) {                                //returns the value at the given index
        return array[index];
    }

    public void set(int index, T value) {                    //change/add the value at the given index
        if (index < 0 || index > (length - 1)) throw new IllegalArgumentException("Invalid index:" + index);
        array[index] = value;

    }

    public int size(){                                            //size of the array
        return length;
    }

    public int getCapacity(){                                    //actual size of array
        return capacity;
    }

    public boolean isEmpty(){                                   //check for the array being empty
        return size()==0;
    }

    public boolean contains(T value){                           //check if the element is present or not
        return indexOf(value) != -1;
    }

    public int indexOf(T value){                                 //returns the index of the element
        for (int i = 0; i < length; i++) {
            if(array[i]==value) return i;
        }
        return -1;
    }

    public void clear(){                                          //delete all the elements
        for (int i = 0; i < capacity; i++) {
            array[i]=null;
        }
        length=0;
    }

    public void add(T value){                                     //appends a value at end of the array
        addAt(length,value);
    }


    public void addAt(int index, T value){                        //adds an element to a particular index
        if(length<capacity) {
            for (int i = length; i > index; i--) {
                array[i]=array[i-1];
            }
            array[index]=value;
            length++;
        }
        else{
            capacity=2*capacity;
            T[] new_array=(T[])new Object[capacity];
            for (int i = 0; i < length; i++) {
                new_array[i]=array[i];
            }
            array=new_array;
            for (int i = length; i > index; i--) {
                array[i]=array[i-1];
            }
            array[index]=value;
            length++;
        }
    }


    public void remove(T value){                            //removes an element
        int index=indexOf(value);
        if(index==-1){
            System.out.println(value+" not present!");
        }
        else{
            removeAt(index);
        }
    }

    public void removeAt(int index){                    //removes an element from the given index
        if(index<0 || index>length-1) throw new IllegalArgumentException("Invalid index: "+index);
        for (int i = index; i < length-1 ; i++) {
            array[i]=array[i+1];
        }
        array[length-1]=null;
        length--;
    }

    public String asString(){                           //returns the array as a string
        String string="[";
        for (int i = 0; i < length-1; i++) {
            string=string+array[i]+", ";
        }
        string =string+array[length-1]+"]";
        return string;
    }



}