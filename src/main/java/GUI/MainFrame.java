package GUI;
import org.example.*;
import threads.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainFrame extends JFrame {

    private JFrame frame;
    private JPanel inputPanel;
    private JPanel buttonPanel;

    private JLabel numClients;
    private static JTextField numClientsText= new JTextField();
    private JLabel queues;
    private static JTextField queuesText= new JTextField();
    private JLabel tMax;
    private static JTextField tMaxText= new JTextField();
    private JLabel minArr;
    private static JTextField minArrText= new JTextField();
    private JLabel maxArr;
    private static JTextField maxArrText= new JTextField();
    private JLabel minService;
    private static JTextField minServiceText= new JTextField();
    private JLabel maxService;
    private static JTextField maxServiceText= new JTextField();

    private JButton validate = new JButton("Validate");
    private JButton startButton = new JButton("Start");
    private static JRadioButton queueButton = new JRadioButton("shortest Queue");

    public MainFrame() {

        setTitle("QUEUES");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(10, 2));

     //   inputPanel.setBackground(Color.PINK);

        numClients = new JLabel("Number of clients: ");
        queues = new JLabel("Number of queues");
        tMax = new JLabel ("Simulation TIME");
        minArr = new JLabel("Minimum ARRIVAL time: ");
        maxArr = new JLabel("Maximum ARRIVAL time: ");
        minService = new JLabel("Minimum SERVICE time: ");
        maxService = new JLabel("Maximum SERVICE time: ");
        JTextField infoText = new JTextField("Input is INVALID");

     //   numClientsText.setEditable(false);

        add(inputPanel);
        inputPanel.add(numClients);

        inputPanel.add(numClientsText);
    //    numClientsText.setBackground(Color.PINK);
        inputPanel.add(queues);
        inputPanel.add(queuesText);
        inputPanel.add(tMax);
        inputPanel.add(tMaxText);
        inputPanel.add(minArr);

        inputPanel.add(minArrText);
      //  minArrText.setBackground(Color.PINK);
        inputPanel.add(maxArr);
     //   maxArrText.setBackground(Color.PINK);
        inputPanel.add(maxArrText);
        inputPanel.add(minService);
      //  minServiceText.setBackground(Color.PINK);
        inputPanel.add(minServiceText);
        inputPanel.add(maxService);
     //   maxServiceText.setBackground(Color.PINK);
        inputPanel.add(maxServiceText);
        inputPanel.add(validate);
        validate.setBackground(Color.PINK);
        inputPanel.add(startButton);
        startButton.setBackground(Color.PINK);
        inputPanel.add(infoText);
        inputPanel.add(queueButton);
        infoText.setText("");
        infoText.setEditable(false);

        validate.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){

               String text1 = minArrText.getText();
               String text2 = maxArrText.getText();
               String text5 = tMaxText.getText();
                try{
                int minA= Integer.parseInt(text1);
                int maxA= Integer.parseInt(text2);
                int tim = Integer.parseInt(text5);
                if (minA > maxA || maxA>tim || minA<=0 || maxA<=0 ) {
                    infoText.setText("INVALID INPUT");
                    startButton.setEnabled(false);
                    } else {
                    infoText.setText("START simulation");
                    startButton.setEnabled(true);
                    }
                } catch(NumberFormatException ec){infoText.setText("INVALID INPUT"); }


                String text3 = minServiceText.getText();
                String text4 = maxServiceText.getText();

                try{
                    int minS= Integer.parseInt(text3);
                    int maxS= Integer.parseInt(text4);
                    if (minS > maxS || minS<=0 || maxS<=0 ) {
                        infoText.setText("INVALID INPUT");
                        startButton.setEnabled(false);
                    } else {
                        infoText.setText("START simulation");
                        startButton.setEnabled(true);
                    }
                } catch(NumberFormatException ec){infoText.setText("INVALID INPUT"); }

            }
        });

        startButton.addActionListener(e->{
            NewFrame newFrame =new NewFrame();
            newFrame.setVisible(true);

            setVisible(false);
            dispose();
        });
        setVisible(true);
    }

    public static boolean getShortestQueue(){
        return queueButton.isSelected();
    }

    public static int getMaximArrival(){
        return Integer.parseInt(maxArrText.getText());
       // return 4;
    }
    public static int getMinimArrival(){
        return Integer.parseInt(minArrText.getText());
        //return 1;
    }
    public static int getMaximService(){
        return Integer.parseInt(maxServiceText.getText());
       // return 4;
    }
    public static int getMinimService(){
        return Integer.parseInt(minServiceText.getText());
       /// return 1;
    }
    public static int getN(){
        String a1= numClientsText.getText();
        try{ int a2=Integer.parseInt(a1);
        return a2;}
        catch(NumberFormatException a11) {System.out.println(a1);}
        return 0;
       // return 15;
    }
    public static int getQ(){
        return Integer.parseInt(queuesText.getText());
       // return 3;
    }
    public static int getSimTime(){
        return Integer.parseInt(tMaxText.getText());
       // return 15;
    }

}
