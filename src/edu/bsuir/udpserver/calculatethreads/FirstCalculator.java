package edu.bsuir.udpserver.calculatethreads;

public class FirstCalculator extends Thread{

    private int a = 0;
    private int b = 0;
    private double firstsum = 0;


    public FirstCalculator(int a,int b){
        this.a = a;
        this.b = b;
        start();
    }

    public double getFirstsum() {
        return firstsum;
    }

    @Override
    public void run(){

     for(int n = a; n <= b; n++ ){

         firstsum += Math.pow(2,n)*(n+1);

     }


    }

}
