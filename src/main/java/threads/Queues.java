package threads;

import GUI.MainFrame;
import GUI.NewFrame;
import org.example.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Array;
import java.util.*;


public class Queues extends Thread{ // implements Comparable {
    private static List<Client> clients1 = new ArrayList<>();
    private static List<Client> clients = Collections.synchronizedList(clients1);

    private List<Client> q1 = new ArrayList<>();
    private List<Client> onQueue =  Collections.synchronizedList(q1);
    private int size = onQueue.size();
    private int contor=0;

    public Queues(){
        synchronized (clients) {
                clients = Manager.printList();
        }
    }

    //@Override
    public synchronized void run(int i, int duration, int seconds) {
        int time = duration - seconds;

        contor=0;
        for (Client c : clients) {
            if (c.getArrival() == time)  contor++;
        }
       // contor+=onQueue.size();
        List<Client> clientsToRemove= new ArrayList<>();
        for(Client c: onQueue){
            c.decrementServiceTime();
            if(c.getService()<=0){
                clientsToRemove.add(c);
            }
        }
        synchronized (onQueue){
            onQueue.removeAll(clientsToRemove);
        }

        List<Client> clientsToAdd = new ArrayList<>();
                for (Client c : clients) {
                    if (c.getArrival() == time) {
                      //  clientsToAdd.add(c);
                        if(clientsToAdd.size()>(contor/(MainFrame.getQ()))) {
                            break;
                        }
                        else   clientsToAdd.add(c);
                }
            }

                synchronized (onQueue){
                    onQueue.addAll(clientsToAdd);
                //    Manager.removeAll(clientsToAdd);
                    clients.removeAll(clientsToAdd);
                }

                System.out.println(i);
                System.out.println("timp "+time);
                System.out.println("duration "+duration);
                System.out.println("seconds "+seconds);
                System.out.println("clientii " + clients);
                System.out.println("coada " + onQueue);
            try{
                FileWriter fileWriter= new FileWriter("fileName.txt", true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write("Time: "+time+"\n");
                bufferedWriter.write("Waiting clients\n" + clients+"\n");
                bufferedWriter.write("Queue "+i+": \n"+ onQueue+"\n");
                bufferedWriter.newLine();
                bufferedWriter.flush();
                bufferedWriter.close();
            }catch(IOException e){System.out.println("Error");}

//
//                List<Client> clientsToRemove= new ArrayList<>();
//                for(Client c: onQueue){
//                    c.decrementServiceTime();
//                    if(c.getService()<=0){
//                        clientsToRemove.add(c);
//                    }
//                }
//                synchronized (onQueue){
//                    onQueue.removeAll(clientsToRemove);
//                }

                synchronized (onQueue) {

                    if (onQueue.isEmpty()) {
                        contor=-1;
                        NewFrame.sisplay("Queue " + (i + 1) + ": closed", onQueue);
                    } else {

                        if(onQueue.isEmpty()) {contor=-1; NewFrame.sisplay("Queue " + (i + 1) + ": closed", onQueue);}
                        else {
                            contor=0;
                            for(Client x: onQueue) contor++;
                            //  System.out.println(contor);
                        NewFrame.sisplay("Queue " + (i + 1) + ": ", onQueue);
                        }
                    }
                }

   }


    public synchronized void run2(int i, int duration, int seconds) {
        int time = duration - seconds;

        synchronized (clients) {

            for (Client c : clients) {
                if (c.getArrival() == time) {
                    synchronized (onQueue) {
                        onQueue.add(c);
                    }
                    break;
                }
            }

            synchronized (onQueue) {

                if (onQueue.isEmpty()) {
                    contor=-1;
                } else {

                    //   List<Client> lista = onQueue;
                    for(Client c:onQueue){
                        if ((c.getService()==0)){
                            onQueue.remove(c);
                        }
                        break;
                    }
                    if(onQueue.isEmpty()) {contor=-1;}
                    else {
                        contor=0;
                        for(Client x: onQueue) contor++;
                    }
                }
            }

        }
    }
        public int getSize(){
          return onQueue.size();
        //return size;
        }

        public int getShortestTime(){
        int suma=0;
        for(Client index: onQueue)
            suma+=(index.getService());
        return suma;
        }

        public float averageWaiting(){
        float suma=0;
        for(int i=1;i<onQueue.size();i++){
            suma+=onQueue.get(i).getService();
        }
            return suma;
        }
}
//}
