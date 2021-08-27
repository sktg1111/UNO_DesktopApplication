/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uno;

import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author user
 */
public class MainClass  {

    static GameGui game;

    static CardDeck objdeck;

    public static void StartGame() {
        game = new GameGui();
        objdeck = new CardDeck();

        MainClass obj = new MainClass();
        obj.distr(objdeck.deck);
        //new Thread(new MainClass()).start();
    }

    //@Override
    //public void run() {
        //To change body of generated methods, choose Tools | Templates.
    
    static ArrayList<Card> user1 = new ArrayList<Card>();
    static ArrayList<Card> user2 = new ArrayList<Card>();
    static ArrayList<Card> mcard = new ArrayList<Card>();
    int top;
    static int current = 0;
    static boolean flag = false;
    static int userid = 0;

    public void distr(ArrayList<Card> deck) {
        for (int i = 0; i < 7; i++) {
            user1.add(deck.get(0));

            deck.remove(0);
            System.out.println(user1.get(i).number);

        }
        for (int i = 0; i < 7; i++) {
            user2.add(deck.get(0));
            deck.remove(0);
            System.out.println(user2.get(i).number);
        }

        mcard.add(deck.get(0));
        deck.remove(0);
        int a = mcard.get(mcard.size() - 1).number;
        if (a > 9 && a < 19) {
            a = a - 9;
        }
        game.maincard.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\small\\" + mcard.get(mcard.size() - 1).color + "_" + a + ".png"));
        display(user1);
    }

    public void draw(ArrayList cards) {    //if(user1.size()>14||user2.size()>14);

        if (current == 0) {
            user1.add(objdeck.deck.get(0));
            objdeck.deck.remove(0);
            display(user1);
        } else if (current == 1) {
            user2.add(objdeck.deck.get(0));
            objdeck.deck.remove(0);
            display(user2);
        }
        game.draw.setEnabled(false);

    }

    public void throw1(int i) throws Exception {
        System.out.println("HEHEHE");
        Card rem = new Card(-1, "abc");
        rem.color = "nocolor";
        rem.number = -1;
        if (current == 0 && user1.get(i).symbol == "+4") {
            game.maincard.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\small\\" + user1.get(i).color + "_" + user1.get(i).number + ".png"));

            mcard.add(user1.get(i));
            user1.set(i, rem);
            plus4(current, objdeck.deck);
            display(user2);
        } else if (current == 1 && user2.get(i).symbol == "+4") {
            game.maincard.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\small\\" + user2.get(i).color + "_" + user2.get(i).number + ".png"));

            mcard.add(user2.get(i));
            user2.set(i, rem);
            plus4(current, objdeck.deck);
            display(user1);
        } else if (current == 0) {
            int a = user1.get(i).number;
            int b = mcard.get(mcard.size() - 1).number;

            if (a > 9 && a < 19) {
                a = a - 9;
            }
            if (b > 9 && b < 19) {
                b = b - 9;
            }
            if ((mcard.get(mcard.size() - 1).color == user1.get(i).color) || a == b || mcard.get(mcard.size() - 1).symbol == "+4") {
                System.out.print("check");
                mcard.add(user1.get(i));
                game.maincard.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\small\\" + mcard.get(mcard.size() - 1).color + "_" + a + ".png"));
                user1.set(i, rem);
                display(user2);

                if (a < 19) {
                    if (!flag) {
                        current = (current + 1) % 2;
                    } else {
                        current = ((current - 1) % 2 + 2) % 2;
                    }
                } else if (a == 20 || a == 19) {
                    plus2(current, objdeck.deck);
                    System.out.print("qwerty");
                } else if (a == 22 || a == 21) {
                    skip();
                } else if (a == 23 || a == 24) {
                    reverse();
                }
                System.out.println("User is playing" + current);

                turn();
            }
        } else if (current == 1) {
            int a = user2.get(i).number;
            int b = mcard.get(mcard.size() - 1).number;
            if (a > 9 && a < 19) {
                a = a - 9;
            }
            if (b > 9 && b < 19) {
                b = b - 9;
            }
            if ((mcard.get(mcard.size() - 1).color == user2.get(i).color) || a == b || mcard.get(mcard.size() - 1).symbol == "+4") {
                mcard.add(user2.get(i));

                game.maincard.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\small\\" + mcard.get(mcard.size() - 1).color + "_" + a + ".png"));
                user2.set(i, rem);
                display(user1);
                if (a < 19) {
                    if (!flag) {
                        current = (current + 1) % 2;
                    } else {
                        current = (((current - 1) % 2) + 2) % 2;
                    }
                } else if (a == 20 || a == 19) {
                    plus2(current, objdeck.deck);
                    System.out.print("qwerty");
                } else if (a == 22 || a == 21) {
                    skip();
                } else if (a == 23 || a == 24) {
                    reverse();
                }

                System.out.println("User is playing" + current);
                turn();
            }
        }
    }

