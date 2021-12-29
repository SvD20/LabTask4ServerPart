package edu.bsuir.udpserver.server;

import edu.bsuir.udpserver.calculatethreads.FirstCalculator;
import edu.bsuir.udpserver.calculatethreads.SecondCalculator;

import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class ServerUDP {

    public final static int SERVICE_PORT=8080;
    public final static String PATH_TO_FILE_FOR_RESULT = "c:/file4.txt";


    public static void main(String[] args) {

        try{

            DatagramSocket serverSocket = new DatagramSocket(SERVICE_PORT);

            byte[] receivingDataBuffer = new byte[1024];
            byte[] sendingDataBuffer = new byte[1024];

            DatagramPacket inputPacket = new DatagramPacket(receivingDataBuffer, receivingDataBuffer.length);
            System.out.println("Сервер ждет датаграммы клиента...");

            serverSocket.receive(inputPacket);
            String receivedData = new String(inputPacket.getData());


            String s[]=receivedData.split(" ");
            int a = Integer.parseInt(s[0].trim());
            int b = Integer.parseInt(s[1].trim());
            int c = Integer.parseInt(s[2].trim());

            FirstCalculator firstCalculator = new FirstCalculator(a,b);
            SecondCalculator secondCalculator = new SecondCalculator(b,c);

            firstCalculator.join();
            secondCalculator.join();

            double resultsum = firstCalculator.getFirstsum() + secondCalculator.getSecondsum();
            saveToFile(a,b,c,resultsum);

            String sendingData = new String(String.valueOf(resultsum));
            sendingDataBuffer = sendingData.getBytes();


            InetAddress senderAddress = inputPacket.getAddress();
            int senderPort = inputPacket.getPort();


            DatagramPacket outputPacket = new DatagramPacket(
                    sendingDataBuffer, sendingDataBuffer.length,
                    senderAddress,senderPort
            );

            serverSocket.send(outputPacket);
            serverSocket.close();

            System.out.println("Сервер закончил работу");

        }
        catch (IOException | InterruptedException e){
            e.printStackTrace();
        }
    }


    public static void saveToFile(int a, int b, int c, double resultsum) throws IOException {

        FileWriter fileWriter = new FileWriter(PATH_TO_FILE_FOR_RESULT);

        fileWriter.write(a);
        fileWriter.write("");
        fileWriter.write(b);
        fileWriter.write("");
        fileWriter.write(c);
        fileWriter.write("");
        fileWriter.write((int) resultsum);

        fileWriter.close();

        System.out.println("Исходные данные и результат записаны в файл");

    }

}





