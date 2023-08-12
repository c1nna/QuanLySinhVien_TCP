/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import model.Request;
import model.Response;
import model.Student;

/**
 *
 * @author Admin
 */
public class ServerControl {
    private int port;
    private String host;
    private DAO dao;
    private ServerSocket myServer;
    private Socket s;
    public ServerControl() {
        port = 9999;
        host = "localhost";
        dao = new DAO();
        openSocket();
        while(true){
            try{
                s = myServer.accept();
                System.out.println(s);
                execute(s);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
    public void openSocket(){
        try {
            myServer = new ServerSocket(port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void sendRes(Response res){
        try{
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(res);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public Request receiveReq(Socket s){
        Request req = null;
        try{
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            req = (Request) ois.readObject();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return req;
    }
    
    public void execute(Socket s){
        try{
            Request req = receiveReq(s);
            Response res = null;
            switch (req.getCode()) {
                case 1:
                    if (dao.addStudent((Student) req.getO())) {
                        res = new Response(1, "ok");
                        sendRes(res);
                        System.out.println("Them thanh cong!!!");
                    }
                    else {
                        res = new Response(1, "failed");
                        sendRes(res);
                        System.out.println("That bai!!!");
                    }
                    break;
                case 2:
                    List<Student> list = dao.getAll();
                    res = new Response(2, list);
                    sendRes(res);
                    break;
                case 3:
                    if (dao.updateStudent((Student) req.getO())) {
                        res = new Response(3, "ok");
                        sendRes(res);
                        System.out.println("Sua thanh cong!!!");
                    }
                    else {
                        res = new Response(3, "failed");
                        sendRes(res);
                        System.out.println("That bai!!!");
                    }
                    break;
                case 4:
                    if (dao.delStudent((Student) req.getO())) {
                        res = new Response(4, "ok");
                        sendRes(res);
                        System.out.println("Xoa thanh cong!!!");
                    }
                    else {
                        res = new Response(4, "failed");
                        sendRes(res);
                        System.out.println("That bai!!!");
                    }
                    break;
                case 5:
                    List<Student> list1 = dao.getStudentByName((Student) req.getO());
                    res = new Response(5, list1);
                    sendRes(res);
                    break;
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
}
