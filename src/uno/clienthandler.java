/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uno;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Sig
 */
public class clienthandler implements Runnable {

    MainClass object;
    ObjectOutputStream oos;
    DataInputStream dis;

    Socket s;
    int state;
    clientdata out1;
    Serverhandler server;

    clienthandler(Socket s, DataInputStream dis, ObjectOutputStream oos, int a, Serverhandler server) {
        this.dis = dis;
        this.oos = oos;
        this.s = s;
        System.out.print("let the loop begin");
        this.state = a;
        object = new MainClass();
        this.server = server;
    }

    public void run() {

//     if (state==1) {
//         out1 = new clientdata(object.user1,object.mcard.get(object.mcard.size()-1));
//     } else {
//         out1 = new clientdata(object.user2,object.mcard.get(object.mcard.size()-1));
//     }
//    try {
//        server.sendAll();
//        oos.reset();
//    } catch (Exception ex) {
//        Logger.getLogger(clienthandler.class.getName()).log(Level.SEVERE, null, ex);
//    }
        Thread listenerThread = new Thread(() -> {
            try {
                while (true) {

                    int move = dis.readInt();

                    receivedData(move);
                    Thread.sleep(100);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        listenerThread.start();

//     try {
//         while (true) {
////             oos.writeObject(out1);
////             oos.flush();
//             Thread.sleep(100);
//         }
//     } catch (Exception e) {
//         e.printStackTrace();
//     }
    }

    public void receivedData(int move) throws Exception {

        if (move == 40) {
            object.draw(CardDeck.deck);
        } else if (move == 30) {
            object.pass();
        } else if (move != 100) {
            object.throw1(move);
        }
        System.out.println("My mid is " + object.mcard.get(object.mcard.size() - 1));

        //object.display(out1.userlist);
        //oos.reset();
        server.sendAll();

    }

    public void sendData() throws Exception {

        if (state == 1) {
            out1 = new clientdata(object.user1, object.mcard.get(object.mcard.size() - 1).number, object.mcard.get(object.mcard.size() - 1).color, object.current);
        } else {
            out1 = new clientdata(object.user2, object.mcard.get(object.mcard.size() - 1).number, object.mcard.get(object.mcard.size() - 1).color, object.current);
        }
        oos.reset();
        oos.writeObject(out1);

    }
    /*
@Override 
 public void run()
 { clientdata out1;
   clientdata out2;
 int received;
 object.current=(object.current+1)%2;
 
 out1=new clientdata(object.user1,object.mcard.get(object.mcard.size()-1));
   out2=new clientdata(object.user2,object.mcard.get(object.mcard.size()-1));
 
 while(true)
 {  
     
 try {
     
//sdatastream.mcarddata=(object.mcard).get((object.mcard.size()-1));
 
    if(object.current==2-state)
    {
        System.out.println("In first if");
        if(object.current==1)
        {
            System.out.println("checking in second if");//sdatastream.a=object.user1;
            JOptionPane.showMessageDialog(null,out1.userlist);
            oos.writeObject(out1);
            oos.flush();
        }
        else
        {
            System.out.println("In second else");
            oos.writeObject(out2);
            oos.flush();
        }
        
            //sdatastream.a=object.user2;
            
                //rdatastream=(fromClienData) dis.readObject();
              //object.throw1(rdatastream.i);
    }
   if(object.current==2-state){
    received=dis.readInt();
     System.out.println(received+" ye ayya hai"+object.current);
    if(received>=0)
    {   System.out.println("hghjg");
        object.throw1(received);
        
    }
   }
     
   
    
    
 
         
         
     } catch (Exception e) {
          
       // e.printStackTrace();
        System.out.println(e+"npr");
     }
//     try { 
//         this.dis.close();
//         this.dos.close();
//     } catch (IOException ex) {
//         //Logger.getLogger(clienthandler.class.getName()).log(Level.SEVERE, null, ex);
//         ex.printStackTrace();
//     }
            

 }
 
 
 
 }
     */

}
