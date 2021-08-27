/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uno;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Sig
 */
public class Serverhandler {
    
    ArrayList<clienthandler> clients = new ArrayList<>();
    
    public void sendAll() throws Exception {
        for (clienthandler chandler:clients) {
            chandler.sendData();
        }
        
    }
    
    public Serverhandler()throws Exception {
        ServerSocket ss=new ServerSocket(8080);
    int i=1; Socket socket = null;
      MainClass object1=new MainClass();
    //String s1[]=new String[1];
    object1.StartGame();
   // clientdata d1=new clientdata(object1.user1,object1.mcard.get(object1.mcard.size()-1));
     //clientdata d2=new clientdata(object1.user2,object1.mcard.get(object1.mcard.size()-1));
     while(i>0)
     {try{
        
        System.out.println("waiting......"+i);
        socket=ss.accept();
        System.out.println("accepted  "+i);
//       ObjectInputStream input=new ObjectInputStream(socket.getInputStream());
//       ObjectOutputStream output=new ObjectOutputStream(socket.getOutputStream());
       ObjectOutputStream output=new ObjectOutputStream(socket.getOutputStream());
       DataInputStream input=new DataInputStream(socket.getInputStream());;
       System.out.println("xedvgv");
      //if(i==1)
        //   output.writeObject(d1);
       //else
         //  output.writeObject(d2);
        clienthandler chandle = new clienthandler(socket,input,output,i,this);
        clients.add(chandle);
      Thread t1=new Thread(chandle);
     t1.start();
     //object1.current=(object1.current+1)%2;

       
        i++;
      }
     catch(Exception e)
     { //socket.close();
     e.printStackTrace();
     }
     }
    }

     public static void main(String[] args) throws Exception
    {
        Serverhandler server = new Serverhandler();
    
    }
  

}
    

    
