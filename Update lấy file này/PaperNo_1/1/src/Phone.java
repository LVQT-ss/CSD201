// =========================================================
// Do NOT modify this file 
// =========================================================

class Phone {
    private String name;
    private double price; 
    private int color;
    
    // Default constructor
    Phone() {}
    
    // Constructor with full parameter
    Phone(String xName, double xPrice, int xColor) {
        this.name = xName;
        this.price = xPrice; 
        this.color = xColor;
    }
    
    @Override
    public String toString(){
        return "(" + name + ", " + price + ", " + color + ")";
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
    public void setPrice(float inPrice) {
        this.price = inPrice;
    }
    public void setName(String inName) {
        this.name = inName;
    }
}
