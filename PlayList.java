import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class PlayList extends JFrame implements ActionListener{

   BeanzDatabase bd = new BeanzDatabase();
   StringBuilder sb;
   private JTextArea playlist;
   private JButton add;
   private JButton clear;
   Login login; 
   
   AddSong frame;
   
   String userN;
   String passW;
   String name;
   

   public PlayList()
   { 
      setLayout(new BorderLayout(5,10));
      
      JPanel jpCenter = new JPanel();
      jpCenter.setLayout(new FlowLayout());
      
      playlist = new JTextArea(10,20);
      jpCenter.add(playlist);
      
      playlist.setEditable(false);
      
      JPanel jpSouth = new JPanel();
      jpSouth.setLayout(new FlowLayout());
      
      add = new JButton("Add Song");
      jpSouth.add(add);
      add.addActionListener(this);
      
      clear = new JButton("Clear Playlist");
      jpSouth.add(clear);
      clear.addActionListener(this);
      
      add(jpCenter,BorderLayout.CENTER);
      add(jpSouth,BorderLayout.SOUTH);                                                                      
      
      load();
        
   }
   
   public void playlist()
   {
      System.out.println("Hello");
   }
   
   @Override
   public void actionPerformed(ActionEvent ae)
   {
      Object choice = ae.getSource();
      
      if(choice == add)
      {   
         frame=new AddSong();
         JPanel panel=new JPanel();
         frame.setSize(300,200);
         frame.setTitle("Add song");
         frame.setVisible(true);
         
         
      }else if(choice == clear){
      
         playlist.setText(null);
         remove();
         
      }
   
   }
   
   public void post(String name){
      //Executing query
      String query = "insert into customPlaylist(songName) values('"+name+"')";
      //calling setData
   
      bd.setData(query);
   }
   
   public void load(){
      bd.connect();
      sb = new StringBuilder();
      for (String s : bd.getData("select songName from customPlaylist"))
      {
         
         sb.append(s+"\n");
      }
      playlist.setText(sb.toString());
   }
   
   
   
   public void remove(){
      //Executing query
      String query = "delete from customPlaylist";
      //Calling setData
      bd.setData(query);
   }
   
   public void disableBut()
   {
      add.setEnabled(false);
      clear.setEnabled(false);
   }
   
   public void enableBut()
   {
      add.setEnabled(true);
      clear.setEnabled(true);
   }
   

   
   public static void main(String[] args)
   {
      PlayList pl = new PlayList();
      pl.setTitle("PlayList");
      pl.pack();
      pl.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      pl.setLocationRelativeTo(null);
      pl.setVisible(true);
   }
   
}