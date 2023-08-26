import java.io.*;

public class MyList {
    Node head, tail;
    int size;

    // Default constructor
    MyList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    
    public boolean isEmpty() {
        return this.size == 0;
    }
    
    public void clear() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    
    void ftraverse(RandomAccessFile f) throws Exception {
        Node p = head;
        while(p != null) {
            f.writeBytes(p.getInfo() + " "); // write data in node p to the file f
            p = p.next;
        }
        
        f.writeBytes("\r\n");
    }
    
    /**
     * Do NOT modify this method
     * Load 3 lines of data from file: 
     *      line k (for owner), 
     *      line k+1 (for price), and
     *      line k+2 (for color)
     * 
     * @param k the k-th line where data is started to be loaded
     */
    void loadData(int k) {
        String [] a = Lib.readLineToStrArray("data.txt", k);
        double [] b = Lib.readLineToDoubleArray("data.txt", k+1);
        int [] c = Lib.readLineToIntArray("data.txt", k+2);
        
        int n = a.length;
        for(int i = 0; i < n; i++) 
            // insert the new Node(a[i], b[i], c[i]) into the list
            addFirst(a[i],b[i],c[i]);
    }
   
    /**
     * Do NOT modify this method
     * This is a helper method for Questions 1.1; 1.2; 1.3; and 1.4
     * @throws Exception 
     */
    void helpFunction(int questionNo) throws Exception {
        clear();
        loadData(questionNo * 4 - 3);
        
        String fname = "f" + Integer.toString(questionNo) + ".txt";
        File g123 = new File(fname);
        if(g123.exists()) g123.delete();
        RandomAccessFile  f = new RandomAccessFile(fname, "rw"); 
        ftraverse(f);
        
        if (questionNo == 2){
            updateNode();
            ftraverse(f);
        }
        
        if (questionNo == 3){
            removeLast();
            ftraverse(f);
        }
          
        if (questionNo == 4){
            reverse();
            ftraverse(f);
        }
        
        f.close();
    }
   
    void f1() throws Exception {
        helpFunction(1);
    }
    
    void f2() throws Exception {
        helpFunction(2);
    }
    
    void f3() throws Exception {
        helpFunction(3);    
    }
    
    void f4() throws Exception {
        helpFunction(4);
    }
    /**
     * Luy y: 1. SV KHONG su dung tieng Viet co dau trong bai lam de tranh Error khi run chuong trinh.
     *        2. Neu khong tuan thu se nhan diem 0 (khong).
     * Question 1.1: implement the 'addFirst' method that inserts
     * a new Node into the list's head if the price is equal or less than 1100.0(price<=1100)
     * Note: increase the list's size by 1 if you insert successfully
     * The output of this method will be written into the file 'f1.txt'. 
     * Therefore, you should open this file to inspect your code output.
     * Example: with the data given in the file 'data.txt', 
     *          then the content of 'f1.txt' after insertion should be  
     *          (S6, 1000.0, 60) (S5, 900.0, 50) (S4, 800.0, 40) (S3, 700.0, 30) (S2, 600.0, 20) (S1, 500.0, 10)
     * @param xName the name of the input Phone
     * @param xPrice the price of the input Phone
     * @param xColor the color of the input Phone
     */
    void addFirst(String xName, double xPrice, int xColor) {
        //------------------------------------------------------------------------------------
        //------ Start your code here---------------------------------------------------------
        //if(xPrice<=1100){
        //    
        //}
        // remember to increase list's size by 1
        if (xPrice <= 1100) {
            if (size == 0) {
                head = tail = new Node(new Phone(xName, xPrice, xColor));
            } else {
                head = new Node(new Phone(xName, xPrice, xColor), head);
            }
            size++;
        }
        //------ End your code here-----------------------------------------------------------
        //------------------------------------------------------------------------------------
    }
    
