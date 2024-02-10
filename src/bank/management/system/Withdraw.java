package bank.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.util.*;

public class Withdraw extends JFrame implements ActionListener{
    JButton withdraw,back;
    JTextField amount;
    String pinnumber;
    public Withdraw(String pinnumber){
        this.pinnumber=pinnumber;
        setSize(900,650);
        setLocation(300,0);
        setVisible(true);
        setLayout(null);
        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2=i1.getImage().getScaledInstance(900,900,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(0,0,900,650);
        add(image);
        
        JLabel text=new JLabel("Enter the amount you want to withdrawl");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System",Font.BOLD,16));
        text.setBounds(200,160,700,35);
        image.add(text);
        
        amount=new JTextField();
        amount.setFont(new Font("Raleway",Font.BOLD,22));
        amount.setBounds(200,205,295,30);
        image.add(amount);
        
        withdraw=new JButton("Withdraw");
        withdraw.setBounds(355,290,150,30);
        image.add(withdraw);
        withdraw.addActionListener(this);
        
        back=new JButton("Back");
        back.setBounds(355,325,150,30);
        image.add(back);
        back.addActionListener(this);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == withdraw){
             String number=amount.getText();
             Date date=new Date();
             if(number.equals("")){
                 JOptionPane.showMessageDialog(null,"PLease enter the amount you want to Withdrawl");
             }else{
                 try{
                     conn c=new conn();

                     // calculate available balance
                     ResultSet rs=c.s.executeQuery("select * from bank where pin='"+pinnumber+"'");
                     int balance=0;
                     while(rs.next()){
                         if(rs.getString("type").equals("Deposit")){
                             balance+=Integer.parseInt(rs.getString("amount"));
                         }else{
                             balance-=Integer.parseInt(rs.getString("amount"));
                         }
                     }

                     // check if balance is less than desired withdrawal amount
                     if(ae.getSource() != back && balance < Integer.parseInt(number)){
                         JOptionPane.showMessageDialog(null,"Insufficiant Balance");
                         return;
                     }

                     // If sufficient balance is available
                    String q="insert into bank values('"+pinnumber+"','"+date+"','Withdraw','"+number+"')";
                    c.s.executeUpdate(q);
                    JOptionPane.showMessageDialog(null,"Rs "+number+" Withdrawl Succesfully");
                    setVisible(false);
                    new Transactions(pinnumber).setVisible(true);
                 }catch(Exception e){
                     System.out.println(e);
                 }
                 
             }
        }else if(ae.getSource() == back){
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        }
    }
    
    public static void main(String args[]){
        new Withdraw(""); 
    }
}

