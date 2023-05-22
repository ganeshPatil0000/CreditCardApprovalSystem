package JavaProject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

class Myframe extends JFrame {

    JLabel label1,label2,label3,label4,label5,label6,label7,label8;
    JTextField t1,t2,t3,t4;
    JRadioButton male,female;
    JComboBox day,month,year,card;
    JTextArea tal;

    JCheckBox terms;

    JButton submit,up;
    JButton transaction;
    JLabel msg;
    JTextArea screen;

String id;
    Connection con=null;
    public Myframe() throws IOException {

        con=DB.dbconnect();

        setTitle("Application form");
        setSize(700,500);
        setContentPane(new JLabel(getImage()));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        Container c =getContentPane();
        c.setLayout(null);

        label8=new JLabel("Customer ID :");
        label8.setBounds(10,20,100,20);
        label8.setForeground(Color.cyan);
        c.add(label8);

        t4=new JTextField();
        t4.setBounds(130,20,120,20);
        c.add(t4);


        label1=new JLabel("First name :");
        label1.setBounds(10,60,100,20);
        label1.setForeground(Color.cyan);

        c.add(label1);

        t1=new JTextField();
        t1.setBounds(130,60,120,20);
        c.add(t1);

        label2=new JLabel("Middle Name :");
        label2.setBounds(10,100,100,20);
        label2.setForeground(Color.cyan);

        c.add(label2);

        t2=new JTextField();
        t2.setBounds(130,100,120,20);
        c.add(t2);

        label3=new JLabel("Last Name :");
        label3.setBounds(10,140,100,20);
        c.add(label3);
        label3.setForeground(Color.cyan);


        t3=new JTextField();
        t3.setBounds(130,140,120,20);
        c.add(t3);

        label4=new JLabel("Gender :");
        label4.setBounds(10,180,100,20);
        c.add(label4);
        label4.setForeground(Color.cyan);


        male=new JRadioButton("Male");
        female=new JRadioButton("Female");
        male.setBounds(130,180,80,20);
        female.setBounds(210,180,80,20);

        c.add(male);
        c.add(female);

        ButtonGroup gen=new ButtonGroup();
        gen.add(male);
        gen.add(female);

        label5=new JLabel("DOB :");
        label5.setBounds(10,220,80,20);
        c.add(label5);
        label5.setForeground(Color.cyan);


        String days[]={"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
        String months[]={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
        String years[]={"2010","2009","2008","2007","2006","2005","2004","2003","2002","2001","2000","1999","1998","1997","1996","1995","1994","1993","1992","1991","1990"};


        day=new JComboBox(days);
        month=new JComboBox(months);
        year=new JComboBox(years);


        day.setBounds(130,220,80,20);
        month.setBounds(230,220,80,20);
        year.setBounds(330,220,80,20);

        c.add(day);
        c.add(month);
        c.add(year);

        label6=new JLabel("Card Type :");
        label6.setBounds(10,260,80,20);
        c.add(label6);
        label6.setForeground(Color.cyan);


        String card_type[]={"Master card", "Visa", "Capital One", "Business", "Lic credit card"};
        card=new JComboBox(card_type);

        card.setBounds(130,260,80,20);

        c.add(card);

        label7=new JLabel("Billing Address :");
        label7.setBounds(10,300,100,20);
        c.add(label7);
        label7.setForeground(Color.cyan);


        tal=new JTextArea();
        tal.setBounds(130,300,200,50);
        c.add(tal);

        terms=new JCheckBox("Confirm");
        terms.setBounds(30,380,80,20);
        c.add(terms);

        submit=new JButton("Submit");
        submit.setBounds(160,380,80,20);
        c.add(submit);




        transaction=new JButton("Transaction");
        transaction.setBounds(480,380,120,20);
        c.add((transaction));

        up=new JButton("Update");
        up.setBounds(280,380,80,20);
        c.add(up);


        screen=new JTextArea();
        screen.setBounds(430,50,230,300);
        c.add(screen);

        msg=new JLabel();
        msg.setBounds(10,400,250,20);
        c.add(msg);


            submit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    if(terms.isSelected()) {
                    try {

                        String name = t1.getText();
                        String mname = t2.getText();
                        String lname = t3.getText();
                        id = t4.getText();
                        String gender = "male";

                        if (female.isSelected()) {
                            gender = "female";
                        }

                        String dob = day.getSelectedItem() + "-" + month.getSelectedItem() + "-" + year.getSelectedItem();
                        int value = 0;
                        Object obj = year.getSelectedItem();
                        value = Integer.parseInt((String)obj);

                        if(value >= 2005){
                            JOptionPane.showMessageDialog(null,"You are not eligible");
                        }else{
                            String cardt = (String) card.getSelectedItem();
                            String address = tal.getText();

                            screen.setText("Customer ID:" + id + "\n" + "First Name: " + name + "\n" + "Middle Name: " + mname + "\n" + "Last Name :" + lname + "\n" + "Gender : " + gender + "\n" + "DOB: " + dob + "\n" + "Card Type :" + cardt + "\n" + "Address :" + address);

                            PreparedStatement pst = con.prepareStatement("INSERT INTO client(id,fname,mname,lname,gender,DOB,CardTypr,address) VALUES(?,?,?,?,?,?,?,?)");
                            pst.setString(1, id);
                            pst.setString(2, name);
                            pst.setString(3, mname);
                            pst.setString(4, lname);
                            pst.setString(5, gender);
                            pst.setString(6, dob);
                            pst.setString(7, cardt);
                            pst.setString(8, address);
                            pst.executeUpdate();
                            JOptionPane.showMessageDialog(null, "data added");
                        }

                        //clearfield();
                    }


                    //}
                    catch (Exception a) {
                        a.printStackTrace();
                    }
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"Plese check Confirm checkBox");
                    }
                }
            });


        up.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (terms.isSelected()) {
                    try {
                        String namee = t1.getText();
                        String mnamee = t2.getText();
                        String lnamee = t3.getText();
                        String genderr = "male";


                        if (female.isSelected()) {
                            genderr = "female";
                        }
                        String Dob = day.getSelectedItem() + "-" + month.getSelectedItem() + "-" + year.getSelectedItem();
                        String Cardt = (String) card.getSelectedItem();
                        String Address = tal.getText();

                        int id = Integer.parseInt(t4.getText());
                        screen.setText("Customer ID:" + id + "\n" + "First Name: " + namee + "\n" + "Middle Name: " + mnamee + "\n" + "Last Name :" + lnamee + "\n" + "Gender : " + genderr + "\n" + "DOB: " + Dob + "\n" + "Card Type :" + Cardt + "\n" + "Address :" + Address);

                        Statement st = con.createStatement();
                        st.executeUpdate("update client set fname='" + namee + "'where id=" + id + "");
                        st.executeUpdate("update client set mname='" + mnamee + "'where id=" + id + "");
                        st.executeUpdate("update client set lname='" + lnamee + "'where id=" + id + "");
                        st.executeUpdate("update client set gender='" + genderr + "'where id=" + id + "");
                        st.executeUpdate("update client set DOB='" + Dob + "'where id=" + id + "");
                        st.executeUpdate("update client set CardTypr='" + Cardt + "'where id=" + id + "");
                        st.executeUpdate("update client set address='" + Address + "'where id=" + id + "");

                        JOptionPane.showMessageDialog(null, "Successfuly updated");
                        //clearfield();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Plese check Confirm checkBox");
                }
            }

        });

        transaction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Myframe_1(id);

            }
        });

        setVisible(true);
    }


    static ImageIcon getImage() throws IOException {
        BufferedImage img = ImageIO.read(new File("C:\\Users\\ganes\\IdeaProjects\\Swing\\src\\Images\\Javapng.jpg"));
        Image scaled = img.getScaledInstance(900,600, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(scaled);
        return icon;
    }

//    void clearfield()
//    {
//        t4.setText(null);
//        t1.setText(null);
//        t2.setText(null);
//    }


}


public class ApplicationForm1 {
    public ApplicationForm1() throws IOException {
        Myframe frame=new Myframe();
        ImageIcon image = new ImageIcon("C:\\Users\\ganes\\IdeaProjects\\Swing\\src\\Images\\Javapng.jpg"); //create image icon
        frame.setIconImage(image.getImage());  //change icon of frame
        frame.getContentPane().setBackground(new Color(192,192,192));

    }
}