    public void turn() {
        game.draw.setEnabled(true);
        if (current == 0) {
            System.out.print("User1 is playing" + current);
            display(user1);
        } else if (current == 1) {
            System.out.println("User2 is playing" + current);
            display(user2);
        }

        //if(!flag)
        //  current=(current+1)%2;
        //else
        //  current=(current-1)%2;
    }

    public void skip() {
        System.out.println("skip");
        current = (current + 2) % 2;
        turn();

    }

    public void reverse() {
        flag = !flag;
        if (!flag) {
            current = (current + 1) % 2;
        } else {
            current = ((current - 1) % 2 + 2) % 2;
        }
    }

    public void plus2(int userid, ArrayList<Card> deck) {
        if (current == 1) {
            user1.add(deck.get(0));
            deck.remove(0);
            user1.add(deck.get(0));
            deck.remove(0);
        }
        if (current == 0) {
            user2.add(deck.get(0));
            deck.remove(0);
            user2.add(deck.get(0));
            deck.remove(0);

        }
        if (!flag) {
            current = (current + 1) % 2;
        } else {
            current = ((current - 1) % 2 + 2) % 2;
        }
    }

    public void plus4(int userid, ArrayList<Card> deck) {
        if (current == 1) {
            user1.add(deck.get(0));
            deck.remove(0);
            user1.add(deck.get(0));
            deck.remove(0);
            user1.add(deck.get(0));
            deck.remove(0);
            user1.add(deck.get(0));
            deck.remove(0);
        }
        if (current == 0) {
            user2.add(deck.get(0));
            deck.remove(0);
            user2.add(deck.get(0));
            deck.remove(0);
            user2.add(deck.get(0));
            deck.remove(0);
            user2.add(deck.get(0));
            deck.remove(0);
        }
        if (!flag) {
            current = (current + 1) % 2;
        } else {
            current = ((current - 1) % 2 + 2) % 2;
        }
    }

    public void colorChange() {
    }

    public void sayUno() {
        if (user1.size() == 1) {//enable button}

        }
        if (user2.size() == 1) {
            //enable button}
        }
    }

    public void display(ArrayList<Card> currentdeck) {
        System.out.println(currentdeck.size());
        for (int i = 0; i < 25; i++) {
            game.btn[i].setIcon(null);
        }
        for (int i = 0; i < currentdeck.size(); i++) {
            int a = currentdeck.get(i).number;
            if (a > 9 && a < 19) {
                a = a - 9;
            }
            System.out.println(currentdeck.get(i).number);
            game.btn[i].setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\small\\" + currentdeck.get(i).color + "_" + a + ".png"));
            //    System.out.print("checked");

        }
    }

    public void pass() {
        if (!flag) {
            current = (current + 1) % 2;
        } else {
            current = ((current - 1) % 2 + 2) % 2;
        }
        turn();
    }

}
