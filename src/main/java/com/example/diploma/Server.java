package com.example.diploma;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class Server {
    private ServerSocket serverSocket;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }
    public void startServer(){
      try{
          while(!serverSocket.isClosed()){
              Socket socket = serverSocket.accept();
              System.out.println("A new client connected");
              ClientHandler cLientHandler = new ClientHandler(socket);
              Thread thread = new Thread(cLientHandler);
              thread.start();
          }
      }catch (IOException e){
          closeServerSocket();
      }
    }
    public void closeServerSocket(){
        try{
            if(serverSocket != null){
                serverSocket.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        Server server = new Server(serverSocket);
        server.startServer();
    }


}
