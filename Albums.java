import java.sql.*;
import java.util.*;

public class Albums{

   private int albumId, artistId, genreId;
   private String albumName;
   BeanzDatabase dBase = new BeanzDatabase();
   ArrayList<ArrayList<String>> newList = null;

   public Albums(){
   }
   public Albums(int albumId,int artistId,int genreId, String albumName){
      this.albumId = albumId;
      this.albumName = albumName;
      this.artistId = artistId;
      this.genreId = genreId;
   }

   public void setId(int albumId){
      this.albumId = albumId;
   }
   public void setGenreId(int genreId){
      this.genreId = genreId;
   }
   public void setArtistId(int artistId){
      this.artistId = artistId;
   }
   public void setName(String albumName){
      this.albumName = albumName;
   }
   public int getId(){
      return albumId;
   }
   public int getArtistId(){
      return artistId;
   }
   public int getGenreId(){
      return genreId;
   }
   public String getName(){
      return albumName;
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