import javax.swing.*;

public class Mobile extends  Gadget{
    private int callingCredits = 0;
    // constructor for mobile class to initialize the parameters
    public Mobile(String model, String size, double price, int weight,int callingCredits) {
        super(model, size, price, weight); // pass common attributes to parent class constructor
        this.callingCredits = callingCredits; // initialize the credits by passed parameter to constructor
    }
    public int getCallingCredits(){
        return callingCredits;
    }
    // return the formated data about mobile along with general attribute from parent class
    public String display(){
        String data = super.display(Mobile.class.getName()); // get the common attribute
       data += "Calling Credits Available: "+callingCredits+'\n'; // append the calling credits with common attributes
       return data;
    }
    // add credits
    public void addCredits(int credits){
        if(credits >0){
            callingCredits+= credits;
        }
        else{
            JOptionPane.showMessageDialog(null,"Credits Value must be a Non Zero Positive Number");
        }
    }
    // method to make call with two parameters phone number and duration
    public boolean makeCall(int duration,String phoneNumber){
        if(duration>callingCredits){ // check if credits are insufficient then show error
            JOptionPane.showMessageDialog(null,"Insufficient credit to make this call");
            return false;
        }
        else{
            callingCredits -= duration; // deduct the credits by duration and show call message that call has been made
            JOptionPane.showMessageDialog(null,"Call to number "+phoneNumber+" Complete \n"+"Remaining Credits Are: "+callingCredits);
            return true;
        }
    }

}
