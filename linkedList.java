package Data_structures_And_Algorithms_in_Java;

public class linkedList <T> {
    private int size=0;
    private Node head=null;
    private Node tail=null;

    private class Node{                     //Internal Node class
        T data;
        Node next;
        public Node(T data){
            this.data=data;
            this.next=null; 
        }
    }


    public void addFirst(T value){          //Adds element to the begining of the linked list
        Node newNode= new Node(value);
        if(head==null){
            head=newNode;
            tail=newNode;
        }
        else{
            newNode.next=head;
            head=newNode;
        }
        size++;
    }

    public void addLast(T value){           //Adds element to the end of the linked list
        Node newNode=new Node(value);
        if(head==null){
            head=newNode;
            tail=newNode;
        }
        else{
            tail.next=newNode;
            tail=newNode;
        }
        size++;
    }

    public void add(T value){                 //Adds element to the end of the linked list
        addLast(value);
    }

    public String asString(){                  // Returns the linked list as a string
        if(head==null) throw new IllegalAccessError("List is empty");

        String string="[ ";
        Node currNode= head;
        while(currNode!=null){
            string=string+currNode.data+" -> ";
            currNode=currNode.next;
        }
        string=string+"null ]";
        return string;
    }

    public void printList(){                    //prints the linked list
        if(head==null) throw new IllegalAccessError("List is empty");
        
        Node currNode=head;
        while(currNode!=null){
            System.out.print(currNode.data+" -> ");
            currNode=currNode.next;
        }
        System.out.println("null");
    }

    public void remove(T value){                //remove a specific element
        if(head==null) throw new IllegalAccessError("List is empty");

        Node trav1=head;
        Node trav2=head;
        
        while(trav2.data!=value){
            trav2=trav2.next;
            if(trav2==null){ return; }
            if(trav2.data!=value) trav1=trav1.next;
        }

        if(trav2==head) head=trav2.next; 
        else if(trav2==tail){ trav1.next=null; tail=trav1; }
        else trav1.next=trav2.next;   
        size--;    
    }

    public void removeFirst(){                  //remove the first element
        if(head==null) throw new IllegalAccessError("List is empty");
        remove(head.data);
        size--;
    }

    public void removeLast(){                   //remove the last element
        if(tail==null) throw new IllegalAccessError("List is empty");
        remove(tail.data);
        size--;

    }

    public void addAfter(T after, T add){       //adds after a specific element
        Node newNode=new Node(add);
        Node trav1=head.next;
        Node trav2=head;
        if(after==tail.data) {  tail.next=newNode; tail=newNode; return;   }
        while(trav2.data!=after){
            trav2=trav2.next;
            trav1=trav1.next;
        }
        trav2.next=newNode;
        newNode.next=trav1;

        size++;
    }

    public void addBefore(T before, T add){         // adds before a specific element
        Node newNode=new Node(add);
        Node trav1=head.next;
        Node trav2=head;
        if(before==head.data) {  newNode.next=head; head=newNode; return;   }
        while(trav1.data!=before){
            trav2=trav2.next;
            trav1=trav1.next;
        }
        trav2.next=newNode;
        newNode.next=trav1;

        size++;
    }

    public T getFirst(){                            //returns the first element
        if(head==null) throw new IllegalAccessError("List is empty");
        return head.data;
    }

    public T getLast(){                             //return the last element
        if(tail==null)throw new IllegalAccessError("List is empty");
        return tail.data;
    }

    public T getAfter(T afterThis){                 //return the element after the specific element
        
        if(tail.data==afterThis)throw new  IllegalArgumentException("There's only void after this");

        Node trav=head;
        while(trav.data!=afterThis){
            if(trav==null) throw new IllegalArgumentException(afterThis+" is not present.");
            trav=trav.next;
        }
        return trav.next.data;

    }

    public T getBefore(T beforeThis){           //return the element before the specific element
        if(head.data==beforeThis)throw new IllegalArgumentException("There's only void before this");
        Node trav=head;
        while(trav.next.data!=beforeThis){
            if(trav.next==null) throw new IllegalArgumentException(beforeThis+" is not present.");
            trav=trav.next;
        }
        return trav.data;
    }


    public boolean search(T value){             //search for the element and returns a bool value
        Node trav=head;
        while(trav.data!=value){
            trav=trav.next;
        }
        if(trav==null) return false;
        else return true;
    }

    public boolean isEmpty(){                   //checks if the linked list is empty
        return head==null;
    }

    public void clear(){                        //clears out the linked list
        while(head!=null){
            head=head.next;
        }
        size=0;
        
    }

    public int size(){                          //returns the size of the linked list
        return size;    
    }

    public T atIndex(int index){               //return the element at the specified index
        Node trav=head;
        for (int i = 1; i <= index; i++) {
            trav=trav.next;
            if(trav==null)throw new IllegalArgumentException("index "+index+": out of bound");
        }
        return trav.data;
    }
}