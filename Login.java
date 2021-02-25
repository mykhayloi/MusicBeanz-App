import java.sql.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;


public class Login extends JFrame implements ActionListener{

   
   private JLabel user;
   private JTextArea username;
   private JLabel pass;
   private JTextArea password;
   private JButton login;
   
   AddSong frame;
   
   BeanzDatabase db = new BeanzDatabase();
   
   String userN;
   String passW;
 
   

   public Login()
   { 
      
      setLayout(new BorderLayout(5,10));
      
      JPanel jpCenter = new JPanel();
      jpCenter.setLayout(new GridLayout(0,2,10,10));
      
      JPanel jpNorth = new JPanel();
      
      user = new JLabel("Username: ");
      jpCenter.add(user);
      
      username = new JTextArea();
      jpCenter.add(username);
      
      pass = new JLabel("Password: ");
      jpCenter.add(pass);
      
      password = new JTextArea();
      jpCenter.add(password); 
      
     //setSiez( JPanel jpSouth = new JPanel();
     // jpSouth.setLayout(new FlowLayout());
      
      //login = new JButton("Login");
    //  jpSouth.add(login);
  //    login.addActionListener(this);
      
      add(jpNorth,BorderLayout.NORTH);
      add(jpCenter,BorderLayout.CENTER);
//       add(jpSouth,BorderLayout.SOUTH);

   }
   
   

   
   public boolean login()
   {
      db.connect();
       String SQLStatement = "SELECT * FROM Users WHERE userName = \""+username.getText()+"\" AND password = \""+password.getText()+"\"";
       try{
 
           
           if(db.getData(SQLStatement).size()>0)
           {
             System.out.println("You're in");
             
           }
           else
           {
            
             System.out.println("No");
             return false;
             
           }
 
          }
         
          catch(NullPointerException e)
          {
             MBException mbe = new MBException(e);
          }
         return true;
         
   }
   
   @Override
   public void actionPerformed(ActionEvent ae)
   {
      Object choice = ae.getSource(); 
      
      if(choice == login)
      {

         if(login())
          {
      
         // frame=new AddSong();
//          JPanel panel=new JPanel();
//          frame.setSize(300,200);
//          frame.setTitle("Add song");
//          frame.setVisible(true);
          
//          

          }
          else
          {
            JOptionPane.showMessageDialog(null, "Incorrect login info");
          }
          dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
      }
      
   }
   
   public void closeTab()
   {
      frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
   }
   
   public static void main(String[] args)
   {
      Login login = new Login();
      login.setTitle("Login");
      login.pack();
      login.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      login.setLocationRelativeTo(null);
      login.setVisible(true);
   }
   
}
