import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class GadgetShop {
    // Ui class for all the UI and it's Logic implementation
    static class ShopUi extends JFrame {
        ArrayList<Gadget> gadgets = new ArrayList<>();
        // interface for callback function when text is change in common or some specific text fields
        interface OnTextChangedCallBack {
            void onTextChanged(String value);
        }
        // specific fields and labels for GUI components
        JTextField creditsField = new JTextField();
        JTextField memoryField = new JTextField();
        JTextField phoneNumber = new JTextField();
        JTextField duration = new JTextField();
        JTextField downloadMusicField = new JTextField();
        JTextField deleteMusicField = new JTextField();
        JLabel deleteMLabel = new JLabel("Delete Music: ");
        JLabel phLabel = new JLabel("Phone No: ");
        JLabel durationLabel = new JLabel("Duration: ");
        JLabel downloadLb = new JLabel("Download: ");
        JLabel crLabel = new JLabel("Credits: ");
        JLabel meLabel = new JLabel("Memory: ");
        JButton addMp3Btn = new JButton("Add MP3");
        JButton addMobileBtn = new JButton("Add Mobile");
        JButton makeCallButton = new JButton("Make A Call");
        JButton downloadButton = new JButton("Download Music");
        JButton deleteMusicButton = new JButton("Delete Music");
        JTextArea logs = new JTextArea(50, 50);
        String modelVal = "";
        double priceVal;
        int weightVal;
        String sizeVal = "";

        int callingCreditsVal;
        float memoryVal;
        int mDurationVal;
        String phoneNumberVal = "";

        float downloadMemoryVal;
        int displayNumberVal = -1;
        float deleteMemoryVal = 0;
        // function to create common text field for better code reusability which also receives an interfaces as a parameter to notify when text is change
        public JPanel CMTextField(JLabel jL, JTextField field, OnTextChangedCallBack textChanged) {
            JPanel jP = new JPanel();
            jP.setLayout(new BoxLayout(jP, BoxLayout.Y_AXIS));
            jP.setSize(new Dimension(500, 40));
            jL.setAlignmentX(Component.LEFT_ALIGNMENT);
            JTextField tF;
            tF = Objects.requireNonNullElseGet(field, JTextField::new);
            tF.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) { // call when text is added
                    textChanged.onTextChanged(tF.getText());

                }

                @Override
                public void removeUpdate(DocumentEvent e) { // called when text is removed
                    textChanged.onTextChanged(tF.getText());

                }

                @Override
                public void changedUpdate(DocumentEvent e) {

                }
            });
            jP.add(jL);
            jP.add(tF);
            return jP;
        }
        // set the visibility of components to invisible or visible based on which gadget is selected eg . MP3 or Mobile
        private void showReleventFeilds(boolean isMobile) {
            if (isMobile) {
                memoryField.setVisible(false);
                creditsField.setVisible(true);
                meLabel.setVisible(false);
                crLabel.setVisible(true);
                addMobileBtn.setVisible(true);
                addMp3Btn.setVisible(false);
                phoneNumber.setVisible(true);
                phLabel.setVisible(true);
                deleteMLabel.setVisible(false);
                deleteMusicField.setVisible(false);
                downloadMusicField.setVisible(false);
                downloadLb.setVisible(false);
                duration.setVisible(true);
                durationLabel.setVisible(true);
                makeCallButton.setVisible(true);
                downloadButton.setVisible(false);
                deleteMusicButton.setVisible(false);
            } else {
                memoryField.setVisible(true);
                creditsField.setVisible(false);
                meLabel.setVisible(true);
                crLabel.setVisible(false);
                addMobileBtn.setVisible(false);
                addMp3Btn.setVisible(true);
                phoneNumber.setVisible(false);
                phLabel.setVisible(false);
                deleteMLabel.setVisible(true);
                deleteMusicField.setVisible(true);
                downloadMusicField.setVisible(true);
                downloadLb.setVisible(true);
                duration.setVisible(false);
                durationLabel.setVisible(false);
                makeCallButton.setVisible(false);
                downloadButton.setVisible(true);
                deleteMusicButton.setVisible(true);
            }
        }

        public ShopUi() { // constructor of the UI class
            setTitle("Product Details");
            setSize(1000, 350);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new GridLayout(1, 3));
            JPanel mainPanel = new JPanel(new GridLayout(6, 2, 5, 0));

            JPanel commonFields = new JPanel();
            JPanel spcificFields = new JPanel();
            mainPanel.setBackground(Color.BLACK);
            commonFields.setLayout(new BoxLayout(commonFields, BoxLayout.X_AXIS));
            spcificFields.setLayout(new BoxLayout(spcificFields, BoxLayout.X_AXIS));
            //start display action
            JPanel displayActionPanel = new JPanel();
            displayActionPanel.setLayout(new GridLayout(1, 2, 10, 0));

            JButton displayButton = getjButton();
            JButton clearAllButton = new JButton("Clear All");
            clearAllButton.addActionListener(e -> clearTextComponents(this));

            JPanel textFieldPanel = new JPanel();
            textFieldPanel.setLayout(new BoxLayout(textFieldPanel, BoxLayout.X_AXIS));
            textFieldPanel.add(CMTextField(new JLabel("Display Number"), null, e -> {
                try {
                    if (!e.trim().isEmpty()) { // if text is not empty then parse it to int
                        displayNumberVal = Integer.parseInt(e);
                        displayNumberVal -= 1;
                    }
                } catch (NumberFormatException ex) { // if exception is thrown show relevant error message
                    JOptionPane.showMessageDialog(mainPanel, "Invalid Display Number");
                }
            }));

            displayActionPanel.add(textFieldPanel);
            displayActionPanel.add(Box.createHorizontalGlue());
            displayActionPanel.add(displayButton);
            displayActionPanel.add(clearAllButton);

            mainPanel.add(displayActionPanel);


            //end display action

            commonFields.add(CMTextField(new JLabel("Modal: "), null, e -> modelVal = e));
            commonFields.add(CMTextField(new JLabel("Price: "), null, e -> {
                try {
                    if (!e.trim().isEmpty()) {// check if not empty then parse it to double
                        priceVal = Double.parseDouble(e);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(mainPanel, "Invalid Value for Price"); // show error if parsing to double was unsuccessful for example user entered characters instead of digits
                }
            }));
            commonFields.add(CMTextField(new JLabel("Weight: "), null, e -> {
                try {
                    if (!e.trim().isEmpty()) {  // same here not empty parse it
                        weightVal = Integer.parseInt(e);
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(mainPanel, "Invalid Value for Weight"); // show error if parse unsuccessful

                }
            }));
            commonFields.add(CMTextField(new JLabel("Size: "), null, e -> sizeVal = e));
            //radio buttons start
            ButtonGroup buttonGroup = new ButtonGroup();
            JRadioButton mobileButton = new JRadioButton("Mobile");
            JRadioButton mp3Button = new JRadioButton("MP3");
            showReleventFeilds(true);
            mobileButton.setSelected(true);
            mobileButton.addActionListener(e -> showReleventFeilds(true));
            mp3Button.addActionListener(e -> showReleventFeilds(false));
            buttonGroup.add(mobileButton);
            buttonGroup.add(mp3Button);
            spcificFields.add(mp3Button);
            spcificFields.add(mobileButton);
            //radio buttons end


            spcificFields.add(CMTextField(crLabel, creditsField, e -> {
                try {
                    if (!e.trim().isEmpty()) {
                        callingCreditsVal = Integer.parseInt(e);
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(mainPanel, "Invalid Value for Calling Credits");

                }

            }));
            spcificFields.add(CMTextField(meLabel, memoryField, e -> {
                try {
                    if (!e.trim().isEmpty()) {
                        memoryVal = Float.parseFloat(e);
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(mainPanel, "Invalid Value for Memory");

                }
            }));


            mainPanel.add(commonFields);
            mainPanel.add(spcificFields);

            // add buttons panel
            JPanel addButtonsPanel = new JPanel();
            addButtonsPanel.add(addMobileBtn);
            addButtonsPanel.add(addMp3Btn);
            mainPanel.add(addButtonsPanel);
            addMobileBtn.addActionListener(e -> { // action on button click to add a mobile in gadgets array list
                if (!modelVal.isEmpty() && !sizeVal.isEmpty() && callingCreditsVal > 0) { // check if necessary fields are not empty
                    Mobile mobile = new Mobile(modelVal, sizeVal, priceVal, weightVal, callingCreditsVal);
                    gadgets.add(mobile); // add it to the array list
                    logs.setText(logs.getText() + '\n' + "Mobile Added"); // show the log in log window
                } else {
                    JOptionPane.showMessageDialog(mainPanel, "Input Modal Value, Credits Value  And Size value"); // if anything is missing show error
                }
            });
            addMp3Btn.addActionListener(e -> { // action on button to add MP3
                MP3 mp3 = new MP3(memoryVal, modelVal, sizeVal, priceVal, weightVal);
                if (!modelVal.isEmpty() && !sizeVal.isEmpty() && memoryVal > 0) { // check for values integrity
                    gadgets.add(mp3); // add the mp3 in array list
                    logs.setText(logs.getText() + '\n' + "MP3 Added");
                } else {
                    JOptionPane.showMessageDialog(mainPanel, "Input Modal Value, Memory Value And Size value"); // show error MessageBox
                }


            });
            // end add buttons panel

            // start actions fields
            JPanel commonActionFieldsPanel = new JPanel();
            commonActionFieldsPanel.setLayout(new BoxLayout(commonActionFieldsPanel, BoxLayout.X_AXIS));
            commonActionFieldsPanel.add(CMTextField(phLabel, phoneNumber, e -> phoneNumberVal = e));
            commonActionFieldsPanel.add(CMTextField(durationLabel, duration, e -> {
                try {
                    if (!e.trim().isEmpty()) {
                        mDurationVal = Integer.parseInt(e);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(mainPanel, "Invalid Duration Value");
                }

            }));
            commonActionFieldsPanel.add(CMTextField(downloadLb, downloadMusicField, e -> {
                try {
                    if (!e.trim().isEmpty()) {
                        downloadMemoryVal = Float.parseFloat(e);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(mainPanel, "Invalid Value for download memeory");
                }
            }));
            commonActionFieldsPanel.add(CMTextField(deleteMLabel, deleteMusicField, e -> {
                try {
                    if (!e.trim().isEmpty()) {
                        deleteMemoryVal = Float.parseFloat(e);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(mainPanel, "Invalid Value for Delete memory");
                }
            }));
            mainPanel.add(commonActionFieldsPanel);
            // end actions filed

            JPanel actionButtonsButton = new JPanel();
            actionButtonsButton.add(makeCallButton);
            actionButtonsButton.add(downloadButton);
            actionButtonsButton.add(deleteMusicButton);
            makeCallButton.addActionListener(e -> { // make call button click function
                try {
                    if (mDurationVal > 0 && !phoneNumberVal.isEmpty()) { // check if valid phone number and duration is entered
                        Mobile mobile = (Mobile) gadgets.get(displayNumberVal); // get the mobile gadget from gadget list and cast it to mobile
                        boolean isCallMde = mobile.makeCall(mDurationVal, phoneNumberVal); // make the call by calling the makeCallfunction
                        if (isCallMde)
                            logs.setText(logs.getText() + '\n' + "Call has been made"); // if call is made update the log
                        else
                            logs.setText(logs.getText() + '\n' + "Not enough Balance"); // if not show not enough balance error in log

                    } else {
                        JOptionPane.showMessageDialog(mainPanel, "Please Input Duration and Phone Number"); // if duration or phone number is invalid show error message box
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(mainPanel, "The selected Gadget is not mobile or it's not available in database"); // if selected device or display number of the gadget is not mobile then show the error, or it does not exists
                }
            });
            downloadButton.addActionListener(e -> { // download the music button click function
                try {
                    if (displayNumberVal != -1) { // if display number is not entered show error
                        if (downloadMemoryVal > 0) { // if download memory value is 0 or less then 0 show error
                            MP3 mp3 = (MP3) gadgets.get(displayNumberVal); // cast the gadget to MP3
                            boolean result = mp3.downloadMusic(downloadMemoryVal); // download the music by calling MP3 class downloadMusic function
                            if (result) {
                                logs.setText(logs.getText() + '\n' + "Music Downloaded"); // update the log on successfully download
                            } else {
                                logs.setText(logs.getText() + "\n" + "Music Not Downloaded"); // show error not enough space is there is not enough space to download
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(mainPanel, "Input Memory Value for the music to download");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(mainPanel, "The selected Gadget is not MP3 or it's not available in database"); // show cast error if the entered display number is not an MP3 device, or it does not exists

                }
            });
            deleteMusicButton.addActionListener(e -> { // delete music function
                try {
                    if (displayNumberVal != -1) { // check if display number is entered
                        if (deleteMemoryVal > 0) { // check if deleted value is greater then 0
                            MP3 mp3 = (MP3) gadgets.get(displayNumberVal); // cast it to MP3
                            boolean result = mp3.deleteMusic(deleteMemoryVal); // delete the music
                            if (result) {
                                logs.setText(logs.getText() + '\n' + "Music Deleted"); // show successfully delete log if deleted
                            } else {
                                logs.setText(logs.getText() + "\n" + "Music Not Deleted"); // if the delete memory is more then the available memory then show error in log
                            }
                        } else {
                            JOptionPane.showMessageDialog(mainPanel, "Input Memory Value for the music to delete");

                        }
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(mainPanel, "The selected Gadget is not MP3 or it's not available in database");

                }
            });
            mainPanel.add(actionButtonsButton);
            // logs compoinent
            logs.setEditable(false);

            JScrollPane scrollPane = new JScrollPane(logs);
            // end logs component
            add(mainPanel);
            add(scrollPane);


            setVisible(true);
        }

        private JButton getjButton() { // function to get common button for display all action
            JButton displayButton = new JButton("Display All");
            displayButton.addActionListener(e -> {
                int i = 0;
                for (Gadget gadget : gadgets) {  // iterates over the gadgets array list

                    if (gadget instanceof Mobile) { // check if Mobile then call the mobile display

                        logs.setText(logs.getText() + "\n" + "Display Number :" + (i + 1) + '\n' + ((Mobile) gadget).display()+"\n\n");

                    } else if (gadget instanceof MP3) { // else call the MP3 Display
                        logs.setText(logs.getText() + "\n" + "Display Number :" + (i + 1) + '\n' + ((MP3) gadget).display()+"\n\n");

                    }
                    i++;
                }
            });
            return displayButton;
        }

        private void clearTextComponents(Container container) { // clear all the text filed and logs  by iterating over parent component
            for (Component c : container.getComponents()) {
                if (c instanceof JTextField) { // check if any component is JTextField clear it's text
                    ((JTextField) c).setText("");
                } else if (c instanceof JTextArea) { // same for Text Area

                    ((JTextArea)c).setText("");
                } else if (c instanceof Container) { // if it's a Container component then make the recursive call to go inside it and check if there is any JTextField in it and clear it
                    clearTextComponents((Container) c);
                }
            }
        }
    }
    // main function for main class GadgetShop is main class
    public static void main(String[] args) {
        new ShopUi();
    }
}