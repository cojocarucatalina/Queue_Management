package org.example;
import GUI.*;
import threads.*;
import java.util.Random;

public class Client extends NewFrame { //} implements Comparable{

    private int id;
    private int arrival;
    private int service;

    public Client(){

        arrival=getRandomArrival();
        id=getRandom();
        service=getRandomService();

    }

    public int getRandom(){  // number of id
        int n = MainFrame.getN();
        Random rand= new Random();
        int random = rand.nextInt(n)+1;
        return random;
    }

    public int getRandomArrival(){  // number of id
        int n1 = MainFrame.getMinimArrival();
        int n2 = MainFrame.getMaximArrival();
        Random random= new Random();
        int rand =random.nextInt((n2 - n1) + 1) + n1;
        return rand;
    }

    public int getRandomService(){  // number of id
        int n1 = MainFrame.getMinimService();
        int n2 = MainFrame.getMaximService();
        Random random1= new Random();
        int rand =random1.nextInt((n2 - n1) + 1) + n1;
        return rand;
    }

    public int getId(){
        return id;
    }

    public String toString(){
       // return "( id: "+id+" arrival: "+arrival+" service: "+service+")";
        return "("+id+","+arrival+","+service+")  ";
    }

    public int getService() {
        return service ;
    }

    public int getArrival(){
        return arrival;
    }

    public void decrementServiceTime(){
        service--;
    }

    public int compareTo(Client c){
        return arrival-c.arrival;
    }

//    public int getWait(){
//        return arrival;

}
