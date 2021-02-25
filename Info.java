import java.sql.*;
import java.util.*;

public class Info{

   private int criminalId, countryId, artistId;
   BeanzDatabase dBase = new BeanzDatabase();
   ArrayList<ArrayList<String>> newList = null;

   public Info(){
   }
   public Info(int criminalId,int countryId,int artistId){
      this.criminalId = criminalId;
      this.countryId = countryId;
      this.artistId = artistId;
   }

   public void setId(int criminalId){
      this.artistId = artistId;
   }

   public int getId(){
      return artistId;
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