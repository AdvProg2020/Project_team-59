package Controller.NetWork;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientBank {
    public static void main(String[] args) {
        new ClientImpl().run();
    }
    static class ClientImpl{
        private Socket socket;
        private DataInputStream dataInputStream;
        private DataOutputStream dataOutputStream;
        private Scanner scanner;

        private void connectionHandling(){
            try {
                scanner=new Scanner(System.in);
                dataInputStream=new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                dataOutputStream=new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
                System.out.println("enter your command");
                String input=scanner.nextLine();
                dataOutputStream.writeUTF(input);
                dataOutputStream.flush();
                String response=dataInputStream.readUTF();
                System.out.println(response);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        public void run(){
            try {
                System.out.println("trying to connect...");
                socket=new Socket("localhost",8888);
                System.out.println("connected");
                connectionHandling();
            } catch (IOException e) {
                System.err.println("Error starting client");
            }
        }
    }
}
