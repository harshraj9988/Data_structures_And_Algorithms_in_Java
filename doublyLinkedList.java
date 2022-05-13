public class doublyLinkedList <T>{
    private int size=0;
    private Node <T> head=null;
    private Node <T> tail=null;

    private class Node<T>{                                  //internal node class
        T data;
        Node<T> next, prev;
        public Node(T data, Node<T> prev, Node<T> next){
            this.data=data;
            this.next=next;
            this.prev=prev;
        }
    }

    public void clear(){                                    // method to clear the doubly linked list
        Node<T> trav=head;
        while(trav!=null){
            node<T> next=trav.next;
            trav.prev=trav.next=null;
            trav.data=null;
            trav=next;
        }
        head=tail=trav=null;
        size=0;
    }

    public int size(){                                        // returns the current size of the doubly linked list
        return size;
    }

    public boolean isEmpty(){                                  // check if the doubly linked list is empty of not
        return size()==0;
    }

    public void add(T item){                                // adds a node to the last
        addLast(item);
    }

    public addFirst(T item){                                 //adds a node to beginning
        if(isEmpty()){  
            head = new Node(item, null, null);
            tail=head;
        }
        else{
            head.prev=new Node(item, null , head);
            head=head.prev;
        }
        size++;
    }

    public addLast(T item){                                 // adds a node to the last
        if(isEmpty()){
            head = new Node(item, null, null);
            tail=head;
        }else{
            tail.next=new Node(item,tail,null);
            tail=tail.next;
        }
        size++;
    }

    public T peekFirst(){                                   //returns the first data in the doubly linked list
        if(isEmpty()) throw new RuntimeException("Empty List");
        return head.data;
    }

    public peekLast(){                                      //returns the last data in the doubly linked list
        if(isEmpty()) throw new RuntimeException("Empty List");
        return tail.data;
    }

    public T removeFirst(){                                 // removes the first element
        if(isEmpty()) throw new RuntimeException("Empty List");

        T temp=head.data;
        head=head.next;
        size--;

        if(isEmpty()) tail=null;

        else head.prev=null;

        return temp;
    }

    public T removeLast(){                                  // removes the last element
        if(isEmpty()) throw new RuntimeException("Empty List");

        T temp=tail.data;
        tail=tail.prev;
        size--;

        if(isEmpty()) head=null;
        else tail.next=null;

        return temp;
    }

    private T remove(Node <T> node){                    // a private method for maintaining the list internally
        if(node.prev==null) return removeFirst();
        if(node.next==null) return removeLast();

        node.next.prev=node.prev;
        node.prev.next=node.next;

        T temp=node.data;

        node.data=null;
        node=node.prev=node.next=null;

        size--;

        return temp;
    }

    public T removeAt(int index){                       // removes the element of a particular index
        if(index <0 || index>= size) throw new IllegalArgumentException();

        int i;
        Node<T> trav;

        if(index<Size/2){
            for(i=0,trav=head;i!=index;i++)
            trav=trav.next;
        }
        else{
            for(i=size-i,trav=tail;i!=index;i--)
            trav=trav.prev;
        }

        return remove(trav);
    }

    public boolean remove(Object obj){          // remove a particular element
        Node<T> trav = head;
        if(obj==null){
            for(trav=head;trav!=null;trav=trav.next){
                if(trav.data==null){
                    remove(trav);
                    return true;
                }
            }
        }else{
            for(trav=head;trav!=null;trav=trav.next){
                if(obj.equals(trav.data)){
                    remove(trav);
                    return true;
                }
            }
        }
        return false;
    }

    public int indexOf(Object obj){                 // returns the index of a particular element
        int index=0;
        Node<T> trav=head;

        if(obj==null){
            for(trav=head;trav!=null;trav=trav.next,index++){
                if(trav.data==null){
                    remove(trav);
                    return index;
                }
            }
        }
        else{
            for(trav=head;trav!=null;trav=trav.next,index++){
                if(obj.equals(trav.data)){
                    remove(trav);
                    return index;
                }
            }
        }
        return -1;
    }  
    
    public boolean contains(Object obj){                // checks if the particular element is present 
        return indexOf(obj)!=-1;
    }

    public String asString(){                           // prints the doubly linked list as a string;
        String str="[";
        Node <T> trav=head;
        while(trav.next!=null){
            str+=trav.data+", ";
            trav=trav.next;
        }
        str+=trav.data+"]";
        return str;
    }
}
