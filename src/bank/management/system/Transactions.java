package bank.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Transactions extends JFrame implements ActionListener{
    JButton deposit,withdrawl,fastcash,mini,pinchange,balanceenquiry,exit;
    String pinnumber;
    Transactions(String pinnumber){
        this.pinnumber=pinnumber;
        setUndecorated(true);
        
        setSize(900,650);
        setLocation(300,0);
        setLayout(null);
        setVisible(true);
        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2=i1.getImage().getScaledInstance(900,900,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(0,0,900,650);
        add(image);
        
        JLabel text=new JLabel("Please Select Your Transactions");
        text.setBounds(210,160,700,35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System",Font.BOLD,16));
        image.add(text);
        
        deposit =new JButton("Deposit");
        deposit.setBounds(170,275,150,30);
        image.add(deposit);
        deposit.addActionListener(this);
        
        withdrawl =new JButton("Cash Wihtdrawl");
        withdrawl.setBounds(355,275,150,30);
        image.add(withdrawl);
        withdrawl.addActionListener(this);
        
        fastcash =new JButton("Fast Cash");
        fastcash.setBounds(170,310,150,30);
        image.add(fastcash);
        fastcash.addActionListener(this);
        
        mini =new JButton("Mini Statement");
        mini.setBounds(355,310,150,30);
        image.add(mini);
        mini.addActionListener(this);
        
        pinchange =new JButton("Pinchange");
        pinchange.setBounds(170,345,150,30);
        image.add(pinchange);
        pinchange.addActionListener(this);
        
        balanceenquiry =new JButton("Balance Enquiry");
        balanceenquiry.setBounds(355,345,150,30);
        image.add(balanceenquiry);
        balanceenquiry.addActionListener(this);
        
        exit =new JButton("Exit");
        exit.setBounds(355,380,150,30);
        image.add(exit);
        exit.addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==exit){
            System.exit(0);
        }else if(ae.getSource()==deposit){
            setVisible(false);
            new Deposite(pinnumber).setVisible(true);
        }else if(ae.getSource()==withdrawl){
            setVisible(false);
            new Withdraw(pinnumber).setVisible(true);
        }
        else if(ae.getSource()==fastcash){
            setVisible(false);
            new FastCash(pinnumber).setVisible(true);
        }
        else if(ae.getSource()==pinchange){
            setVisible(false);
            new Pin(pinnumber).setVisible(true);
        }
        else if(ae.getSource()==balanceenquiry){
            setVisible(false);
            new BalanceEnquiry(pinnumber).setVisible(true);
        }
         else if(ae.getSource()==mini){
            new MiniStatement(pinnumber).setVisible(true);
        }
    }
    public static void main(String args[]){
        new Transactions("");
    }
}
