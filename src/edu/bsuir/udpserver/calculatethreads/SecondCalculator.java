package edu.bsuir.udpserver.calculatethreads;

public class SecondCalculator extends Thread {

    private int b = 0;
    private int c = 0;
    private double secondsum = 0;


    public SecondCalculator(int b,int c){
        this.b = b;
        this.c = c;
        start();
    }

    public double getSecondsum() {
        return secondsum;
    }

    @Override
    public void run(){

        for(int n = b; n <= c; n++ ){

            secondsum += 2/3*n;

        }


    }


}






