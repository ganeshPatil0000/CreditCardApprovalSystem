package JavaProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


class Myframe_1 extends JFrame implements ActionListener
{

    JLabel label1,label2,label3,label4,ide;
    JButton select,card,perform;
    JTextField t2,t3,t4;


    String ids;


    Connection con=null;

    Myframe_1(String id)
    {
        ids =id;
        con= DB.dbconnect();
        setTitle("Transaction");
        setSize(500,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        Container c =getContentPane();
        c.setLayout(null);





        label1=new JLabel("Customer ID :");
        label1.setBounds(10,60,100,20);
        c.add(label1);

        ide=new JLabel(id);
        ide.setBounds(130,60,100,20);
        c.add(ide);


        select=new JButton(" Select a Customer ");
        select.setBounds(450,60,180,20);




        label2=new JLabel("Total Purchase :");
        label2.setBounds(10,120,100,20);
        c.add(label2);

        t2=new JTextField();
        t2.setBounds(120,120,100,20);
        c.add(t2);

        label3=new JLabel("Credit Card :");
        label3.setBounds(10,180,100,20);


        t3=new JTextField();
        t3.setBounds(120,180,200,20);


        card=new JButton(" Select credit card ");
        card.setBounds(450,180,180,20);


        label4=new JLabel("Store Name :");
        label4.setBounds(10,180,100,20);
        c.add(label4);

        t4=new JTextField();
        t4.setBounds(120,180,200,20);
        c.add(t4);

        perform=new JButton("Perform Transaction");
        perform.setBounds(160,260,180,20);
        c.add(perform);

        perform.addActionListener(this);


        setVisible(true);
        validate();



    }



    public void actionPerformed(ActionEvent e) {



        try {

            String amount = t2.getText();
            String sname = t4.getText();
            String Id=ids;

            PreparedStatement pst = con.prepareStatement("INSERT INTO cpclients(id,purchase,StoreName) VALUES(?,?,?)");
            pst.setString(1, Id);
            pst.setString(2, amount);
            pst.setString(3, sname);

            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "data added");

        }
        catch(Exception a){
            a.printStackTrace();
        }
    }

}

public class Transaction {
    public static void main(String[] args) {

        String id="10";
        Myframe_1 frame=new Myframe_1(id);
        frame.getContentPane().setBackground(new Color(192,192,192));

    }

}