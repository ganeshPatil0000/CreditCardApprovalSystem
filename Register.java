package JavaProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
public class Register
{
    JFrame f;
    JTextField t1,t3;
    JPasswordField t2,t7;

    Connection con=null;
    Statement stmt;
    public Register()
    {
        f=new JFrame();
        f.getContentPane().setBackground(new Color(240, 255, 255));
        f.setSize(700,500);
        f.getContentPane().setLayout(null);
        f.setLocationRelativeTo(null);

        JLabel l1= new JLabel ("Registration Page");
        l1.setFont(new Font("Tahoma", Font.PLAIN, 25));
        l1.setBounds(268,22,255,40);
        f.getContentPane().add(l1);

        JLabel l2= new JLabel ("Enter username :");
        l2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        l2.setBounds(100,180,120,40);
        f.getContentPane().add(l2);

        t1=new JTextField();
        t1.setBounds(268,183  ,255,40);
        f.getContentPane().add(t1);

        JLabel l3= new JLabel ("Enter Password :");
        l3.setFont(new Font("Tahoma", Font.PLAIN, 15));
        l3.setBounds(100,243,120,40);
        f.getContentPane().add(l3);

        t2=new JPasswordField();
        t2.setBounds(268,246,255,40);
        f.getContentPane().add(t2);

        JLabel l4= new JLabel ("Enter FullName :");
        l4.setFont(new Font("Tahoma", Font.PLAIN, 15));
        l4.setBounds(100,120,120,40);
        f.getContentPane().add(l4);

        t3=new JTextField();
        t3.setBounds(268,123,255,40);
        f.getContentPane().add(t3);




        JLabel l8= new JLabel ("Confirm Password :");
        l8.setFont(new Font("Tahoma", Font.PLAIN, 15));
        l8.setBounds(100,300,158,40);
        f.getContentPane().add(l8);

        t7=new JPasswordField();
        t7.setBounds(268,303,255,40);
        f.getContentPane().add(t7);

        JButton b1= new JButton ("Submit");
        b1.setBounds(289,380,100,40);
        f.getContentPane().add(b1);



        b1.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {

                String username=t1.getText().toString();
                char p[]=t2.getPassword();
                String password=new String(p);
                String name=t3.getText().toString();

                char q[]=t7.getPassword();
                String confirm_password=new String(q);


                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    con=DriverManager.getConnection("jdbc:mysql://172.22.37.41/applicationform","Project","Pass@123");
                    stmt=con.createStatement();         //connection establishment

                    if(password.equals(confirm_password))
                    {
                        stmt.executeUpdate("insert into reg values ('"+ username +"','"+ password +"','"+ name +"','"+ confirm_password+"')");

                        f.dispose();
                        JOptionPane.showMessageDialog(f, "register successfully");
                        f.dispose();
                    }
                    else
                        JOptionPane.showMessageDialog(f, "Please Retype Your Password.");

                }catch(ClassNotFoundException e1)
                {
                    e1.printStackTrace();
                }
                catch(SQLException e1)
                {
                    e1.printStackTrace();
                }
            }
        });


        f.setVisible(true);
    }
};