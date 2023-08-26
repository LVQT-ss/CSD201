import java.io.*;

public class BSTree {
    Node root;
    
    // Default constructor
    BSTree() {
        this.root = null;
    }
    
    public boolean isEmpty() {
        return this.root == null;
    }
    
    public void clear() {
        this.root = null;
    }
    
    public void visit(Node p) {
        System.out.print("p.info: ");
        if(p != null) 
            System.out.println(p.getInfo() + " ");
    }
    
    public void fvisit(Node p, RandomAccessFile f) throws Exception {
        if(p != null) 
            f.writeBytes(p.getInfo() + " ");
    }
    
    public void breadth(Node p, RandomAccessFile f) throws Exception {
        if(p == null) 
            return;
        Queue q = new Queue();
        q.enqueue(p);
        Node r;
        while(!q.isEmpty()) {
            r = q.dequeue();
            fvisit(r,f);
            
            if(r.left != null) 
                q.enqueue(r.left);
            
            if(r.right != null) 
                q.enqueue(r.right);
        }
    }
    
    public void preOrder(Node p, RandomAccessFile f) throws Exception {
        
        if(p == null) 
            return;
        
        fvisit(p,f);
        preOrder(p.left,f);
        preOrder(p.right,f);
    }
    
    void inOrder(Node p, RandomAccessFile f) throws Exception {
        if(p == null) 
            return;
        
        inOrder(p.left,f);
        fvisit(p,f);
        inOrder(p.right,f);
    }
    
    void postOrder(Node p, RandomAccessFile f) throws Exception {
        if(p == null) 
            return;
        
        postOrder(p.left,f);
        postOrder(p.right,f);
        fvisit(p,f);
     }

    /**
     * Do NOT modify this method
     * Load 3 lines of data from file: 
     *      line k (for owner), and
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
        for(int i = 0; i < n;i ++) 
            insert(a[i],b[i],c[i]); // insert the new Node(a[i], b[i], c[i]) into the BST
    }

    void helpFunction(int questionNo) throws Exception {
        clear();
        loadData(4*questionNo - 3);
        
        String fname = "f" + Integer.toString(questionNo) + ".txt";
        File g123 = new File(fname);
        if(g123.exists()) g123.delete();
        RandomAccessFile  f = new RandomAccessFile(fname, "rw"); 
        //breadth(root,f);
        postOrder(root,f);
        
        f.writeBytes("\r\n");
        
        if (questionNo == 1)
            postOrder(root,f);
        
        if (questionNo == 2){
            bfs_WithConstraint(this.root, f);
        }
        
        if (questionNo == 3){
            addOneNode();
            postOrder(root,f);

        }
          
        if (questionNo == 4){
            updateTree();
            postOrder(root,f);

        }
        
        f.writeBytes("\r\n");
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
    * Question 2.1: use Phone’s name as the key attribute when building a BST.
    *               implement the 'insert' method that inserts a new Node into 
    *               the BST if the price is < 1000
    * The output of this method will be written into the file 'f1.txt'. 
    * Therefore you should open this file to see/test your code output.
    * Example: with the content given in the file 'data.txt', 
    *          the content of 'f1.txt' after insertion should be
    *          (S4,900.0,50) (S3,700.0,30) (S8,800.0,40) (S5,600.0,20) (S1,500.0,10) 
    *          (S4,900.0,50) (S3,700.0,30) (S8,800.0,40) (S5,600.0,20) (S1,500.0,10)  
    * @param xName the name of the input Phone
    * @param xPrice the price of the input Phone
    * @param xColor the color of the input Phone
    */
    
    /**
     * Hint: 
     * 1. Use the method compareTo() in Java to lexicographically compare two strings. This method returns an int value (0, positive, or negative). For example:
     *      "hello".compareTo("hello") // returns 0
     *      "hello".compareTo("Hello") // returns 32
     *      "Hello".compareTo("hello") // returns -32
     * 2. You may implement an auxiliary method (e.g., insertRec(...)) to 
     * recursively insert a new node into the tree. 
     * Then the method insert() will call insertRec() like this:
     *      void insert(String xName, int xPrice, int xColor) {
     *              if (... < 1000.0)
     *                  this.root = insertRec(this.root, ...);
     *      }
     * 
     * 3. The hint for method insertRec() is given below. If you want to use it, 
     * just uncomment the block and fill in the '...' with your code 
     */
    
    void insert(String xName, double xPrice, int xColor) {
        //------ Start your code here-------------------------------------------------------
        //if(xPrice<1000){
            
        //}
                if (xPrice < 1000) {
            root = insertRec(root, new Phone(xName, xPrice, xColor));
        }
            
        //------ End your code here---------------------------------------------------------
    }
        private Node insertRec(Node root, Phone data) {
        if (root == null) { 
            return new Node(data);
        } else if (data.getName().compareTo(root.getInfo().getName()) < 0) {
            root.setLeft(insertRec(root.getLeft(), data));
        } else if (data.getName().compareTo(root.getInfo().getName()) > 0) {
            root.setRight(insertRec(root.getRight(), data));
        } else {
            // Duplicate keys are not allowed, you can decide how to handle them.
        }
        
        return root;
    }
//    
//    /**
//     * A recursive function to insert a new node with data (e) into BST 
//     * @param root : the root of the current sub-tree
//     * @param e: data field
//     * @return root node of the tree after insertion
//     */
//    private Node insertRec(Node root, Phone data){
//        /* If the tree is empty, then return a new node */
//        if (root == null){ 
//            return ...;
//        }
//        /**
//         * If the string of the data being inserted is less than the string of the current root node,
//         * then traverse to the left node of the current root, 
//         * and set the current left node to whatever gets returned from the insert method
//         */
//        else if (data.getName().compareTo(...) < 0 )
//        {
//              root... = insertRec(...,...);
//        }
//        /**
//         * If the value of the data being inserted is greater than the value of the current root node,
//         * then traverse to the right node of the current root, 
//         * and set the current right node to whatever gets returned from the insert method 
//         */
//        else if (data.getName().compareTo(...) > 0)
//        {
//              root... = insertRec(...,...);
//        }
//        else
//        {
//            // This is empty to explicitly state that we do NOT 
//            // allow insert duplicate keys into the tree.
//            
//        }
//        
//        /* return the (unchanged) node pointer */
//        return ...;
//    }
    
