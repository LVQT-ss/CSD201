// =========================================================
// Do NOT modify this file 
// =========================================================

class Node{
    private Phone info;
    Node next;
    
    // Default constructor (no parameter)
    Node () {}
    
    // Constructor for a typical node
    Node (Phone x, Node p) {
        this.info = x; // data stored inside the node
        this.next = p; // link to the next node
    }
    
    //Copy constructor
    Node (Phone x) {
        this(x,null);
    }
    
    public Phone getInfo() {
        return this.info;
    }
    
    public Node getNext() {
        return this.next;
    }
    
    public void setInfo(Phone inBike) {
        this.info = inBike;
    }
    
    public void setNext(Node n) {
        this.next = n;
    }
 }

