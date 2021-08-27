/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uno;

import java.awt.Color;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.Thread.sleep;
import java.net.Socket;
import javax.swing.ImageIcon;
import static uno.MainClass.mcard;
import java.io.ByteArrayOutputStream;

/**
 *
 * @author Sig
 */
public class Client {

    private static ObjectInputStream oi;
    private static DataOutputStream oo;
    private Socket socket;
    static private int send;
    static String color;
    static int number;

    public Client(String ip, int port) throws IOException {
        socket = new Socket(ip, port);
        System.out.print("Server found");
        oo = new DataOutputStream(socket.getOutputStream());
        oi = new ObjectInputStream(socket.getInputStream());

    }

    public static void main(String args[]) throws IOException, ClassNotFoundException {
        ClientGui gui = new ClientGui();
        // MainClass forDisplay=new MainClass();

        Client client = new Client("localhost", 8080);

        new Thread(() -> {
            while (true) {
                try {
                    int check = 0;
                    clientdata recobj = (clientdata) oi.readObject();
                    for (int i = 0; i < recobj.userlist.size(); i++) {

                        if (recobj.userlist.get(i).number == -1) {
                            check++;
                        }
                    }

                    if (recobj.userlist.size() == check) {
                        System.out.println("YOU WIN");
                        gui.result.setForeground(Color.yellow);
                        gui.result.setFont(gui.result.getFont().deriveFont(24.0f));

                        gui.result.setText("YOU WIN");

                    }
                    gui.result.setFont(gui.result.getFont().deriveFont(40.0f));
                    gui.result.setForeground(Color.WHITE);
                    gui.result.setText("User " + (recobj.current + 1) + " is playing");
                    if (recobj.userlist.size() >= 25) {
                        System.out.println("YOU LOSE");
                        gui.result.setFont(gui.result.getFont().deriveFont(40.0f));
                        gui.result.setForeground(Color.WHITE);
                        gui.result.setText("YOU LOSE");

                    } else {
                        System.out.println(recobj.userlist);

                        //forDisplay.display(recobj.a);
                        System.out.println(recobj.color + " " + recobj.number);
                        Thread.sleep(100);
                        gui.displayGui(recobj.userlist);
                        color = recobj.color;
                        number = recobj.number;
                        if (number > 9 && number < 19) {
                            number = number - 9;
                        }
                        //gui.maincard.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\small1\\"+recobj.color+"_"+recobj.number+".png"));
                        gui.maincard.setIcon(new ImageIcon("C:\\Users\\MY HP\\Desktop\\small\\" + color + "_" + number + ".png"));

                        //gui.maincard.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\small\\"+recobj.mcarddata.color+"_"+recobj.mcarddata.number+".png"));
                        System.out.print("client ready to accept");

                        Thread.sleep(100);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // ClientGui gui=new ClientGui();

            }
        }).start();

    }

    public static void Perform(int j) {
        int temp = send;
        try {
            System.out.println("qwert");
            send = j;
            if (send != temp) {
                oo.writeInt(send);

            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.print("error caught");
        }
    }
}