    //        //------ End your code here---------------------------------------------------------

   
    /**
     * Question 2.2: Perform breadth-first-search on the BST, but ONLY visit nodes that Phone's name contains "iPhone".
     * Hint: This method is similar to the method 'breadth' (provided in this class already). You should create 
 a new method which body is similar to 'breadth' for doing BFS but considering only nodes that Phone's name contains "iPhone".
 The output f2() will be written into the file "f2.txt". 
 Therefore you should open this file to see/test your code output.
 Example: With the data provided in "data.txt", the content of 'f2.txt' after running this method is 
                (BPhone8,800.0,5) (BPhone7,700.0,3) (iPhone6,600.0,8) (iPhone5,500.0,22) 
                (iPhone5,500.0,22) (iPhone6,600.0,8) 
     */
    private void bfs_WithConstraint(Node p, RandomAccessFile f) throws Exception {
        //------ Start your code here------------------------------------------------------------
                if (p == null) {
            return;
        }
        
        Queue q = new Queue();
        q.enqueue(p);
        
        while (!q.isEmpty()) {
            Node current = q.dequeue();
            if (current.getInfo().getName().contains("iPhone")) {
                fvisit(current, f);
            }

            if (current.getLeft() != null) {
                q.enqueue(current.getLeft());
            }
            if (current.getRight() != null) {
                q.enqueue(current.getRight());
            }
        }
        //------ End your code here--------------------------------------------------------------
    }
    
    /**
     * Question 2.3: Implement the method addOneNode() that inserts into the current tree a new Phone which  
 name = "VPhone", price = 800, color = k, where k is total number of nodes in the tree before insertion 
 Hint:  
      (1) Implement a method to count the tree's nodes
      (2) Insert the new Phone("VPhone", 800, Number of Tree's Nodes) into the current tree
 The output f3() will be written into the file "f3.txt". 
     * Therefore you should open this file to see/test your code output.
     * Example: With the data given in "data.txt", the content of "f3.txt" after running this method is             
(Samsung_S8,850.0,2) (Samsung_S7,800.0,-7) (Samsung_S6,750.0,9) (Samsung_S5,700.0,4) (Samsung_S4,650.0,5) (Samsung_S3,600.0,3) (Samsung_S2,550.0,8) (Samsung_S1,500.0,22) 
(Vphone,800.0,8) (Samsung_S8,850.0,2) (Samsung_S7,800.0,-7) (Samsung_S6,750.0,9) (Samsung_S5,700.0,4) (Samsung_S4,650.0,5) (Samsung_S3,600.0,3) (Samsung_S2,550.0,8) (Samsung_S1,500.0,22)
     */
    
    void addOneNode(){
        //------ Start your code here------------------------------------------------------------
        int numOfNodes = countNodes(root);
        Phone newNodeData = new Phone("VPhone", 800, numOfNodes);
        root = insertRec(root, newNodeData);
        //------ End your code here--------------------------------------------------------------
    }
    private int countNodes(Node root) {
        if (root == null) {
            return 0;
        }
        return 1 + countNodes(root.getLeft()) + countNodes(root.getRight());
    }
    
    /**
     * Question 2.4: Increase the Phone's price by 200.0 if a node in the tree satisfies following conditions:
      1. It is a leaf node
      2. Phone's color is less than 9(color<9.0)
 Hint: Leaf nodes have neither left child nor right child
 The output f4() will be written into the file 'f4.txt'. 
 Therefore you should open this file to see/test your code output.
 Example: With the data provided in 'data.txt', the content of 'f4.txt' after running this method is 
  (SS_Note4,400.0,8) (SS_Note3,300.0,12) (SS_Note2,200.0,6) (SS_Note1,100.0,2) 
  (SS_Note4,600.0,8) (SS_Note3,300.0,12) (SS_Note2,200.0,6) (SS_Note1,100.0,2)
     */
    public void updateTree() {
        
root = updateTreeRec(root);
   
    
    }
    private Node updateTreeRec(Node root) {
        if (root == null) {
            return null;
        }
        
        if (root.getLeft() == null && root.getRight() == null && root.getInfo().getColor() < 9) {
            root.getInfo().setPrice(root.getInfo().getPrice() + 200.0);
        }
        
        root.setLeft(updateTreeRec(root.getLeft()));
        root.setRight(updateTreeRec(root.getRight()));
        
        return root;
    }
 } // End BST
