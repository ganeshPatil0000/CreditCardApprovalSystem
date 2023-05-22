package JavaProject;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class Login_page2 extends JFrame
{
    JPanel contentPane;

    Connection con = null;
    public Login_page2() throws IOException {

        con = (Connection) DB.dbconnect();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize( 700, 500);
        setContentPane(new JLabel(getImage()));
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(153, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Register And Login Here !!");
        lblNewLabel.setFont(new Font("Sylfaen", Font.BOLD, 40));
        lblNewLabel.setBackground(Color.RED);
        lblNewLabel.setBounds(100, 80, 544, 100);
        contentPane.add(lblNewLabel);

        JButton btnNewButton = new JButton("Login");
        btnNewButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                try {
                    NewThread newThread = new NewThread();
                    newThread.start();

                }
                catch(StackOverflowError e1)
                {
                    e1.printStackTrace();
                }

            }
        });

        btnNewButton.setBackground(new Color(255, 0, 102));
        btnNewButton.setForeground(new Color(255, 255, 255));
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel.setBackground(Color.RED);
        btnNewButton.setBounds(120, 230, 164, 47);
        contentPane.add(btnNewButton);


        JButton btnNewButton_1 = new JButton("Register");
        btnNewButton_1.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {
                try {
                    new Register();
                } catch(StackOverflowError e1)
                {
                    e1.printStackTrace();
                }
            }
        });
        btnNewButton_1.setForeground(new Color(255, 255, 255));
        btnNewButton_1.setBackground(new Color(255, 0, 102));
        btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnNewButton_1.setBounds(350, 230, 164, 47);
        contentPane.add(btnNewButton_1);




    }
    public static void main(String[] args) throws IOException {

        Login_page2 frame = new Login_page2();
        frame.setVisible(true);

    }

    static ImageIcon getImage() throws IOException {
        BufferedImage img = ImageIO.read(new File("C:\\Users\\ganes\\IdeaProjects\\Swing\\src\\Images\\Loin png.jpg"));
        Image scaled = img.getScaledInstance(700, 500, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(scaled);
        return icon;
}
}

class NewThread extends Thread{
    public void run(){
        try {
            LoginPage s = new LoginPage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}