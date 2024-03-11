package GUI;
import org.example.*;
import threads.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class NewFrame extends JFrame {

    private static JPanel panel = new JPanel();
    private static JScrollPane scrollPanel = new JScrollPane();
    private static JTextArea area= new JTextArea();

    private Threads t1= new Threads(MainFrame.getSimTime());

    public NewFrame(){

    setTitle("SIMULATION");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(500, 500);

    add(panel);
     panel.setLayout(new GridLayout(0,1,0,0));

     Container container = new Container();
     container.setLayout(new BorderLayout());
     container.add(panel, BorderLayout.CENTER);

     scrollPanel = new JScrollPane(container);
     getContentPane().add(scrollPanel);

   // panel.add(area);

        Manager m=new Manager();

        startTimeThread();
      //  Manager.startOthers();
        setVisible(true);

    }

    public void startTimeThread(){

        t1.start();

      //  startOthers();

      //  Threads average= new Threads();
     //   average.start();

    }

    public static List printWaiting(List lista){
        return lista;
    }

    public static void displayTime(String string, List<Client> lista){
        JTextArea area1= new JTextArea();
        panel.add(area1);
        String s=string+"\n"+"Waiting list: ";//+printWaiting(lista)+"\n\n";

        s+=printWaiting(lista);
        s+="\n\n";

        area1.setText(s);
        area1.setEditable(false);
    }

//    public static void displayClients(){
//        JTextArea area1= new JTextArea();
//        panel.add(area1);
//        String s="Waiting list: "+Manager.printList()+"\n\n";
//
//        //   s+=Manager.print();
//
//        area1.setText(s);
//        area1.setEditable(false);
//    }

    public static void display(String string){

        JTextArea area1= new JTextArea();
        panel.add(area1);

        area1.setText(string);
        area1.setEditable(false);
    }

    public static void sisplay(String string,List<Client> c){

        JTextArea area1= new JTextArea();
        panel.add(area1);
        String s="";
        s+=string;
        for (Client i:c) {

            s+=i.toString();

        }
        area1.setText(s);
        area1.setEditable(false);
    }

    public static void displayService(float i){
        JTextArea area1= new JTextArea();
        panel.add(area1);
        String s="AVERAGE SERVICE TIME IS: ";
        s+=String.valueOf(i);
        area1.setText(s);
        area1.setEditable(false);
    }

    public static void displayPeak(int i){
        JTextArea area1= new JTextArea();
        panel.add(area1);
        String s="PEAK HOUR IS: ";
        s+=String.valueOf(i);
        area1.setText(s);
        area1.setEditable(false);
    }

    public static void displayWaiting(float i){
        JTextArea area1= new JTextArea();
        panel.add(area1);
        String s="AVERAGE WAITING TIME IS: ";
        s+=String.valueOf(i);
        area1.setText(s);
        area1.setEditable(false);

    }
}
