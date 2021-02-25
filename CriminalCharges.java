import java.sql.*;
import java.util.*;

public class CriminalCharges{

   private int criminalId;
   private String artistName;
   BeanzDatabase dBase = new BeanzDatabase();
   ArrayList<ArrayList<String>> newList = null;

   public CriminalCharges(){
   }
   public CriminalCharges(int criminalId, String artistName){
      this.criminalId = criminalId;
      this.artistName = artistName;
   }

   public void setId(int criminalId){
      this.criminalId = criminalId;
   }
   public void setName(String artistName){
      this.artistName = artistName;
   }
   public int getId(){
      return criminalId;
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