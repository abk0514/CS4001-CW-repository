public abstract class Gadget {
    private String model;
    private String size;
    private double price;
    private int weight;
    public Gadget(String model,String size,double price,int weight){ // constructor for general Gadget class
        this.model = model;
        this.size = size;
        this.price = price;
        this.weight = weight;
    }
    // getter methods
    public double getPrice() {
        return price;
    }

    public int getWeight() {
        return weight;
    }

    public String getModel() {
        return model;
    }

    public String getSize() {
        return size;
    }
    // returns the appended and formated text
    protected String display(String gadgetName){
       return "Model of "+gadgetName+" :"+this.model+'\n'+
        "Size of "+gadgetName+" :"+this.size+'\n'+
        "Price of "+gadgetName+" :"+this.price+'\n'+
        "Weight of "+gadgetName+" :"+this.weight+'\n';
    }

}