    /**
     * Question 1.2: visit every node in the list and do following tasks
     *      1. if Phone's price < 700 then update color = color + 5
     *      2. if 700 <= Phone's price <= 1500 then update color = color + 10
     *      3. if Phone's price > 1500 then update color = color + 15
     * The output of this method will be written into the file 'f2.txt'. 
     * Therefore you should open this file to see/test your code output.
     * Example: with in data given, the content of 'f2.txt' should be:
     *        (iPhone8, 800.0, 5) (iPhone7, 700.0, 3) (iPhone6, 600.0, 8) (iPhone5, 500.0, 22) 
     *        (iPhone8, 800.0, 15) (iPhone7, 700.0, 13) (iPhone6, 600.0, 13) (iPhone5, 500.0, 27)    
     * Note: You should use methods getPrice(), getColor(), and setColor(int) 
     * in the class Phone rather than directly accessing the private attributes 'color', and 'price'
     */
    void updateNode(){
        //------ Start your code here---------------------------------------------------------
                Node current = head;
        while (current != null) {
            double price = current.getInfo().getPrice();
            int color = current.getInfo().getColor();

            if (price < 700) {
                current.getInfo().setColor(color + 5);
            } else if (price >= 700 && price <= 1500) {
                current.getInfo().setColor(color + 10);
            } else {
                current.getInfo().setColor(color + 15);
            }

            current = current.next;
        }
        //------ End your code here-----------------------------------------------------------
    }
    
    /**
    * Question 1.3: Remove the last node of the list
    * The output of this method will be written into the file 'f3.txt'. 
    * Therefore you should open this file to see/test your code output.
    * Example: with the data given, the linked list before and after removeLast is:
    * (Samsung_S8, 850.0, 2) (Samsung_S7, 800.0, -7) (Samsung_S6, 750.0, 9) (Samsung_S5, 700.0, 4) (Samsung_S4, 650.0, 5) (Samsung_S3, 600.0, 3) (Samsung_S2, 550.0, 8) (Samsung_S1, 500.0, 22) 
    * (Samsung_S8, 850.0, 2) (Samsung_S7, 800.0, -7) (Samsung_S6, 750.0, 9) (Samsung_S5, 700.0, 4) (Samsung_S4, 650.0, 5) (Samsung_S3, 600.0, 3) (Samsung_S2, 550.0, 8) 
    */   
    void removeLast(){
        /**
         * 1. if you have your own idea for this Question, just follow it
         * 2. if you do NOT have any idea, you may follow the hint
         * You should code ONLY in Case 1 or Case 2 below
         */
        
        // Case 1. If you have an idea, then just follow it
        //------ Start your code here-------------------------------------------------------
        if (isEmpty()) {
            return;
        }

        if (size == 1) {
            head = tail = null;
            size = 0;
            return;
        }

        Node current = head;
        while (current.next != tail) {
            current = current.next;
        }

        current.next = null;
        tail = current;
        size--;
        //------ End your code here---------------------------------------------------------

        
        // Case 2. if you do NOT have any idea, you may follow the hint below
        // uncomment the following block code and 
        // fill in the '...' with your appropriate code
        
//        //------ Start your code here-------------------------------------------------------
// 
//        // The list is empty
//        if (isEmpty())
//            return ;
//        
//        // The list has only one node
//        if (this.size == 1) {
//            head = tail = ...;
//            this.size...;
//            return ;
//        }
//        
//        // The number of nodes is >= 2
//        // Find the second last node (the node before tail node)
//        Node current = head;
//        //Loop through the list untill reaching the second last node 
//        while(current.next != ...) {  
//            current = current....;
//        }
//        
//        // The second last node becomes new tail of the list  
//        tail = ...;  
//        tail.next = ...;  
//        
//        // Descrease list's size by 1
//        this.size...;
//                
//        //------ End your code here---------------------------------------------------------
        
    } // end removeLast()
    
    /**
    * Question 1.4: Reverse the order of all nodes in the linked list 
    * The output of this method will be written into the file 'f4.txt'. 
    * Therefore you should open this file to see/test your code output.
    * Example: with data given in the file "data.txt",
    *           the linked list before and after reversing is: 
    *   (SS_Note4, 400.0, 9) (SS_Note3, 300.0, 12) (SS_Note2, 200.0, 6) (SS_Note1, 100.0, 2) 
    *   (SS_Note1, 100.0, 2) (SS_Note2, 200.0, 6) (SS_Note3, 300.0, 12) (SS_Note4, 400.0, 9)   
    */
    void reverse(){
        
        //------ Start your code here-------------------------------------------------------
                Node prev = null;
        Node current = head;
        Node next;

        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        tail = head;
        head = prev;
    
        //------ End your code here---------------------------------------------------------
    }
    
} // end MyList

