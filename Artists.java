import java.sql.*;
import java.util.*;

public class Artists{

   private int artistId;
   private String artistName;
   BeanzDatabase dBase = new BeanzDatabase();
   ArrayList<ArrayList<String>> newList = null;

   public Artists(){
   }
   public Artists(int artistId, String artistName){
      this.artistId = artistId;
      this.artistName = artistName;
   }

   public void setId(int artistId){
      this.artistId = artistId;
   }
   public void setName(String artistName){
      this.artistName = artistName;
   }
   public int getId(){
      return artistId;
   }
   public String getName(){
      return artistName;
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