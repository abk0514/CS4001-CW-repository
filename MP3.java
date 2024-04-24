import javax.swing.*;

public class MP3 extends Gadget{
    private float availableMemory;
    private final float TOTAL_MEMORY;
    public MP3(float availableMemory,String model,String size,double price,int weight){
        super(model,size,price,weight);
        this.availableMemory = availableMemory;
        TOTAL_MEMORY = availableMemory;
    }
    public float getAvailableMemory(){
        return availableMemory;
    }

    public String display(){
        String data = super.display(MP3.class.getName());
        data += "Available Memory: "+availableMemory;
        return data;

    }
    public boolean downloadMusic(float memory){
        if(memory>availableMemory){
            JOptionPane.showMessageDialog(null,"Not Enough Space to download this music");
            return false;
        }
        else{
            JOptionPane.showMessageDialog(null,"Music Downloaded");
            availableMemory -= memory;
            return true;
        }
    }
    public boolean deleteMusic(float memory){
        if((availableMemory+memory) > TOTAL_MEMORY){
            JOptionPane.showMessageDialog(null,"Invalid Delete Memory Operation");
            return false;
        }
        else{
            JOptionPane.showMessageDialog(null,"Music Deleted");
            availableMemory += memory;
            return true;
        }
    }
}
