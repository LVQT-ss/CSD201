// =========================================================
// Do NOT modify this file 
// =========================================================

class Phone {
    String name;
    double price;
    int color;
    
    // Default constructure
    Phone () {}
    
    // Constructor with full parameter
    Phone (String xName, double xPrice, int xColor) {
        this.name = xName;
        this.price = xPrice; 
        this.color = xColor;
    }
    
    @Override
    public String toString(){
        return "(" + name + "," + price + "," + color + ")";
    }

    public double getPrice() {
        return this.price;
    }
    
    public int getColor() {
        return this.color;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setColor(int inColor) {
        this.color = inColor;
    }
    
    public void setPrice(double inPrice) {
        this.price = inPrice;
    }
    
    public void setName(String inName) {
        this.name = inName;
    }
}