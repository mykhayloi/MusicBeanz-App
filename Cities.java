import java.sql.*;
import java.util.*;

public class Cities{

   private int countryId,cityId;
   private String cityName;
   BeanzDatabase dBase = new BeanzDatabase();
   ArrayList<ArrayList<String>> newList = null;

   public Cities(){
   }
   public Cities(int countryId,int cityId, String cityName){
      this.countryId = countryId;
      this.cityId = cityId;
      this.cityName = cityName;
   }

   public void setId(int countryId){
      this.countryId = countryId;
   }
   public void setCityId(int countryId){
      this.cityId = cityId;
   }
   public void setName(String cityName){
      this.cityName = cityName;
   }
   public int getId(){
      return countryId;
   }
   public int getCityId(){
      return cityId;
   }
   public String getName(){
      return cityName;
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