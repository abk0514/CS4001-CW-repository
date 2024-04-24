public abstract class Gadget {
    private String model;
    private String size;
    private double price;
    private int weight;
    public Gadget(String model,String size,double price,int weight){
        this.model = model;
        this.size = size;
        this.price = price;
        this.weight = weight;
    }

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
    protected String display(String gadgetName){
       return "Model of "+gadgetName+" :"+this.model+'\n'+
        "Size of "+gadgetName+" :"+this.size+'\n'+
        "Price of "+gadgetName+" :"+this.price+'\n'+
        "Weight of "+gadgetName+" :"+this.weight+'\n';
    }

}
