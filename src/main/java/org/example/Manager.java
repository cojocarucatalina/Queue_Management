package org.example;
import GUI.*;
import org.example.*;
import threads.*;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Manager {

    private static List<Client> list = new ArrayList<>();

    private static List<Client> waiting = Collections.synchronizedList(list);
    private static ArrayList<Threads> cozi = new ArrayList<>();//array de threaduri
    private static float avgService=0;
    private float avgWait=0;

    Set<Integer> usedId=new HashSet<>();

    public Manager() {
        for (int i = 1; i <= MainFrame.getN(); i++) {

//            Client c = new Client();
//            for (Client j : waiting){
//                if (j.getId() == c.getId())
//                    c = new Client();
//                }
//            waiting.add(c);
//            }
            Client c = new Client();
            while (usedId.contains(c.getId()))
                c = new Client();
            usedId.add(c.getId());

            avgService+=c.getService();
        //    avgWait+=c.getWait();
            waiting.add(c);
        }

    }
        public static List printList(){
             return waiting;
        }
        public static List printL1st(){
             return list;
        }

        public static synchronized void remove(Client cl){

        synchronized (waiting) {
            for (Client c : waiting) {
                if (cl == c) {
                    waiting.remove(c);
                    break;
                }
            }
        }
        }

        public static String print(){
           String string ="";
           for(int i=0;i<MainFrame.getQ();i++) {
               string += "Queue " + (i + 1) + ":closed\n\n";
//               try {
//                   FileWriter fileW = new FileWriter();
//                   fileW.write(string);
//                   fileW.close();
//               } catch (IOException e) {
//                   System.out.println("An error occurred.");
//               }
           }
        return string;
        }



    public static float getAverageService(){
        return avgService/MainFrame.getN();
    }

    public static void removeAll(List<Client> list) {
        for(Client c: list){
            remove(c);
        }
    }
}
