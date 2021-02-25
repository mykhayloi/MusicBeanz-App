import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class MusicBeanz extends JFrame implements ActionListener{

   ArrayList<ArrayList<String>> resultList = new ArrayList();
   ArrayList preparedList = new ArrayList();
   BeanzDatabase bd = new BeanzDatabase();;
   private JRadioButton song;
   private JRadioButton album;
   private JRadioButton genre;
   private JRadioButton artist;
   private JRadioButton upcomingEv;
   private JButton search;
   private JTextArea searchKeyWord;
   
   private String selection = "";

   public MusicBeanz()
   {
      JMenuBar menuBar = new JMenuBar();
      
      JMenu menu = new JMenu("Extra");
      JMenuItem add = new JMenuItem("PlayList");
      
      menuBar.add(menu);
      menu.add(add);
      
      add.addActionListener(this);
      
   
      setLayout(new BorderLayout(5,10));
      
      JPanel jpNorth = new JPanel();
      jpNorth.setLayout(new GridLayout(0,2));
      
      JLabel keyWord = new JLabel("Key Word: ");
      jpNorth.add(keyWord);
      
      searchKeyWord = new JTextArea();
      jpNorth.add(searchKeyWord);
      
      JPanel jpCenter = new JPanel();
      jpCenter.setLayout(new GridLayout(0,3));
      
      ButtonGroup sel = new ButtonGroup();
      
      song = new JRadioButton("Song");
      jpCenter.add(song);
      song.addActionListener(this);
      
      album = new JRadioButton("Album");
      jpCenter.add(album);
      album.addActionListener(this);
      
      genre = new JRadioButton("Genre");
      jpCenter.add(genre);
      genre.addActionListener(this);
      
      artist = new JRadioButton("Artist");
      jpCenter.add(artist);
      artist.addActionListener(this);
      
      upcomingEv = new JRadioButton("Upcoming Events");
      jpCenter.add(upcomingEv);
      upcomingEv.addActionListener(this);
      
      sel.add(song);
      sel.add(album);
      sel.add(genre);
      sel.add(artist);
      sel.add(upcomingEv);
      
      JPanel jpSouth = new JPanel();
      jpSouth.setLayout(new FlowLayout());
      
      search = new JButton("Search");
      jpSouth.add(search);
      search.addActionListener(this);
   
      
      setJMenuBar(menuBar);
      add(jpNorth,BorderLayout.NORTH);
      add(jpCenter,BorderLayout.CENTER);
      add(jpSouth,BorderLayout.SOUTH);
   }
   
   
   
   @Override
   public void actionPerformed(ActionEvent ae)
   {
      Object choice = ae.getSource(); 
      
      if(ae.getActionCommand().equals("PlayList"))
      {
         PlayList pl = new PlayList();
         pl.setTitle("PlayList");
         pl.pack();
         pl.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         pl.setLocationRelativeTo(null);
         pl.setVisible(true);
         pl.disableBut();
         
         Login login = new Login();
         login.setTitle("Login");
         login.pack();
         login.setSize(500,300);
         login.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         login.setLocationRelativeTo(null);
         login.setVisible(true);
         
         JButton loginBut = new JButton("Login");
         JPanel jpSouth = new JPanel();
         jpSouth.setLayout(new FlowLayout());
         jpSouth.add(loginBut);
         login.add(jpSouth,BorderLayout.SOUTH);
         
         loginBut.addActionListener(
            new ActionListener(){
               public void actionPerformed(ActionEvent e){
                  if(login.login())
                  {
                     pl.enableBut();
                     login.dispatchEvent(new WindowEvent(login, WindowEvent.WINDOW_CLOSING));
                  }
               
               }
            });
         
        
         
      }
      else if(choice == song)
      {
         selection = "song";
         System.out.println(selection);
      }
      else if(choice == album)
      {
         selection = "album";
         System.out.println(selection);
      }
      else if(choice == genre)
      {
         selection = "genre";
         System.out.println(selection);
      }
      else if(choice == artist)
      {
         selection = "artist";
         System.out.println(selection);
      }
      else if(choice == upcomingEv)
      {
         selection = "upcomingEvent";
         System.out.println(selection);
      }
      else if(choice == search){
         System.out.println(selection);
         String keyword = searchKeyWord.getText();
         bd.connect();
      
         resultList = bd.getData("select * from "+selection+"s where "+selection+"Name = ?",keyword,preparedList);
         if(selection == "song"){
            String name = resultList.get(1).get(1);
            System.out.println(name);
            String artistId = resultList.get(1).get(2);
            String genreId = resultList.get(1).get(3);
            String albumId = resultList.get(1).get(4);
            String runTime = resultList.get(1).get(5);
            ArrayList<ArrayList<String>> resultListArtist = new ArrayList();
            resultListArtist = bd.getData("select * from artists where artistId = ?",artistId,preparedList);
            String artistName = resultListArtist.get(1).get(1);
            System.out.println(artistName);
            ArrayList<ArrayList<String>> resultListGenre = new ArrayList();
            resultListArtist = bd.getData("select * from genres where genreId = ?",genreId,preparedList);
            String genreName = resultListArtist.get(1).get(1);
            System.out.println(genreName);
            ArrayList<ArrayList<String>> resultListAlbum = new ArrayList();
            resultListArtist = bd.getData("select * from albums where albumId = ?",albumId,preparedList);
            String albumName = resultListArtist.get(1).get(1);
            System.out.println(albumName);
            System.out.println(runTime);
            JOptionPane.showMessageDialog(this, "Artist: "+artistName+"\nGenre: "+genreName+"\nAlbum: "+albumName+"\nDuration: "+runTime);
         }else if(selection == "artist"){
            String name = resultList.get(1).get(1);
            System.out.println(name);
            String artistId = resultList.get(1).get(0);
            ArrayList<ArrayList<String>> resultListArtist = new ArrayList();
            resultListArtist = bd.getData("select * from songs  where artistID = ?",artistId,preparedList);
            String artistSong1 = resultListArtist.get(1).get(1);
            String artistSong2 = resultListArtist.get(1).get(7);
            String artistSong3 = resultListArtist.get(1).get(13);
            System.out.println(artistSong1);
            System.out.println(artistSong2);
            System.out.println(artistSong3);
            ArrayList<ArrayList<String>> resultListAlbums = new ArrayList();
            resultListAlbums = bd.getData("select * from albums  where artistID = ?",artistId,preparedList);
            String artistalbum1 = resultListAlbums.get(1).get(1);
            System.out.println(artistalbum1);
            ArrayList<ArrayList<String>> resultListCriminal = new ArrayList();
            resultListCriminal = bd.getData("select * from criminalCharges  where criminalID = ?",artistId,preparedList);
            String criminal = resultListCriminal.get(1).get(1);
            System.out.println(criminal);
            JOptionPane.showMessageDialog(this, "Artist: "+name+"\nSongs: "+artistSong1+","+artistSong2+","+artistSong3+"\nAlbum: "+artistalbum1+"\nCriminal Charge: "+criminal);
         }else if(selection == "upcomingEvent"){
         //System.out.println(resultList.size());
            String eventname = resultList.get(1).get(1);
            String artistid = resultList.get(1).get(2);
            String cityid = resultList.get(1).get(3);
         
            System.out.println(eventname);
            System.out.println(artistid);
            System.out.println(cityid);
            ArrayList<ArrayList<String>> resultListEvent = new ArrayList();
            resultListEvent = bd.getData("select * from artists where artistId = ?",artistid,preparedList);
            String artistName = resultListEvent.get(1).get(1);
            System.out.println(artistName);
            ArrayList<ArrayList<String>> resultListCity = new ArrayList();
            resultListCity = bd.getData("select * from cities where cityId = ?",cityid,preparedList);
            String cityName = resultListCity.get(1).get(1);
            System.out.println(cityName);
            JOptionPane.showMessageDialog(this, "Event Name: "+eventname+"\nArtist: "+artistName+"\nCity: "+cityName);
         }else if(selection == "genre"){
            String genreid = resultList.get(1).get(0);
            String genreName = resultList.get(1).get(1);
            System.out.println(genreid);
            ArrayList<ArrayList<String>> resultListgenre = new ArrayList();
            resultListgenre = bd.getData("select * from songs where genreId = ?",genreid,preparedList);
            String song1 = resultListgenre.get(1).get(1);
            String song2 = resultListgenre.get(1).get(7);
            String song3 = resultListgenre.get(1).get(13);
            System.out.println(song1);
            JOptionPane.showMessageDialog(this, "Genre: "+genreName+"\nSongs:\n"+song1+",\n"+song2+",\n"+song3);
         }else if(selection == "album"){
            String genreid = resultList.get(1).get(0);
            String genreName = resultList.get(1).get(1);
            System.out.println(genreid);
            ArrayList<ArrayList<String>> resultListgenre = new ArrayList();
            resultListgenre = bd.getData("select * from songs where albumId = ?",genreid,preparedList);
            String song1 = resultListgenre.get(1).get(1);
            String song2 = resultListgenre.get(1).get(7);
            String song3 = resultListgenre.get(1).get(13);
            System.out.println(song1);
            JOptionPane.showMessageDialog(this, "Album: "+genreName+"\nSongs:\n"+song1+",\n"+song2+",\n"+song3);
         
         }
      }
   }

   
   public String getSelected()
   {
      return selection;
   }
   

      
   
   public static void main(String[] args)
   {
      BeanzDatabase bd = new BeanzDatabase();
      MusicBeanz mz = new MusicBeanz();
      bd.connect();
      mz.setTitle("Music Beanz");
      mz.pack();
      mz.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      mz.setLocationRelativeTo(null);
      mz.setVisible(true);
   }
   
}
