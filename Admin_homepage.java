package JavaProject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


class Admin_homepage1 extends JFrame {

    Connection con = null;

    JTabbedPane tp;
    JPanel p1, p2, p3;
    JTextField cust_id, cust_password, cust_name, cust_username, del_cust;
    JLabel j1;


    Admin_homepage1() {
        //Connection
        con = (Connection) DB.dbconnect();
        setTitle("Server Side");
        setLocation(280, 120);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(Color.pink);

        p2 = new JPanel();
        p2.setLayout(null);
        p2.setBackground(Color.pink);

        p3 = new JPanel();
        p3.setLayout(null);
        p3.setBackground(Color.pink);


        tp = new JTabbedPane();
        tp.setBounds(0, 0, 800, 600);
        tp.add("Application Form", p1);
        tp.add("Transaction", p2);
        tp.add("Delete Data", p3);
        add(tp);


        JButton b1 = new JButton("Show Application");
        b1.setBounds(300, 30, 160, 40);
        p1.add(b1);

        JButton b2 = new JButton("Transaction");
        b2.setBounds(300, 30, 160, 40);
        p2.add(b2);


        //DELETE
        JButton b3 = new JButton("Delete Customer");
        b3.setBounds(190, 100, 150, 50);
        p3.add(b3);

        j1 = new JLabel("Customer ID:");
        j1.setBounds(50, 40, 120, 40);
        p3.add(j1);

        del_cust = new JTextField();
        del_cust.setBounds(190, 40, 180, 40);
        p3.add(del_cust);


        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String[][] rowData = {{"Customer ID", "First Name", "Middle Name", "Last Name", "Gender", "DOB", "Card Type", "Billing address"}};
                    String[] ColName = {"Customer ID", "First Name", "Middle Name", "Last Name", "Gender", "DOB", "Card Type", "Billing address"};
                    final DefaultTableModel model = new DefaultTableModel(rowData, ColName);
                    final JTable table = new JTable(model);
                    table.setBounds(40, 80, 700, 300);
                    ScrollPane s1 = new ScrollPane();
                    s1.add(table);
                    s1.setBounds(40, 80, 700, 300);
                    p1.add(s1);

                    table.setBorder((BorderFactory.createLineBorder(Color.BLACK, 1)));
                    table.setRowHeight(table.getRowHeight() + 18);


                    PreparedStatement pst = (PreparedStatement) con.prepareStatement("select * from client");
                    ResultSet r = pst.executeQuery();

                    while (r.next()) {
                        String id = String.valueOf(r.getInt("id"));
                        String Fname = r.getString("fname");
                        String Mname = r.getString("mname");
                        String Lname = r.getString("lname");
                        String Gender = r.getString("gender");
                        String Dob = r.getString("DOB");
                        String Cardtype = r.getString("CardTypr");
                        String Address = r.getString("Address");


                        String tbData[] = {id, Fname, Mname, Lname, Gender, Dob, Cardtype, Address};
                        model.addRow(tbData);

                    }

                } catch (Exception d) {
                    System.out.println(d);
                }
            }
        });

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String[][] rowData = {{"Customer ID", "Purchase", "Store Name"}};
                    String[] ColName = {"Customer ID", "Purchase", "Store Name"};
                    final DefaultTableModel model1 = new DefaultTableModel(rowData, ColName);
                    final JTable table2 = new JTable(model1);
                    table2.setBounds(40, 80, 400, 200);
                    table2.setBorder((BorderFactory.createLineBorder(Color.BLACK, 1)));
                    ScrollPane s = new ScrollPane();
                    s.add(table2);
                    s.setBounds(40, 80, 700, 300);
                    p2.add(s);
                    PreparedStatement pst1 = (PreparedStatement) con.prepareStatement("select * from cpclients");
                    ResultSet r1 = pst1.executeQuery();
                    table2.setRowHeight(table2.getRowHeight() + 18);


                    while (r1.next()) {
                        String Id = String.valueOf(r1.getInt("id"));
                        String Purchase = r1.getString("purchase");
                        String Storename = r1.getString("StoreName");

                        String tbData[] = {Id, Purchase, Storename};
                        model1.addRow(tbData);

                    }

                } catch (Exception d) {
                    System.out.println(d);
                }
            }
        });


        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int p2 = JOptionPane.showConfirmDialog(null, "Do you really want to delete Customer with id :" + Integer.parseInt(del_cust.getText()) + "", "Delete", JOptionPane.YES_NO_OPTION);
                if (p2 == 0) {
                    try {
                        String sql = "delete from client where id=?";
                        PreparedStatement pst1 = (PreparedStatement) con.prepareStatement(sql);
                        pst1.setInt(1, Integer.parseInt(del_cust.getText()));
                        pst1.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Customer no:" + Integer.parseInt(del_cust.getText()) + " is Deleted Successfully");
                        clearField1();
                    } catch (Exception e2) {
                        System.out.println(e2);
                    }
                }
                clearField1();
            }
        });


        setVisible(true);
        validate();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void clearField1() {
        cust_id.setText(null);
        cust_name.setText(null);
        cust_username.setText(null);
        cust_password.setText(null);
        del_cust.setText(null);
    }

}

public class Admin_homepage {

    public static void main(String[] args) {

        Admin_homepage1 a1 = new Admin_homepage1();
        a1.setSize(800, 600);

    }
}