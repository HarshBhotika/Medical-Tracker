//Java core packages
import  java.awt.*;
import  java.awt.event.*;
import  java.awt.font.*;
import  java.text.DecimalFormat;
import  java.lang.System.*;
//Java extension packages
import  javax.swing.*;
import  javax.swing.event.*;
import  javax.swing.border.*;


public class CHD_Calc extends JFrame
        implements MouseListener, KeyListener, FocusListener, DocumentListener {
    //declare custom output formats
    private DecimalFormat DF1 = new DecimalFormat("##.0");
    private DecimalFormat DF0 = new DecimalFormat("##");
    //setup custom font
    private Font FONT1 = new Font("TimesRoman", Font.PLAIN, 18);
    //set custom RGB colors
    private Color LIGHT_GREY = new Color(225, 225, 225);
    private Color LIGHT_RED = new Color(255, 128, 128);
    private Border FLAT = BorderFactory.createEtchedBorder(Color.white, Color.gray);
    private Border UP = BorderFactory.createBevelBorder(0, Color.white, Color.darkGray);
    private Border DOWN = BorderFactory.createBevelBorder(1, Color.white, Color.darkGray);
    //arrayindex of the JTF
    private int i = 0;
    //index of the Indexed JTF that has focus
    static int n = 0;
    //declare the array holding all the data
    private double[] Holder = new double[23];
    //setup the JPanels
    private JPanel row1 = new JPanel();
    private JPanel row2 = new JPanel();
    //define the JLabel array,  holding 11 JLabels
    private JLabel JL[] = new JLabel[11];
    //setup the array holding all the initial data and label text
    private String startdata[] =  {
        "Male", "50", "150", "90", "No", "6", "1.3", "No", "No", "10", "10.4%",
                "Sex", "Age", "Sys BP", "Dias BP", "Smoking", "Tot Chol", "HDL Chol",
                "Diabetes", "LVH", "Years", "Heart Risk"
    };
    //initial data for the first 10 elements of the Holder array
    private double HolderStartdata[] =  {
        0, 50, 150, 90, 0, 6, 1.3, 0, 0, 10
    };
    //define the IndexedJTextField array,  holding 11 Indexed JTF's
    final static IndexedJTextField JTF[] = new IndexedJTextField[11];
    private boolean isError = false;
    private boolean changed = false;

    //constructor
    public CHD_Calc () {
        //setup the frame
        super(" Heart Disease Prediction");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 120);
        //layout for the whole frame (2 rows, 1 column)
        GridLayout layout1 = new GridLayout(2, 1);
        Container pane = getContentPane();
        pane.setLayout(layout1);
        //layout for the rows
        GridLayout layout2 = new GridLayout(1, 11, 8, 4);
        row1.setLayout(layout2);
        row2.setLayout(layout2);
        //fill the Holder Array with default values
        for (i = 0; i < 10; i++)
            Holder[i] = HolderStartdata[i];
        for (i = 0; i < 11; i++) {
            //add the IndexedJTextFields
            JTF[i] = new IndexedJTextField();
            row1.add(JTF[i]);
            JTF[i].setIndex(i);
            JTF[i].setFont(FONT1);
            JTF[i].setHorizontalAlignment(JTextField.CENTER);
            //add the JLabels
            JL[i] = new JLabel();
            row2.add(JL[i]);
            JL[i].setHorizontalAlignment(JLabel.CENTER);
            //add document- and focus listeners to the first 10 IndexedJTextFields
            if (i < 10) {
                JTF[i].getDocument().addDocumentListener(this);
                JTF[i].addFocusListener(this);
                JTF[i].addKeyListener(this);
                JTF[i].addMouseListener(this);
            }
            else {
                //format the last Indexed JTextField and JLabel
                JTF[i].setBorder(BorderFactory.createEtchedBorder(Color.blue,
                        Color.lightGray));
                JTF[i].setEnabled(false);
                JTF[i].setDisabledTextColor(Color.black);
                JL[i].setForeground(Color.blue);
            }
        }
        //set the initial data and text of JLabels
        for (i = 0; i < 11; i++) {
            JTF[i].setText(startdata[i].toString());
            JL[i].setText(startdata[i + 11].toString());
        }
        //add the 2 JPanels to the Container called pane
        pane.add(row1);
        pane.add(row2);
        setContentPane(pane);
        setVisible(true);
        JTF[0].requestFocus();
        JTF[0].selectAll();
        JTF[0].setBackground(Color.white);
    }

    //class to make an Indexed JTextField
    final class IndexedJTextField extends JTextField {
        private int index;

        private void setIndex (int index) {
            this.index = index;
        }

        private int getIndex () {
            return  index;
        }
    }

    public static void main (String args[]) {
        CHD_Calc frame = new CHD_Calc();
        //selectAll after minimize
        frame.addWindowListener(new WindowAdapter() {

            public void windowDeiconified (WindowEvent e) {
                JTF[n].selectAll();
            }
        });
    }

    //mouse events
    public void mouseClicked (MouseEvent mE) {
        JTF[n].selectAll();
    }

    public void mousePressed (MouseEvent mE) {
    //nil for now
    }

    public void mouseReleased (MouseEvent mE) {
    //nil for now
    }

    public void mouseEntered (MouseEvent mE) {
    IndexedJTextField FG = (IndexedJTextField)mE.getSource();
    FG.setBorder(UP);
        //nil for now
    }

    public void mouseExited (MouseEvent mE) {
    IndexedJTextField FG = (IndexedJTextField)mE.getSource();
    FG.setBorder(FLAT);
    //nil for now
    }

    //document events
    public void insertUpdate (DocumentEvent e) {
        JTF[10].setText("");
        JTF[n].setBackground(LIGHT_GREY);
        changed = true;
    }

    public void removeUpdate (DocumentEvent e) {
        JTF[10].setText("");
        JTF[n].setBackground(LIGHT_GREY);
        changed = true;
    }

    public void changedUpdate (DocumentEvent e) {
    //nil for now
    }

    //Focus events
    //action when Indexed JTextField gains focus
    public void focusGained (FocusEvent efG) {
        changed = false;
        IndexedJTextField FG = (IndexedJTextField)efG.getSource();
        FG.selectAll();
        if (isError == true)
            JTF[n].requestFocus();
        else
            //get index of FG
            n = FG.getIndex();
    }

    //action when Indexed JTextField loses focus
    public void focusLost (FocusEvent efL) {
        //do the calculation
        if (isError == false && changed == true) {
            JTF[10].setText(doCalc());
        }
    }

    //key events
    public void keyTyped (KeyEvent ke) {
    //nil for now
    }

    public void keyReleased (KeyEvent ke) {
    //nil for now
    }

    public void keyPressed (KeyEvent ke) {
        //react to key_UP
        if (ke.getKeyCode() == KeyEvent.VK_UP) {
            switch (n) {
                case 0:         //Sex up
                    if (Holder[0] == 1) {
                        Holder[0] = 0;
                        JTF[0].setText("Male");
                    }
                    else {
                        Holder[0] = 1;
                        JTF[0].setText("Female");
                    }
                    JTF[0].selectAll();
                    break;
                case 1:         //Age up
                    if (Holder[1] < 29.9)
                        Holder[1] = 30;
                    else if (Holder[1] < 73.9) {
                        Holder[1] = Holder[1] + 1;
                    }
                    JTF[1].setText(String.valueOf(DF0.format((Holder[1]))));
                    JTF[1].selectAll();
                    break;
                case 2:         //Sys BP up
                    if (Holder[2] < 79.9)
                        if (Holder[3] < 69.9)
                            Holder[2] = 80;
                        else
                            Holder[2] = Holder[3] + 10;
                    else if (Holder[2] < 249.9) {
                        Holder[2] = Holder[2] + 1;
                    }
                    JTF[2].setText(String.valueOf(DF0.format((Holder[2]))));
                    JTF[2].selectAll();
                    break;
                case 3:         //Dias BP up
                    if (Holder[3] < 29.9)
                        Holder[3] = 30;
                    else if (Holder[3] < 159.9 && Holder[3] < Holder[2] - 10.1) {
                        Holder[3] = Holder[3] + 1;
                    }
                    JTF[3].setText(String.valueOf(DF0.format((Holder[3]))));
                    JTF[3].selectAll();
                    break;
                case 4:         //Smoking up
                case 7:         //Diabetes up
                case 8:         //LVH up
                    if (Holder[n] == 1) {
                        Holder[n] = 0;
                        JTF[n].setText("No");
                    }
                    else {
                        Holder[n] = 1;
                        JTF[n].setText("Yes");
                    }
                    JTF[n].selectAll();
                    break;
                case 5:         //Tot Chol up
                    if (Holder[5] < 1.99)
                        if (Holder[6] < 1.49) {
                            Holder[5] = 2;
                            JTF[5].setText("2");
                        }
                        else {
                            Holder[5] = Holder[6] + 0.5;
                            JTF[5].setText(String.valueOf(DF1.format((Holder[5]))));
                        }
                    else if (Holder[5] < 11.99) {
                        Holder[5] = Holder[5] + 0.1;
                        JTF[5].setText(String.valueOf(DF1.format((Holder[5]))));
                    }
                    JTF[5].selectAll();
                    break;
                case 6:         //HDL Chol up
                    if (Holder[6] < 0.29) {
                        Holder[6] = 0.3;
                        JTF[6].setText("0.3");
                    }
                    else if (Holder[6] < 4.99 && Holder[6] < Holder[5] - 0.501) {
                        Holder[6] = Holder[6] + 0.1;
                        JTF[6].setText(String.valueOf(DF1.format((Holder[6]))));
                    }
                    JTF[6].selectAll();
                    break;
                case 9:         //Years up
                    if (Holder[9] < 1.9)
                        Holder[9] = 2;
                    else if (Holder[9] < 9.9) {
                        Holder[9] = Holder[9] + 1;
                    }
                    JTF[9].setText(String.valueOf(DF0.format((Holder[9]))));
                    JTF[9].selectAll();
                    break;
            }
            //do the calculation
            JTF[10].setText(doCalc());
        }
        //react to key_DOWN
        if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
            switch (n) {
                case 0:         //Sex down
                    if (Holder[0] == 1) {
                        Holder[0] = 0;
                        JTF[0].setText("Male");
                    }
                    else {
                        Holder[0] = 1;
                        JTF[0].setText("Female");
                    }
                    JTF[0].selectAll();
                    break;
                case 1:         //Age down
                    if (Holder[1] > 74.1)
                        Holder[1] = 74;
                    else if (Holder[1] > 30) {
                        Holder[1] = Holder[1] - 1;
                    }
                    JTF[1].setText(String.valueOf(DF0.format((Holder[1]))));
                    JTF[1].selectAll();
                    break;
                case 2:         //Sys BP down
                    if (Holder[2] > 250.1)
                        Holder[2] = 250;
                    else if (Holder[2] > 80.1 && Holder[2] > Holder[3] + 10.1) {
                        Holder[2] = Holder[2] - 1;
                    }
                    JTF[2].setText(String.valueOf(DF0.format((Holder[2]))));
                    JTF[2].selectAll();
                    break;
                case 3:         //Dias BP down
                    if (Holder[3] > 160.1)
                        if (Holder[2] > 170.1)
                            Holder[3] = 160;
                        else
                            Holder[3] = Holder[2] - 10;
                    else if (Holder[3] > 30.1) {
                        Holder[3] = Holder[3] - 1;
                    }
                    JTF[3].setText(String.valueOf(DF0.format((Holder[3]))));
                    JTF[3].selectAll();
                    break;
                case 4:         //Smoking down
                case 7:         //Diabetes down
                case 8:         //LVH down
                    if (Holder[n] == 1) {
                        Holder[n] = 0;
                        JTF[n].setText("No");
                    }
                    else {
                        Holder[n] = 1;
                        JTF[n].setText("Yes");
                    }
                    JTF[n].selectAll();
                    break;
                case 5:         //Tot Chol down
                    if (Holder[5] > 12.01) {
                        Holder[5] = 12;
                        JTF[5].setText("12");
                    }
                    else if (Holder[5] > 2.01 && Holder[5] > Holder[6] + 0.501) {
                        Holder[5] = Holder[5] - 0.1;
                        JTF[5].setText(String.valueOf(DF1.format((Holder[5]))));
                    }
                    JTF[5].selectAll();
                    break;
                case 6:         //HDL Chol down
                    if (Holder[6] > 5.01)
                        if (Holder[5] > 5.51) {
                            Holder[6] = 5;
                            JTF[6].setText("5");
                        }
                        else {
                            Holder[6] = Holder[5] - 0.5;
                            JTF[6].setText(String.valueOf(DF1.format((Holder[6]))));
                        }
                    else if (Holder[6] > 0.301) {
                        Holder[6] = Holder[6] - 0.1;
                        JTF[6].setText(String.valueOf(DF1.format((Holder[6]))));
                    }
                    JTF[6].selectAll();
                    break;
                case 9:         //Years down
                    if (Holder[9] > 10.1)
                        Holder[9] = 10;
                    else if (Holder[9] > 2.1) {
                        Holder[9] = Holder[9] - 1;
                    }
                    JTF[9].setText(String.valueOf(DF0.format((Holder[9]))));
                    JTF[9].selectAll();
                    break;
            }
            //do the calculation
            JTF[10].setText(doCalc());
        }
        //react to other keys
        if (ke.getKeyCode() == KeyEvent.VK_ESCAPE)
            System.exit(0);
        if (ke.getKeyCode() == KeyEvent.VK_ENTER && isError == false) {
            JTF[n].transferFocus();
        }
        if (ke.getKeyCode() == KeyEvent.VK_TAB && isError == false)
            JTF[n].transferFocus();
        if (ke.getKeyCode() == KeyEvent.VK_F12 && isError == false) {
            if (n > 0)
                JTF[n - 1].requestFocus();
            else
                JTF[9].requestFocus();
        }
    }

    //fill the array, check the input and do the calculation
    public String doCalc () {
        try {
            isError = false;
            //fill the Holder array
            for (i = 0; i < 10; i++) {
                switch (i) {
                    //dealing with Sex
                    case 0:
                        if (JTF[0].getText().startsWith("f") || JTF[0].getText().startsWith("F")) {
                            Holder[0] = 1;
                            JTF[0].setText("Female");
                        }
                        else {
                            if (JTF[0].getText().startsWith("m") || JTF[0].getText().startsWith("M")) {
                                Holder[0] = 0;
                                JTF[0].setText("Male");
                            }
                            else
                                throw  new Exception();
                        }
                        JTF[0].selectAll();
                        break;
                        //dealing with smoking, diabetes and LVH
                    case (4):case (7):case (8):
                        if (JTF[i].getText().startsWith("y") || JTF[i].getText().startsWith("Y")) {
                            Holder[i] = 1;
                            JTF[i].setText("Yes");
                        }
                        else {
                            if (JTF[i].getText().startsWith("n") || JTF[i].getText().startsWith("N")) {
                                Holder[i] = 0;
                                JTF[i].setText("No");
                            }
                            else
                                throw  new Exception();
                        }
                        //JTF[i].selectAll();
                        break;
                    default:
                        //dealing with numeric fields
                        Holder[i] = Double.parseDouble(JTF[i].getText());
                        break;
                }
            }
            //check for out of range input
            if (Holder[1] > 74 || Holder[1] < 30 || Holder[2] > 250 || Holder[2] < 80
                    || Holder[3] > 160 || Holder[3] < 30 || Holder[5] > 12 ||
                    Holder[5] < 2 || Holder[6] > 5 || Holder[6] < 0.3 || Holder[9] > 10
                    || Holder[9] < 2 || (Holder[2] - Holder[3]) < 9.9 || (Holder[5]
                    - Holder[6]) < 0.49) {
                throw  new Exception();
            }
            JTF[n].setBackground(Color.white);
            //**********start of calculations
            //systolic a
            Holder[11] = 11.1122 - 0.9119*Math.log(Holder[2]) - 0.2767*Holder[4]
                    - 0.7181*Math.log(Holder[5]/Holder[6]) - 0.5865*Holder[8];
            //diastolic a
            Holder[17] = 11.0938 - 0.867*Math.log(Holder[3]) - 0.2789*Holder[4]
                    - 0.7142*Math.log(Holder[5]/Holder[6]) - 0.7195*Holder[8];
            //systolic m
            if (Holder[0] < 1)
                //Male
                Holder[12] = (Holder[11] - 1.4792*Math.log(Holder[1])) - 0.1759*Holder[7];
            else
                //Female
                Holder[12] = (Holder[11] - 5.8549) + 1.8515*Math.log(Holder[1]/74)*Math.log(Holder[1]/74)
                        - 0.3758*Holder[7];
            //diastolic m
            if (Holder[0] < 1)
                //Male
                Holder[18] = (Holder[17] - 1.6343*Math.log(Holder[1])) - 0.2082*Holder[7];
            else
                //Female
                Holder[18] = (Holder[17] - 6.5306) + 2.1059*Math.log(Holder[1]/74)*Math.log(Holder[1]/74)
                        - 0.4055*Holder[7];
            //systolic mu
            Holder[13] = 4.4181 + Holder[12];
            //diastolic mu
            Holder[19] = 4.4284 + Holder[18];
            //systolic sigma
            Holder[14] = Math.exp(-0.3155 - 0.2784*Holder[12]);
            //diastolic sigma
            Holder[20] = Math.exp(-0.3171 - 0.2825*Holder[18]);
            //systolic u
            Holder[15] = (Math.log(Holder[9]) - Holder[13])/Holder[14];
            //diastolic u
            Holder[21] = (Math.log(Holder[9]) - Holder[19])/Holder[20];
            //systolic p
            Holder[16] = 1 - Math.exp(-Math.exp(Holder[15]));
            //diastolic p
            Holder[22] = 1 - Math.exp(-Math.exp(Holder[21]));
            //Indexed JTF[10] font red if > 29.9
            if ((Holder[16]*100) > 29.9 || (Holder[22]*100) > 29.9) {
                JTF[10].setDisabledTextColor(Color.red);
            }
            else {
                JTF[10].setDisabledTextColor(Color.black);
            }
            //final risk score, formatted to 1 decimal
            changed = false;
            if (Holder[22] > Holder[16])
                return  (DF1.format(Holder[22]*100) + "%");
            else
                return  (DF1.format(Holder[16]*100) + "%");
            //end of try
        }
        //***********end of calculations
        catch (Exception e) {
            //go back to the field that caused the error
            isError = true;
            JTF[n].requestFocus();
            JTF[n].selectAll();
            JTF[n].setBackground(LIGHT_RED);
            return  "Error";
        }
    }
}
