import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class AddSong extends JFrame implements ActionListener{


   PlayList pl = new PlayList();
   BeanzDatabase bd = new BeanzDatabase();
   JLabel lablename;
   TextField jtfSong;
   JButton jbAdd;
   String name;

   public AddSong()
   {
      setLayout(new BorderLayout(5,10));
      
      JPanel panel=new JPanel();
      
      
      lablename=new JLabel("Enter a song");
      jtfSong=new TextField(30);
      jbAdd=new JButton("Add Song");
      
      panel.add(lablename);
      panel.add(jtfSong);
      panel.add(jbAdd);
      jbAdd.addActionListener(this);
      add(panel);
   }
   
   @Override
   public void actionPerformed(ActionEvent ae)
   {
      Object choice = ae.getSource();
      
      if(choice == jbAdd)
      {
         
         name = jtfSong.getText();
         post(name);
         System.out.println(name);
         //String playlistContent = playlist.getText();
         //playlistContent = playlistContent + name +"\n";
         
         
         pl.load();
      }
   }
   
   public void post(String name){
  
      //Executing query
      String query = "insert into customPlaylist(songName) values('"+name+"')";
          System.out.println("THIS IS WHAT WE ADDED"+name);
      //calling setData
      bd.setData(query);
   }
   
   
   public static void main(String[] args)
   {
   
         AddSong frame=new AddSong();
         JPanel panel=new JPanel();
         frame.setSize(300,200);
         frame.setTitle("Add song");
         frame.setVisible(true);
     }

}