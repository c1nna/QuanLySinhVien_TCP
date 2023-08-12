/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import model.Request;
import model.Response;
import model.Student;

/**
 *
 * @author Admin
 */
public class ClientControl {
    private int port;
    private String host;
    private Socket mySocket;

    public ClientControl() {
        host = "localhost";
        port = 9999;
    }
    public void openSocket(){
        try{
            mySocket = new Socket(host, port);
            System.out.println(mySocket);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void sendReq(Request req){
        try{
            ObjectOutputStream oos = new ObjectOutputStream(mySocket.getOutputStream());
            oos.writeObject(req);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public Response getRes(){
        Response res = null;
        try{
            ObjectInputStream ois = new ObjectInputStream(mySocket.getInputStream());
            res = (Response) ois.readObject();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return res;
    }
    
    public void closeSocket(){
        try{
            mySocket.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
