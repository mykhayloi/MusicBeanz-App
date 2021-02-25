import java.sql.*;
import java.util.*;

public class Countries{

   private int countryId;
   private String countryName;
   BeanzDatabase dBase = new BeanzDatabase();
   ArrayList<ArrayList<String>> newList = null;

   public Countries(){
   }
   public Countries(int countryId, String countryName){
      this.countryId = countryId;
      this.countryName = countryName;
   }

   public void setId(int countryId){
      this.countryId = countryId;
   }
   public void setName(String countryName){
      this.countryName = countryName;
   }
   public int getId(){
      return countryId;
   }
   public String getName(){
      return countryName;
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