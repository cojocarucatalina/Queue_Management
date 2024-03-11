package threads;
import GUI.*;
import com.sun.tools.javac.Main;
import org.example.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class Threads extends Thread{

  //  private ArrayList<Threads> fire = new ArrayList<>();
    private int duration;

    public Threads(int duration){
        this.duration = duration;
    }
    private static List<Queues> fir= new ArrayList<Queues>();
    private static List<Queues> fire = Collections.synchronizedList(fir);
    //private static List<Queues> ordonat1 = new ArrayList<Queues>();
    private ArrayList<Queues> ordonat = new ArrayList<>();
  //  private Queues[] ordonat=null;
  private LinkedBlockingQueue<Client> waitingClient= new LinkedBlockingQueue<>();
    int ok=0;
    int j = 1;
    FileWriter fileWriter;
    BufferedWriter bufferedWriter;
    int verif=0;
    private static int peakHour=0;
    private static float avgWaiting=0;
    private static int peakHourSize=0;
    private static int suma;

    @Override
    public void run() {
        //System.out.println("This thread is running");
        synchronized (fire) {
            int seconds = duration;
            while (seconds > 0) {

                if (seconds == duration) { //un fel de initializare

                    for (int i = 0; i < MainFrame.getQ(); i++) {
                        Queues t = new Queues();
                        fire.add(t);
                    }

                        try {
                            FileWriter fileWriter = new FileWriter("fileName.txt", false);
                            fileWriter.write("");
                            fileWriter.close();
                            System.out.println("File cleared successfully.");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }


                    List<Client> waitingClients = new ArrayList<Client>();
                  //  waitingClients=Manager.printL1st();

                synchronized (Manager.printList()) {
                    waitingClients.addAll(Manager.printList());
                }

                    System.out.println(waitingClients + "aici 1");

                    List<Client> clientsToRemove = new ArrayList<Client>();
                    for (Client c : waitingClients) {
                     //   System.out.println(c.getArrival());
                        if (c.getArrival() == (duration - seconds))
                            clientsToRemove.add(c);
                    }


                synchronized (waitingClients) {
                    waitingClients.removeAll(clientsToRemove);
                }

                    System.out.println(NewFrame.printWaiting(waitingClients) + "aici 2");
                    System.out.println(clientsToRemove + "aici 3");
                    System.out.println(Manager.printList() + "aici 4");
                    System.out.println(Manager.printL1st() + "aici 4");

                    NewFrame.displayTime(("Time:" + (duration - seconds)), waitingClients);

                    List<Queues> copie = new ArrayList<Queues>();
                    copie = fire;

//daca schimb aici getShortest cu getSize ==> shortest Queue strategy

                    if (!MainFrame.getShortestQueue())
                        Collections.sort(copie, Comparator.comparingInt(Queues::getShortestTime));
                    else
                        Collections.sort(copie, Comparator.comparingInt(Queues::getSize));

                    ArrayList<Queues> ordonat = new ArrayList<>(copie);

                    System.out.println(ordonat);
                    suma = 0;
                    for (int index = 0; index < ordonat.size(); index++) {
                        suma += ordonat.get(index).getSize();
                        avgWaiting += ordonat.get(index).averageWaiting();
                        System.out.println(ordonat.get(index).getSize());
                        ordonat.get(index).run(index, duration, seconds);
                    }
                    if (suma > peakHourSize) {
                        peakHourSize = suma;
                        peakHour = duration - seconds - 1;
                    }
                    try {
                        sleep(1000);
                    } catch (InterruptedException intr) {}
                    seconds--;

                    if (seconds == 0) {
                        finish();
                    }
                    verif++;
                }
            }
        }

    public int getDuration(){
      return duration;
    }

    public void finish(){
        NewFrame.displayService(Manager.getAverageService());
        NewFrame.displayWaiting(avgWaiting/MainFrame.getQ());
        NewFrame.displayPeak(peakHour);

        try{
            FileWriter fileWriter= new FileWriter("fileName.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("Average service is: "+Manager.getAverageService());
            bufferedWriter.write("\nAverage waiting time is: " + (avgWaiting/ MainFrame.getQ()));
            bufferedWriter.write("\nPeak hour is: "+ peakHour);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            bufferedWriter.close();
        }catch(IOException e){System.out.println("Error");}

        System.out.println(Manager.getAverageService());
        System.out.println(avgWaiting);
        System.out.println(peakHour);
    }

}
