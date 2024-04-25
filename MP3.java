import javax.swing.*;

// Extends Gadget to create a specialized MP3 class.
public class MP3 extends Gadget {
    private float availableMemory;  // Tracks the currently available memory.
    private final float TOTAL_MEMORY;  // Constant to hold the total memory capacity of the MP3.

    // Constructor to initialize an MP3 object with its properties.
    public MP3(float availableMemory, String model, String size, double price, int weight) {
        super(model, size, price, weight);  // Calls the superclass constructor to set  gadget properties.
        this.availableMemory = availableMemory;  // Set the initial available memory.
        TOTAL_MEMORY = availableMemory;  // Set total memory as the initial available memory.
    }

    // Returns the current available memory in the MP3.
    public float getAvailableMemory() {
        return availableMemory;
    }

    // Overrides the display method to include MP3-specific details.
    public String display() {
        String data = super.display(MP3.class.getName());  // Include general gadget info.
        data += " Available Memory: " + availableMemory;  // Append available memory info to the display string.
        return data;
    }

    // Attempts to download music and adjusts memory accordingly.
    public boolean downloadMusic(float memory) {
        if (memory > availableMemory) {  // Check if there's enough available memory.
            JOptionPane.showMessageDialog(null, "Not Enough Space to download this music");
            return false;
        } else {
            JOptionPane.showMessageDialog(null, "Music Downloaded");
            availableMemory -= memory;  // Deduct the memory used by the music file.
            return true;
        }
    }

    // Attempts to delete music and free up the used memory.
    public boolean deleteMusic(float memory) {
        if ((availableMemory + memory) > TOTAL_MEMORY) {  // Check if freed memory exceeds total capacity.
            JOptionPane.showMessageDialog(null, "Invalid Delete Memory Operation");  // Show error message.
            return false;
        } else {
            JOptionPane.showMessageDialog(null, "Music Deleted");  // Confirm deletion success.
            availableMemory += memory;  // Add back the memory freed by deleting the music file.
            return true;
        }
    }
}
