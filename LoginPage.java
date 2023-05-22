package JavaProject;

import JavaProject.ApplicationForm1;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.*;

class LoginPage extends JFrame
{
    JTextField user1;
    JTextField password1;

    Connection con = null;
    
    public LoginPage() throws IOException {
        con = (Connection) DB.dbconnect();
        this.setSize(700,500);
        setTitle("Customer Login");
        setLocation(380,120);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.cyan);


        Font fo = new Font("Times New Roman",Font.BOLD,30);
        Font fo1 = new Font("Times New Roman",Font.PLAIN,18);



        JLabel stu1 = new JLabel("LOGIN");
        stu1.setBounds(300, 30, 200, 100);
        stu1.setFont(fo);
        add(stu1);


        JLabel j1 = new JLabel("Username :");
        j1.setBounds(200, 150, 100, 40);
        j1.setFont(fo1);
        add(j1);

        JLabel j2 = new JLabel("Password :");
        j2.setBounds(200, 230, 100, 40);
        j2.setFont(fo1);
        add(j2);

        user1 = new JTextField();
        user1.setBounds(350,150,120,40);
        add(user1);

        password1 = new JPasswordField();
        password1.setBounds(350,230,120,40);
        add(password1);


        JButton b1 = new JButton("Submit");
        b1.setFont(fo1);
        b1.setBounds(300,350,120,40);
        add(b1);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String usr1= user1.getText();
                    String pwd1=String.valueOf(password1.getText());
                    PreparedStatement pst= (PreparedStatement)con.prepareStatement("select * from reg where Username =? and CPassword=?");
                    pst.setString(1,usr1);
                    pst.setString(2,pwd1);
                    ResultSet r = pst.executeQuery();
                    if(r.next())
                    {
                        JOptionPane.showMessageDialog(null,"Login Successfull ");
                        dispose();
                        new ApplicationForm1();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"Invalid Username or Password");
                    }
                }catch (Exception e1)
                {
                    e1.printStackTrace();
                }

            }

        });

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


}