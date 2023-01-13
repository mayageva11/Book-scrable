package test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class MyServer  {
    
    private final int port;
    private final ClientHandler clientHandler;
    private volatile boolean stop= false;
    //constructor
    public MyServer( int port, ClientHandler clientHandler) {
        this.port = port;
        this.clientHandler = clientHandler;
        stop= false;
    }
    //a private method that runs the server
    private void runServer() throws Exception {
        ServerSocket server = new ServerSocket(port);
        server.setSoTimeout(1000);
        while (!stop) {
            try {
                Socket aClient = server.accept();
                try {
                   clientHandler.handleClient(aClient.getInputStream(), aClient.getOutputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    clientHandler.close();
                    aClient.close();

                }
            } catch (SocketTimeoutException e) {
                e.printStackTrace();
            }
        }
        server.close();
    }
        
    //this method runs the server in the background
    public void start() {
        new Thread(() -> {
            try {
                runServer();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
   
//this method will stop the server from running in the background and will close the server
    
    public void close() {
        stop = true;
    }
  	
}
