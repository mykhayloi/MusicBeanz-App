import java.sql.*;
import java.util.*;

public class Songs{

   private int artistId,songId,albumId,genreId;
   private String artistName,songName, runTime;
   BeanzDatabase dBase = new BeanzDatabase();
   ArrayList<ArrayList<String>> newList = null;

   public Songs(){
   }
   public Songs(int artistId, int songId, int albumId, int genreId, String artistName){
      this.artistId = artistId;
      this.songId = songId;
      this.albumId = albumId;
      this.genreId = genreId;
      this.artistName = artistName;
      this.songName = songName;
   }
   public void setRunTime(String runTime){
      this.runTime = runTime;
   }
   public void setId(int artistId){
      this.songId = songId;
   }
   public void setName(String songName){
      this.songName = songName;
   }
   public int getId(){
      return songId;
   }
   public String getName(){
      return songName;
   }
   public String getRunTime(){
      return runTime;
   }

   public ArrayList<ArrayList<String>> fetchP(String query, String keyword, ArrayList<String> prepareList){
      try{
           //calling new getData
         dBase.getData(query,keyword, prepareList);
           // Catching exception
      }catch(NullPointerException npe){
                              MBException mbe = new MBException(npe);

      }
      return newList;
   } 


}