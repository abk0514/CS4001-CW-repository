import javax.swing.*;

public class Mobile extends  Gadget{
    private int callingCredits = 0;
    public Mobile(String model, String size, double price, int weight,int callingCredits) {
        super(model, size, price, weight);
        this.callingCredits = callingCredits;
    }
    public int getCallingCredits(){
        return callingCredits;
    }
    public String display(){
        String data = super.display(Mobile.class.getName());
       data += "Calling Credits Available: "+callingCredits+'\n';
       return data;
    }
    public void addCredits(int credits){
        if(credits >0){
            callingCredits+= credits;
        }
        else{
            JOptionPane.showMessageDialog(null,"Credits Value must be a Non Zero Positive Number");
        }
    }
    public boolean makeCall(int duration,String phoneNumber){
        if(duration>callingCredits){
            JOptionPane.showMessageDialog(null,"Insufficient credit to make this call");
            return false;
        }
        else{
            callingCredits -= duration;
            JOptionPane.showMessageDialog(null,"Call to number "+phoneNumber+" Complete \n"+"Remaining Credits Are: "+callingCredits);
            return true;
        }
    }

}
