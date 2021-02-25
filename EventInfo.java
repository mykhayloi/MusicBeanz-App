import java.sql.*;
import java.util.*;

public class EventInfo{

   private int eventInfoId, cityId, artistId;
   BeanzDatabase dBase = new BeanzDatabase();
   ArrayList<ArrayList<String>> newList = null;

   public EventInfo(){
   }
   public EventInfo(int eventInfoId,int cityId,int artistId){
      this.eventInfoId = eventInfoId;
      this.cityId = cityId;
      this.artistId = artistId;
   }

   public void setId(int eventInfoId){
      this.eventInfoId = eventInfoId;
   }
   public int getId(){
      return eventInfoId;
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